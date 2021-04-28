package com.example.stocksimple;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class baseStock extends AppCompatActivity  implements View.OnClickListener {

    Button btn_GUARDAR, btn_LEER;
    MyDbAdapter helper = new MyDbAdapter(this);

    ProgressDialog loading;

    String idItem, codigoBarra, descripcion, cantidad;

    String url = "https://script.google.com/macros/s/AKfycbykKAixmwR3lfbLHA_iMFehrR12dElPLMD14cCkBpFo3gdKGUw/exec";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_stock);

        btn_GUARDAR = (Button) findViewById(R.id.btn_GUARDAR);
        btn_GUARDAR.setOnClickListener(this);
        btn_LEER = (Button) findViewById(R.id.btn_LEER);
        btn_LEER.setOnClickListener(this);
    }

    private void exportDB(String idItem, String codigoBarra, String descripcion, String cantidad) {
//This is the part where data is transfeered from Your Android phone to Sheet by using HTTP Rest API calls
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {@Override public void onResponse(String response) {}},
                new Response.ErrorListener() {@Override public void onErrorResponse(VolleyError error) {Toast.makeText(baseStock.this, "error", Toast.LENGTH_SHORT).show();}})
        {@Override protected Map<String, String> getParams() {Map<String, String> parmas = new HashMap<>();
                //here we pass params
                parmas.put("action", "addStocks");
                parmas.put("idItem", idItem);
                parmas.put("codigoBarra", codigoBarra);
                parmas.put("descripcion", descripcion);
                parmas.put("cantidad", cantidad);
                return parmas;}};
        int socketTimeOut = 20000;// u can change this .. here it is 20 seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_GUARDAR) {
            int dimension = helper.getidItem().size();
            for (int i = 0; i<dimension; i++) {
                idItem = helper.getidItem().get(i);
                codigoBarra = helper.getCodigoBarra().get(i);
                descripcion = helper.getDescripcion().get(i);
                cantidad = helper.getCantidad().get(i);
                exportDB(idItem,codigoBarra, descripcion, cantidad);
                try {Thread.sleep (300);}
                catch (InterruptedException e) {e.printStackTrace();}
            }
            Toast.makeText(this, "ENVIADO " +  dimension + " REGISTROS", Toast.LENGTH_SHORT).show();
        }
        if (v == btn_LEER) {
            helper = new MyDbAdapter(this);
            helper.borrarDatosStock();
            getStock();
            try {
                Thread.sleep (300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    private void getStock() {
        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + "?action=getStock",
                response -> parseItems(response),
                error -> {});
        int socketTimeOut = 10000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void parseItems(String jsonResponse) {
       // ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            JSONObject jobj = new JSONObject(jsonResponse);
            JSONArray jarray = jobj.getJSONArray("stock");
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                String idItem = jo.getString("idItem");
                String codigoBarra = jo.getString("codigoBarra");
                String descripcion = jo.getString("descripcion");
                String cantidad = jo.getString("cantidad");
                long id = helper.modificarBDStock(idItem, codigoBarra, descripcion, Float.valueOf(cantidad));
                if (id <= 0) {
                    Toast.makeText(this, "ERROR!!!", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(this, idItem + "-" + codigoBarra + "-" + descripcion + "-" + cantidad, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this,  e.toString() , Toast.LENGTH_LONG).show();
        }
        loading.dismiss();
    }
}
