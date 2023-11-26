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
import com.example.pontovenda.model.Pedido;

import java.util.ArrayList;

public class PedidoDao implements IGenericDao<Pedido>{

    private SQLiteOpenHelper sqLiteOpenHelper;

    private SQLiteDatabase baseDados;

    private String[]colunas = {"id", "nome", "quantidade", "valor"};

    private String tabela = "pedido";

    private Context context;

    private static PedidoDao instancia;

    public static PedidoDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new PedidoDao(context);
        } else {
            return instancia;
        }
    }

    private PedidoDao(Context context) {
        this.context = context;

        sqLiteOpenHelper = new SQLiteDataHelper(this.context,
                "TRABALHO_SEG_BIMESTRE", null, 1);

        baseDados =sqLiteOpenHelper.getWritableDatabase();
    }

    @Override
    public long insert(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getIdPedido());
            valores.put(colunas[1], obj.getNome());
            valores.put(colunas[2], obj.getQuantidade());
            valores.put(colunas[3], obj.getValorTotal());

            return baseDados.insert(tabela, null, valores);

        } catch (SQLException sqlException) {
            Log.e("PONTO VENDA", "ERRO: PedidoDao.insert()" + sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getNome());
            valores.put(colunas[2], obj.getQuantidade());
            valores.put(colunas[3], obj.getValorTotal());

            String[]identificador = {String.valueOf(obj.getIdPedido())};

            baseDados.update(tabela, valores,
                    colunas[0]+"= ?", identificador);
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: PedidoDao.update()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Pedido obj) {
        try {
            String[]identificador = {String.valueOf(obj.getIdPedido())};

            baseDados.delete(tabela,
                    colunas[0]+"= ?", identificador);
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: PedidoDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Pedido> getAll() {
        ArrayList<Pedido> listaPedido = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela, colunas,
                    null, null, null, null, colunas[0]+" desc");

            if (cursor.moveToFirst()) {
                do {

                    Pedido pedidoObj = new Pedido();
                    pedidoObj.setIdPedido(cursor.getInt(0));
                    pedidoObj.setNome(cursor.getString(1));
                    pedidoObj.setQuantidade(cursor.getInt(2));
                    pedidoObj.setValorTotal(cursor.getDouble(3));

                    listaPedido.add(pedidoObj);
                }while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: PedidoDao.getAll()" + ex.getMessage());
        }
        return listaPedido;
    }

    @Override
    public Pedido getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador, null, null, null);

            if (cursor.moveToFirst()) {

                Pedido pedidoObj = new Pedido();
                pedidoObj.setIdPedido(cursor.getInt(0));
                pedidoObj.setNome(cursor.getString(1));
                pedidoObj.setQuantidade(cursor.getInt(2));
                pedidoObj.setValorTotal(cursor.getDouble(3));

                return pedidoObj;
            }

        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: PedidoDao.getById()" + ex.getMessage());
        }
        return null;
    }
}
