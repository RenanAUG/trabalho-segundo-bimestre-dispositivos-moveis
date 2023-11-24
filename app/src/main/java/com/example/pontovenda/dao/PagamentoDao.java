package com.example.pontovenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pontovenda.helper.SQLiteDataHelper;
import com.example.pontovenda.model.Pagamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PagamentoDao implements IGenericDao<Pagamento>{

    private SQLiteOpenHelper sqLiteOpenHelper;

    private SQLiteDatabase baseDados;

    private String[]colunas = {"id", "valor", "data_pagamento", "cod_pedido"};

    private String tabela = "pagamento";

    private Context context;

    private static PagamentoDao instancia;

    //TODO Variável para pegar a data do banco, pois não tem como passar direto a data para lista.
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    Date data = null;

    public static PagamentoDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new PagamentoDao(context);
        } else {
            return instancia;
        }
    }

    private PagamentoDao(Context context) {
        this.context = context;

        sqLiteOpenHelper = new SQLiteDataHelper(this.context,
                "TRABALHO_SEG_BIMESTRE", null, 1);

        baseDados =sqLiteOpenHelper.getWritableDatabase();
    }

    @Override
    public long insert(Pagamento obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getValorPagamento());
            valores.put(colunas[2], obj.getDataPagamento());
            valores.put(colunas[3], obj.getCodPedido());

            return baseDados.insert(tabela, null, valores);

        } catch (SQLException sqlException) {
            Log.e("PONTO VENDA", "ERRO: PagamentoDao.insert()" + sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Pagamento obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getValorPagamento());
            valores.put(colunas[2], obj.getDataPagamento());
            valores.put(colunas[3], obj.getCodPedido());

            String[]identificador = {String.valueOf(obj.getIdPagamento())};

            baseDados.update(tabela, valores,
                    colunas[0]+"= ?", identificador);
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: PagamentoDao.update()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Pagamento obj) {
        try {
            String[]identificador = {String.valueOf(obj.getIdPagamento())};

            baseDados.delete(tabela,
                    colunas[0]+"= ?", identificador);
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: PagamentoDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Pagamento> getAll() {
        ArrayList<Pagamento> listaPagamento = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela, colunas,
                    null, null, null, null, colunas[0]+" desc");

            if (cursor.moveToFirst()) {
                do {

                    Pagamento pagamento = new Pagamento();
                    pagamento.setIdPagamento(cursor.getInt(0));
                    pagamento.setValorPagamento(cursor.getDouble(1));
                    pagamento.setDataPagamento(cursor.getString(2));
                    pagamento.setCodPedido(cursor.getInt(3));

                    listaPagamento.add(pagamento);
                }while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: PagamentoDao.getAll()" + ex.getMessage());
        }
        return listaPagamento;
    }

    @Override
    public Pagamento getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador, null, null, null);

            if (cursor.moveToFirst()) {

                Pagamento pagamento = new Pagamento();
                pagamento.setIdPagamento(cursor.getInt(0));
                pagamento.setValorPagamento(cursor.getDouble(1));
                pagamento.setDataPagamento(cursor.getString(2));
                pagamento.setCodPedido(cursor.getInt(3));

                return pagamento;
            }

        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: PagamentoDao.getById()" + ex.getMessage());
        }
        return null;
    }
}
