package com.example.pontovenda.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pontovenda.R;
import com.example.pontovenda.adapter.PagamentoListAdapter;
import com.example.pontovenda.controller.PagamentoController;
import com.example.pontovenda.model.Pagamento;

import java.util.ArrayList;

public class PagamentoActivity extends AppCompatActivity {

    private AlertDialog alertDialog;
    private PagamentoController pagamentoController;
    private EditText edCodPedido;
    private EditText edValor;
    private EditText edDataPagamento;
    private Button btCadastroPagamento;
    private RecyclerView rvPagamentos;
    private View viewAlert;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamentos);

        pagamentoController = new PagamentoController(this);
        rvPagamentos = findViewById(R.id.rvPagamentos);
        btCadastroPagamento = findViewById(R.id.btCadastroPagamento);

        btCadastroPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        atualizarListaPagamento();
    }

    public void abrirCadastro() {
        viewAlert = getLayoutInflater().inflate(
                R.layout.dialog_cadastro_pagamento, null
        );

        edCodPedido = viewAlert.findViewById(R.id.edCodPedido);
        edDataPagamento = viewAlert.findViewById(R.id.edDataPagamento);
        edValor = viewAlert.findViewById(R.id.edValor);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("NOVO PAGAMENTO"); //Adicionando título ao popup
        builder.setView(viewAlert); //Setando o layout
        builder.setCancelable(false); //não deixa fechar o popup se clicar fora dele

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        builder.setPositiveButton("Salvar", null);
        alertDialog = builder.create();

        //Adicionando ação ao botão salvar após criação da tela.
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
          @Override
          public void onShow(DialogInterface dialogInterface) {
              Button bt = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
              bt.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      salvarDados();
                  }
              });
          }
        });
        alertDialog.show();
    }

    public void salvarDados() {
        String retorno = pagamentoController.salvarPagamento(edCodPedido.getText().toString(),
                edValor.getText().toString(), edDataPagamento.getText().toString());

        if (retorno != null) {
            if(retorno.contains("Cod. Pedido")){
                edCodPedido.setError(retorno);
                edCodPedido.requestFocus();
            }
            if(retorno.contains("valor")){
                edValor.setError(retorno);
                edValor.requestFocus();
            }
            if (retorno.contains("data_pagamento")) {
                edDataPagamento.setError(retorno);
                edDataPagamento.requestFocus();
            }
        }else{
            Toast.makeText(this, "Pagamento Salvo com Sucesso!", Toast.LENGTH_LONG).show();
            alertDialog.dismiss();
            atualizarListaPagamento();
        }
    }

    public void atualizarListaPagamento() {
        ArrayList<Pagamento> listaPagamentos = pagamentoController.retornaTodosPagamentos();
        PagamentoListAdapter adapter = new PagamentoListAdapter(listaPagamentos, this);
        rvPagamentos.setLayoutManager(new LinearLayoutManager(this));
        rvPagamentos.setAdapter(adapter);
    }
}
