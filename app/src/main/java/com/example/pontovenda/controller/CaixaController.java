package com.example.pontovenda.controller;

import android.content.Context;

import com.example.pontovenda.dao.CaixaDao;
import com.example.pontovenda.model.Caixa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CaixaController {

    private Context context;

    public CaixaController(Context context) {
        this.context = context;
    }

    public String salvarCaixa(String dataAbertura, String dataFechamento) {
        try {
            if (dataAbertura == null || dataAbertura.isEmpty()) {
                return "Informe a Data de Abertura";
            }

            if (dataFechamento == null || dataFechamento.isEmpty()) {
                return "Informe a Data de Fechamento";
            }

            if (comparaDatas(dataAbertura, dataFechamento) > 0) {
                return "Data Fechamento maior que a Data de Abertura";
            }

            Caixa caixa = new Caixa();
            caixa.setDataAbertura(dataAbertura);
            caixa.setDataFechacmento(dataFechamento);

            CaixaDao.getInstancia(context).insert(caixa);

        } catch (Exception ex) {
            return "Erro ao salvar o Caixa.";
        }

        return null;
    }

    public ArrayList<Caixa> retornaTodosCaixas() {
        return CaixaDao.getInstancia(context).getAll();
    }

    private int comparaDatas(String dataAber, String dataFech) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dataAbertura = format.parse(dataAber);
            Date dataFechamento = format.parse(dataFech);

            return dataAbertura.compareTo(dataFechamento);
        } catch (ParseException ex) {
            return 0;
        }
    }
}
