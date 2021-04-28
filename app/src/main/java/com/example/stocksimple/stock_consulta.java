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

import java.util.List;

import devliving.online.mvbarcodereader.MVBarcodeScanner;

public class stock_consulta extends AppCompatActivity implements View.OnClickListener {

    private Button b_auto, btn_desc;
    private MVBarcodeScanner.ScanningMode modo_Escaneo;
    private int CODE_SCAN = 1;

    // para ingresar datos
    private EditText codigo;
    private TextView descripcion, cantidad;

    MyDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_consulta);

        helper = new MyDbAdapter(this);

        UI();

    }

    private void UI(){
        b_auto = findViewById(R.id.btn_m_auto);
        btn_desc = findViewById(R.id.btn_desc);

        codigo = findViewById(R.id.stockCons_escaneo);
        descripcion = findViewById(R.id.consulta_descripcion);
        cantidad = findViewById(R.id.consulta_cantidad);

        b_auto.setOnClickListener(this);
        btn_desc.setOnClickListener(this);

        codigo.setBackgroundResource(R.drawable.edittext_bg);
        descripcion.setBackgroundResource(R.drawable.edittext_bg);
        cantidad.setBackgroundResource(R.drawable.edittext_bg);

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
                    Float q = Float.parseFloat(helper.getCantidadStock(codigo.getText().toString()).toString());
                    cantidad.setText(q.toString());
                } catch (Exception e) {
                    descripcion.setText("");
                    Toast.makeText(this, " ITEM INEXISTENTE !!!", Toast.LENGTH_LONG).show();
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


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
