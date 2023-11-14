package com.example.pontovenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pontovenda.helper.SQLiteDataHelper;
import com.example.pontovenda.model.Caixa;
import com.example.pontovenda.model.Pagamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CaixaDao implements IGenericDao<Caixa> {

    private SQLiteOpenHelper sqLiteOpenHelper;

    private SQLiteDatabase baseDados;

    private String[]colunas = {"id", "data_abertura", "data_fechamento"};

    private String tabela = "caixa";

    private Context context;

    private static CaixaDao instancia;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    Date dataAbertura = null;
    Date dataFechamento = null;

    public static CaixaDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new CaixaDao(context);
        } else {
            return instancia;
        }
    }

    private CaixaDao(Context context) {
        this.context = context;

        sqLiteOpenHelper = new SQLiteDataHelper(this.context,
                "TRABALHO_SEG_BIMESTRE", null, 1);

        baseDados =sqLiteOpenHelper.getWritableDatabase();
    }

    @Override
    public long insert(Caixa obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getIdCaixa());
            valores.put(colunas[1], obj.getDataAbertura().getTime());
            valores.put(colunas[2], obj.getDataFechacmento().getTime());

            return baseDados.insert(tabela, null, valores);

        } catch (SQLException sqlException) {
            Log.e("PONTO VENDA", "ERRO: CaixaDao.insert()" + sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Caixa obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getDataAbertura().getTime());
            valores.put(colunas[2], obj.getDataFechacmento().getTime());

            String[]identificador = {String.valueOf(obj.getIdCaixa())};

            baseDados.update(tabela, valores,
                    colunas[0]+"= ?", identificador);
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: CaixaDao.update()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Caixa obj) {
        try {
            String[]identificador = {String.valueOf(obj.getIdCaixa())};

            baseDados.delete(tabela,
                    colunas[0]+"= ?", identificador);
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: CaixaDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Caixa> getAll() {
        ArrayList<Caixa> listaCaixa = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela, colunas,
                    null, null, null, null, colunas[0]+"desc");

            if (cursor.moveToFirst()) {
                do {
                    String dataAberturaString = cursor.getString(1);
                    String dataFechamentoString = cursor.getString(2);
                    try {
                        dataAbertura = format.parse(dataAberturaString);
                        dataFechamento = format.parse(dataFechamentoString);
                    }catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    Caixa caixa = new Caixa();
                    caixa.setIdCaixa(cursor.getInt(0));
                    caixa.setDataAbertura(dataAbertura);
                    caixa.setDataFechacmento(dataFechamento);

                    listaCaixa.add(caixa);
                }while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: CaixaDao.getAll()" + ex.getMessage());
        }
        return listaCaixa;
    }

    @Override
    public Caixa getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador, null, null, null);

            if (cursor.moveToFirst()) {
                String dataAberturaString = cursor.getString(1);
                String dataFechamentoString = cursor.getString(2);
                try {
                    dataAbertura = format.parse(dataAberturaString);
                    dataFechamento = format.parse(dataFechamentoString);
                }catch (ParseException ex) {
                    ex.printStackTrace();
                }

                Caixa caixa = new Caixa();
                caixa.setIdCaixa(cursor.getInt(0));
                caixa.setDataAbertura(dataAbertura);
                caixa.setDataFechacmento(dataFechamento);

                return caixa;
            }

        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: CaixaDao.getById()" + ex.getMessage());
        }
        return null;
    }
}
