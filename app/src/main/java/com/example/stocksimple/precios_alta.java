package com.example.stocksimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import devliving.online.mvbarcodereader.MVBarcodeScanner;

public class precios_alta extends AppCompatActivity implements View.OnClickListener {

    private Button b_auto, btn_desc, btn_precio_graba;
    private MVBarcodeScanner.ScanningMode modo_Escaneo;
    private int CODE_SCAN = 1;

    // para ingresar datos
    private EditText codigo, descripcion, costo, precio;

    MyDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.precios_alta);

        helper = new MyDbAdapter(this);

        UI();
    }

    private void UI(){
        b_auto = findViewById(R.id.btn_m_auto);
        btn_desc = findViewById(R.id.btn_desc);
        btn_precio_graba = findViewById(R.id.btn_precio_graba);
        costo = findViewById(R.id.precio_costo);
        codigo = findViewById(R.id.precioAlta_escaneo);
        descripcion = findViewById(R.id.precio_descripcion);

        precio = findViewById(R.id.precio_precio);

        b_auto.setOnClickListener(this);
        btn_desc.setOnClickListener(this);
        btn_precio_graba.setOnClickListener(this);

        codigo.setBackgroundResource(R.drawable.edittext_bg);
        descripcion.setBackgroundResource(R.drawable.edittext_bg);
        costo.setBackgroundResource(R.drawable.edittext_bg);
        precio.setBackgroundResource(R.drawable.edittext_bg);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_m_auto:
                modo_Escaneo = MVBarcodeScanner.ScanningMode.SINGLE_AUTO;
                new MVBarcodeScanner.Builder().setScanningMode(modo_Escaneo).setFormats(Barcode.ALL_FORMATS)
                        .build()
                        .launchScanner(this, CODE_SCAN);
                break;

            case R.id.btn_desc:
                try {
                    descripcion.setText(helper.getDescripcion(codigo.getText().toString()).get(0));
                } catch (Exception e) {
                    Toast.makeText(this, " ITEM INEXISTENTE !!!", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.btn_precio_graba:
                try {
                    Grabar(view);
                } catch (Exception e) {
                    Toast.makeText(this, " ERROR DE GRABACION !!!", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_SCAN) {
            if (resultCode == RESULT_OK && data != null
                    && data.getExtras() != null) {
                if (data.getExtras().containsKey(MVBarcodeScanner.BarcodeObject)) {
                    Barcode mBarcode = data.getParcelableExtra(MVBarcodeScanner.BarcodeObject);
                    codigo.setText(mBarcode.rawValue);
                } else if (data.getExtras().containsKey(MVBarcodeScanner.BarcodeObjects)) {
                    List<Barcode> mBarcodes = data.getParcelableArrayListExtra(MVBarcodeScanner.BarcodeObjects);
                    StringBuilder s = new StringBuilder();
                    for (Barcode b:mBarcodes){
                        s.append(b.rawValue + "\n");
                    }
                    codigo.setText(s.toString());
                }
            }
        }
    }

    public void Grabar(View view) {

        if (codigo.getText().toString().isEmpty() || descripcion.getText().toString().isEmpty() || costo.getText().toString().isEmpty()|| precio.getText().toString().isEmpty()) {
            Toast.makeText(this, "Faltan datos!!!", Toast.LENGTH_LONG).show();
        } else {
            String t1 = codigo.getText().toString();
            Float t2 = Float.valueOf(costo.getText().toString());
            Float t3 = Float.valueOf(precio.getText().toString());
            String t4 = fechaHoy();

            long id = 0;
            id = helper.insertPrecio(t1, t2, t3, t4);

            if (id <= 0) {
                Toast.makeText(this, "ERROR!!!", Toast.LENGTH_LONG).show();
                codigo.setText("");
                descripcion.setText("");
                costo.setText("");
                precio.setText("");
            } else {
                Toast.makeText(this, "Successful!!!", Toast.LENGTH_LONG).show();
                codigo.setText("");
                descripcion.setText("");
                costo.setText("");
                precio.setText("");
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public String fechaHoy() {

        String fechaHoy = null;
        DateFormat diaFormat = new SimpleDateFormat("dd");
        DateFormat mesFormat = new SimpleDateFormat("MM");
        DateFormat añoFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        fechaHoy = diaFormat.format(date) + "/" + mesFormat.format(date) + "/" + añoFormat.format(date);
        return fechaHoy;
    }

}
