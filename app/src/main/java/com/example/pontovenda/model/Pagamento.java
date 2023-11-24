package com.example.pontovenda.model;

import java.util.Date;

public class Pagamento {

    private int idPagamento;
    private double valorPagamento;
    private String dataPagamento;
    private int codPedido;

    public Pagamento() {}

    public Pagamento(int idPagamento, double valorPagamento, String dataPagamento, int codPedido) {
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

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }
}
