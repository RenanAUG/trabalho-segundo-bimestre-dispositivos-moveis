package com.example.pontovenda.model;

public class Pedido {

    private int idPedido;

    private String nome;

    private int quantidade;

    private double valorTotal;

    public Pedido() {}

    public Pedido(int idPedido, String nome, int quantidade, double valorTotal) {
        this.idPedido = idPedido;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
