package com.example.pontovenda.model;

import java.util.Date;

public class Pagamento {

    private int idPagamento;
    private double valorPagamento;
    private Date  dataPagamento;
    private int codPedido;

    public Pagamento() {}

    public Pagamento(int idPagamento, double valorPagamento, Date dataPagamento, int codPedido) {
        this.idPagamento = idPagamento;
        this.valorPagamento = valorPagamento;
        this.dataPagamento = dataPagamento;
        this.codPedido = codPedido;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }
}
