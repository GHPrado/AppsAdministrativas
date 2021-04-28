package com.example.stocksimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class baseDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_datos);
    }

    public void BaseStock(View view) {
        Intent i = new Intent(this, baseStock.class);
        startActivity(i);
    }

    public void BasePrecios(View view) {
        Intent i = new Intent(this, basePrecios.class);
        startActivity(i);
    }

}
