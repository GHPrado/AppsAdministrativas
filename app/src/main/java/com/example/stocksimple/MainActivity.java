package com.example.stocksimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Stock(View view) {
        Intent i = new Intent(this, stock_principal.class);
        startActivity(i);
    }

    public void Precios(View view) {
        Intent i = new Intent(this, precios_principal.class);
        startActivity(i);
    }

    public void Base(View view) {
        Intent i = new Intent(this, baseDatos.class);
        startActivity(i);
    }
}
