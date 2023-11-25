package com.example.pontovenda.model;

import java.util.Date;

public class Caixa {

    private int idCaixa;

    private String dataAbertura;

    private String dataFechacmento;

    public Caixa() {}

    public Caixa(int idCaixa, String dataAbertura, String dataFechacmento) {
        this.idCaixa = idCaixa;
        this.dataAbertura = dataAbertura;
        this.dataFechacmento = dataFechacmento;
    }

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDataFechacmento() {
        return dataFechacmento;
    }

    public void setDataFechacmento(String dataFechacmento) {
        this.dataFechacmento = dataFechacmento;
    }
}
