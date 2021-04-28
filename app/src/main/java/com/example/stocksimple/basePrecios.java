
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

public class basePrecios extends AppCompatActivity implements View.OnClickListener {

    Button btn_GUARDAR, btn_LEER;
    MyDbAdapter helper = new MyDbAdapter(this);

    ProgressDialog loading;

    String idPrecio, codItem, costo, precio, fecha;

    String url = "https://script.google.com/macros/s/AKfycbykKAixmwR3lfbLHA_iMFehrR12dElPLMD14cCkBpFo3gdKGUw/exec";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_precios);

        btn_GUARDAR = (Button) findViewById(R.id.btn_GUARDAR);
        btn_GUARDAR.setOnClickListener(this);
        btn_LEER = (Button) findViewById(R.id.btn_LEER);
        btn_LEER.setOnClickListener(this);
    }

    private void exportDB(String idPrecio, String codItem, String costo, String precio, String fecha) {
//This is the part where data is transfeered from Your Android phone to Sheet by using HTTP Rest API calls

       StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {@Override public void onResponse(String response) {}},
                new Response.ErrorListener() {@Override public void onErrorResponse(VolleyError error) {
                    Toast.makeText(basePrecios.this, "error", Toast.LENGTH_SHORT).show();}})
       {@Override protected Map<String, String> getParams() {Map<String, String> parmas = new HashMap<>();
                //here we pass params
                parmas.put("action", "addPrecios");
                parmas.put("idPrecio", idPrecio);
                parmas.put("codItem", codItem);
                parmas.put("costo", costo);
                parmas.put("precio", precio);
                parmas.put("fecha", fecha);
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
            int dimension = helper.getidPrecio().size();
            for (int i = 0; i<dimension; i++) {
                idPrecio = helper.getidPrecio().get(i);
                codItem = helper.getCodItem().get(i);
                costo = helper.getCosto().get(i);
                precio = helper.getPrecio().get(i);
                fecha = helper.getFecha().get(i);
                exportDB(idPrecio,codItem, costo, precio, fecha);
                try {
                    Thread.sleep (300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(this, "ENVIADO " +  dimension + " REGISTROS", Toast.LENGTH_SHORT).show();
         }

        if (v == btn_LEER) {
            helper = new MyDbAdapter(this);
            helper.borrarDatosPrecio();
            getItems();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    private void getItems() {
        loading =  ProgressDialog.show(this,"Loading","please wait",false,true);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + "?action=getPrecios",
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
            JSONArray jarray = jobj.getJSONArray("precios");

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                String idPrecio = jo.getString("idPrecio");
                String codItem = jo.getString("codItem");
                String costo = jo.getString("costo");
                String precio = jo.getString("precio");
                String fecha = jo.getString("fecha");
                long id = helper.modificarBDPrecio(idPrecio, codItem, Float.valueOf(costo), Float.valueOf(precio), fecha);
                if (id <= 0) {
                    Toast.makeText(this, "ERROR!!!", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(this, idPrecio + "-" + codItem + "-" + costo + "-" + precio + "-" + fecha, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "ERROR DE LECTURA " , Toast.LENGTH_SHORT).show();
        }
        loading.dismiss();
    }
}