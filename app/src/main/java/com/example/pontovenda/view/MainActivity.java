package com.example.pontovenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pontovenda.R;
import com.example.pontovenda.dao.CaixaDao;
import com.example.pontovenda.dao.PagamentoDao;
import com.example.pontovenda.dao.PedidoDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PagamentoDao.getInstancia(this);
        CaixaDao.getInstancia(this);
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

    public void abrirTelaPedidos(View view) {
        Intent intent = new Intent(MainActivity.this,
                PedidoActivity.class);

        startActivity(intent);
    }

    public void abrirLoginCadastro(View view){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);

        startActivity(intent);
    }
}