package com.example.pontovenda.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pontovenda.R;
import com.example.pontovenda.adapter.PagamentoListAdapter;
import com.example.pontovenda.adapter.PedidoListAdapter;
import com.example.pontovenda.controller.LoginController;
import com.example.pontovenda.controller.PedidoController;
import com.example.pontovenda.model.Pagamento;
import com.example.pontovenda.model.Pedido;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class PedidoActivity extends AppCompatActivity {

    private PedidoController pedidoController;
    private AlertDialog alertDialog;

    private View viewAlert;
    private Button btVoltar;
    private FloatingActionButton btCadastroPedido;
    private RecyclerView rvPedidos;

    private EditText edNome,edQuantidade,edValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        pedidoController = new PedidoController(this);

        btVoltar = findViewById(R.id.btVoltar);
        btCadastroPedido = findViewById(R.id.btCadastroPedido);
        rvPedidos = findViewById(R.id.rvPedidos);

        btCadastroPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PedidoActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        atualizarListaPedido();
    }

    public void abrirCadastro(){
        viewAlert = getLayoutInflater().inflate(
                R.layout.dialog_pedidos, null
        );

        edNome = viewAlert.findViewById(R.id.edNome);
        edQuantidade = viewAlert.findViewById(R.id.edQuantidade);
        edValor = viewAlert.findViewById(R.id.edValor);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("NOVO PEDIDO");
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

        //Adicionando ação ao botão salvar após criação da tela.
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button bt = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salvarDadosPedido();
                        atualizarListaPedido();
                    }
                });
            }
        });
        alertDialog.show();
    }

    public String salvarDadosPedido(){
        String retorno = pedidoController.salvarPedido(edNome.getText().toString(),
                Integer.parseInt(edQuantidade.getText().toString()),
                Double.parseDouble(edValor.getText().toString()));

        if(retorno.equals("nomeVazio")){
            Toast.makeText(this,"Preencha o campo de nome de forma válida!", Toast.LENGTH_LONG).show();
            edNome.setError(retorno);
            edNome.requestFocus();
        }
        else if(retorno.equals("quantidadeVazio")){
            Toast.makeText(this,"Preencha o campo de quantidade de forma válida!", Toast.LENGTH_LONG).show();
            edQuantidade.setError(retorno);
            edQuantidade.requestFocus();
        }
        else if(retorno.equals("valorVazio")){
            Toast.makeText(this,"Preencha o campo de valor de forma válida!", Toast.LENGTH_LONG).show();
            edValor.setError(retorno);
            edValor.requestFocus();
        }
        else if(retorno.equals("sucesso")){
            Toast.makeText(this,"Pedido: " + pedidoController.getIdPedidoGerado() + " salvo com sucesso!", Toast.LENGTH_LONG).show();
            edNome.setText("");
            edQuantidade.setText("");
            edValor.setText("");
        }else{
            Toast.makeText(this,retorno, Toast.LENGTH_LONG).show();
        }
        return null;
    }

    public void atualizarListaPedido(){
        ArrayList<Pedido> listaPedido = pedidoController.retornaTodosPedidos();
        PedidoListAdapter adapter = new PedidoListAdapter(listaPedido,this);
        rvPedidos.setLayoutManager(new LinearLayoutManager(this));
        rvPedidos.setAdapter(adapter);
    }

}
