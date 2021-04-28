package com.example.stocksimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class stock_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_principal);
    }

    public void Compras(View view) {
        Intent i = new Intent(this, stock_compras.class);
        startActivity(i);
    }

    public void Ventas(View view) {
        Intent i = new Intent(this, stock_ventas.class);
        startActivity(i);
    }

    public void Consulta(View view) {
        Intent i = new Intent(this, stock_consulta.class);
        startActivity(i);
    }

    public void Listado(View view) {
        Intent i = new Intent(this, stock_listado.class);
        startActivity(i);
    }
}
