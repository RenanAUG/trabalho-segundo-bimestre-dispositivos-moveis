package com.example.pontovenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pontovenda.R;

public class MenuActivity extends AppCompatActivity {

    private Button btSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btSair = findViewById(R.id.btSair);

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

    public void abrirTelaPagamentos(View view) {
        Intent intent = new Intent(MenuActivity.this,
                PagamentoActivity.class);

        startActivity(intent);
    }

    public void abrirTelaCaixa(View view) {
        Intent intent = new Intent(MenuActivity.this,
                CaixaActivity.class);

        startActivity(intent);
    }

    public void abrirTelaPedidos(View view) {
        Intent intent = new Intent(MenuActivity.this,
                PedidoActivity.class);

        startActivity(intent);
    }
}