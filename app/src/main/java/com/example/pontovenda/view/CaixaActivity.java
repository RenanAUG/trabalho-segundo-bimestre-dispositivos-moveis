package com.example.pontovenda.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pontovenda.R;
import com.example.pontovenda.adapter.CaixaListAdapter;
import com.example.pontovenda.controller.CaixaController;
import com.example.pontovenda.model.Caixa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CaixaActivity extends AppCompatActivity {

    private AlertDialog alertDialog;

    private CaixaController caixaController;

    private FloatingActionButton btFechamentoAberturaCaixa;

    private View viewAlert;

    private EditText edDataAbertura;

    private EditText edDataFechamento;

    private RecyclerView rvCaixa;

    private Button btVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caixa);

        caixaController = new CaixaController(this);
        rvCaixa = findViewById(R.id.rvCaixa);
        btFechamentoAberturaCaixa = findViewById(R.id.btFechamentoAberturaCaixa);
        btVoltar = findViewById(R.id.btVoltar);

        btFechamentoAberturaCaixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFechamentoAberturaCaixa();
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaixaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        atualizarListaCaixa();
    }

    public void abrirFechamentoAberturaCaixa() {
        viewAlert = getLayoutInflater().inflate(
                R.layout.dialog_caixa, null
        );

        edDataAbertura = viewAlert.findViewById(R.id.edDataAbertura);
        edDataFechamento = viewAlert.findViewById(R.id.edDataFechamento);

        edDataAbertura.setEnabled(false);
        edDataAbertura.setText(new SimpleDateFormat("dd/MM/yyyy").format(System.currentTimeMillis()));

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Abertura/Fechamento de Caixa");
        builder.setView(viewAlert);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        builder.setPositiveButton("Salvar", null);
        alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button bt = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salvarDados();
                        atualizarListaCaixa();
                    }
                });
            }
        });
        alertDialog.show();
    }

    public void salvarDados() {
        String retorno = caixaController.salvarCaixa(
                edDataAbertura.getText().toString(), edDataFechamento.getText().toString()
        );

        if (retorno != null) {
            if (retorno.contains("Data Abertura")) {
                edDataAbertura.setError(retorno);
                edDataAbertura.requestFocus();
            }

            if (retorno.contains("Data Fechamento")) {
                edDataFechamento.setError(retorno);
                edDataFechamento.requestFocus();
            }

            Toast.makeText(this, retorno, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Caixa salvo com sucesso.", Toast.LENGTH_LONG).show();
            alertDialog.dismiss();
            atualizarListaCaixa();
        }
    }

    public void atualizarListaCaixa() {
        ArrayList<Caixa> caixas = caixaController.retornaTodosCaixas();
        CaixaListAdapter caixaListAdapter = new CaixaListAdapter(caixas, this);
        rvCaixa.setLayoutManager(new LinearLayoutManager(this));
        rvCaixa.setAdapter(caixaListAdapter);
    }
}
