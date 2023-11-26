package com.example.pontovenda.controller;

import android.content.Context;
import android.util.Log;

import com.example.pontovenda.dao.PedidoDao;
import com.example.pontovenda.model.Pedido;

import java.util.ArrayList;
import java.util.Random;

public class PedidoController {

    private Context context;
    private int pedidoId;

    public PedidoController(Context context) {
        this.context = context;
    }

    public String salvarPedido(String nome, int quantidade, double valor){
        Random random = new Random();
        try{
            if(nome.equals(" ") || nome.isEmpty()){
                return "nomeVazio";
            }
            if(quantidade <= 0){
                return "quantidadeVazio";
            }
            if(valor <= 0){
                return "valorVazio";
            }

            Pedido pedidoObj = new Pedido();
            int idAleatorio = random.nextInt(1000000) + 1;
            setIdPedidoGerado(idAleatorio);
            pedidoObj.setIdPedido(idAleatorio);
            pedidoObj.setNome(nome);
            pedidoObj.setQuantidade(quantidade);
            pedidoObj.setValorTotal(valor);
            Log.e("teste", String.valueOf(pedidoObj.getIdPedido()));

            PedidoDao.getInstancia(context).insert(pedidoObj);
            return "sucesso";
        }catch(Exception e){
            return e.toString();
        }
    }

    public ArrayList<Pedido> retornaTodosPedidos(){
        return PedidoDao.getInstancia(context).getAll();
    }

    public int getIdPedidoGerado(){
        return this.pedidoId;
    }

    public void setIdPedidoGerado(int id){
        this.pedidoId = id;
    }
}
