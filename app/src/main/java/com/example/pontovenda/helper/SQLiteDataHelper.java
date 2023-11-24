package com.example.pontovenda.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }


    /***
     * Método é executado uma unica vez quando o aplicativo é instalado
     * é responsável por executar os scripts da criação das tabelas
     * @param sqLiteDatabase : base de dados que irá se criar as tabelas
     *
     * TABELA pedido O ID vai ser autoincrement
     * TABELA pagamento e caixa o ID vai ser autoincrement
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE pedido(id INTEGER, nome VARCHAR(30), quantidade INTEGER, valor NUMERIC(10,2))");
        sqLiteDatabase.execSQL("CREATE TABLE pagamento(id INTEGER PRIMARY KEY AUTOINCREMENT, valor NUMERIC(10,2), data_pagamento VARCHAR(10), cod_pedido INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE caixa(id INTEGER PRIMARY KEY AUTOINCREMENT, data_abertura DATE, data_fechamento DATE)");
        sqLiteDatabase.execSQL("CREATE TABLE login(id INTEGER PRIMARY KEY AUTOINCREMENT, login VARCHAR(20), senha VARCHAR(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // 3. Remova a tabela original
        sqLiteDatabase.execSQL("DROP TABLE pagamento");
    }
}
