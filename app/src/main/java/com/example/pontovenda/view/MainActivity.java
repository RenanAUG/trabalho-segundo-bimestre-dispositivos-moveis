package com.example.pontovenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pontovenda.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirTelaPagamentos(View view) {
        Intent intent = new Intent(MainActivity.this,
                PagamentoActivity.class);

        startActivity(intent);
    }

    public void abrirTelaCaixa(View view) {
        Intent intent = new Intent(MainActivity.this,
                CaixaActivity.class);

        startActivity(intent);
    }
}