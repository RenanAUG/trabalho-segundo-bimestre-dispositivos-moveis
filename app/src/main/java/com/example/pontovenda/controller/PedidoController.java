package com.example.pontovenda.controller;

import android.content.Context;

import com.example.pontovenda.dao.PedidoDao;
import com.example.pontovenda.model.Pedido;

import java.util.ArrayList;
import java.util.Random;

public class PedidoController {

    private Context context;

    public PedidoController(Context context) {
        this.context = context;
    }

    public String salvarPedido(String nome, int quantidade, double valor){
        Random random = new Random();
        if(nome.equals(" ") || nome.isEmpty()){
            return "nomeVazio";
        }
        if(quantidade == 0){
            return "quantidadeVazio";
        }
        if(valor == 0){
            return "valorVazio";
        }

        Pedido pedidoObj = new Pedido();
        pedidoObj.setIdPedido(random.nextInt(1000000) + 1);
        pedidoObj.setNome(nome);
        pedidoObj.setQuantidade(quantidade);
        pedidoObj.setValorTotal(valor);

        return null;
    }

    public ArrayList<Pedido> retornaTodosPedidos(){
        return PedidoDao.getInstancia(context).getAll();
    }
}
