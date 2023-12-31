package com.example.pontovenda.controller;

import android.content.Context;
import android.util.Log;

import com.example.pontovenda.dao.PagamentoDao;
import com.example.pontovenda.model.Pagamento;

import java.sql.Array;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class PagamentoController {

    private Context context;

    public PagamentoController(Context context) {
        this.context = context;
    }

    public String salvarPagamento(String codPedido, String valor, String dataPagamento) {
        try {
            if (codPedido == null || codPedido.isEmpty() || codPedido.equals("0")) {
                return "Informe o Código do Pedido";
            }
            if (valor.equals("0") || valor.isEmpty()) {
                return "Informe o Valor a ser pago";
            }

            Pagamento pagamento = new Pagamento();
            pagamento.setCodPedido(Integer.parseInt(codPedido));
            pagamento.setValorPagamento(Double.parseDouble(valor));
            pagamento.setDataPagamento(dataPagamento);

            PagamentoDao.getInstancia(context).insert(pagamento);

        } catch (Exception ex) {
            return "Erro ao salvar o Pagamento.";
        }

        return null;
    }

    public ArrayList<Pagamento> retornaTodosPagamentos() {
        return PagamentoDao.getInstancia(context).getAll();
    }
}
