package com.example.stocksimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class precios_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.precios_principal);
    }

    public void Alta(View view) {
        Intent i = new Intent(this, precios_alta.class);
        startActivity(i);
    }

    public void ConsultaPrecios(View view) {
        Intent i = new Intent(this, precios_consulta.class);
        startActivity(i);
    }

    public void ListadoPrecios(View view) {
        Intent i = new Intent(this, precios_listado.class);
        startActivity(i);
    }
}
