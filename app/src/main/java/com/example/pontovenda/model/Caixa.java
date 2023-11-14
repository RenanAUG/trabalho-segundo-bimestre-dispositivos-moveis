package com.example.pontovenda.model;

import java.util.Date;

public class Caixa {

    private int idCaixa;

    private Date dataAbertura;

    private Date dataFechacmento;

    public Caixa() {}

    public Caixa(int idCaixa, Date dataAbertura, Date dataFechacmento) {
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

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Date getDataFechacmento() {
        return dataFechacmento;
    }

    public void setDataFechacmento(Date dataFechacmento) {
        this.dataFechacmento = dataFechacmento;
    }
}
