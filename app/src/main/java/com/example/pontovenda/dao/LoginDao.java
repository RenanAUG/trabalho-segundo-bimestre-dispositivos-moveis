package com.example.pontovenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pontovenda.helper.SQLiteDataHelper;
import com.example.pontovenda.model.Login;

import java.util.ArrayList;

public class LoginDao implements IGenericDao<Login> {

    private SQLiteOpenHelper sqLiteOpenHelper;

    private SQLiteDatabase baseDados;

    private String[]colunas = {"id", "login", "senha"};

    private String tabela = "login";

    private Context context;

    private static LoginDao instancia;

    public static LoginDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new LoginDao(context);
        } else {
            return instancia;
        }
    }

    private LoginDao(Context context) {
        this.context = context;

        sqLiteOpenHelper = new SQLiteDataHelper(this.context,
                "TRABALHO_SEG_BIMESTRE", null, 1);

        baseDados =sqLiteOpenHelper.getWritableDatabase();
    }

    @Override
    public long insert(Login obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getLogin());
            valores.put(colunas[2], obj.getSenha());

            return baseDados.insert(tabela, null, valores);

        } catch (SQLException sqlException) {
            Log.e("PONTO VENDA", "ERRO: LoginDao.insert()" + sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Login obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getLogin());
            valores.put(colunas[2], obj.getSenha());

            String[]identificador = {String.valueOf(obj.getId())};

            baseDados.update(tabela, valores,
                    colunas[0]+"= ?", identificador);
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: LoginDao.update()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Login obj) {
        try {
            String[]identificador = {String.valueOf(obj.getId())};

            baseDados.delete(tabela,
                    colunas[0]+"= ?", identificador);
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: LoginDao.delete()" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Login> getAll() {
        ArrayList<Login> listalogin = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela, colunas,
                    null, null, null, null, colunas[0]+" desc");

            if (cursor.moveToFirst()) {
                do {

                    Login loginObj = new Login();
                    loginObj.setLogin(cursor.getString(1));
                    loginObj.setSenha(cursor.getString(2));

                    listalogin.add(loginObj);
                }while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: LoginDao.getAll()" + ex.getMessage());
        }
        return listalogin;
    }

    @Override
    public Login getById(int id) {
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador, null, null, null);

            if (cursor.moveToFirst()) {

                Login loginObj = new Login();
                loginObj.setId(cursor.getInt(0));
                loginObj.setLogin(cursor.getString(1));
                loginObj.setSenha(cursor.getString(2));

                return loginObj;
            }

        } catch (SQLException ex) {
            Log.e("PONTO VENDA", "ERRO: LoginDao.getById()" + ex.getMessage());
        }
        return null;
    }
}
