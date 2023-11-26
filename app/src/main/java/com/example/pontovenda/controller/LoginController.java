package com.example.pontovenda.controller;

import android.content.Context;
import android.util.Log;

import com.example.pontovenda.dao.LoginDao;
import com.example.pontovenda.dao.PagamentoDao;
import com.example.pontovenda.model.Login;
import com.example.pontovenda.model.Pagamento;

import java.util.ArrayList;

public class LoginController {

    private Context context;

    public LoginController(Context context) {
        this.context = context;
    }

    public String salvarLogin(String login, String senha){
        try{
            if(login.equals(" ") || login.isEmpty()){
                return "Preencha o campo de login!";
            }
            if(senha.equals(" ") || senha.isEmpty()){
                return "Preencha o campo de senha!";
            }

            Login loginObj = new Login();
            loginObj.setLogin(login);
            loginObj.setSenha(senha);

            LoginDao.getInstancia(context).insert(loginObj);
        }catch(Exception e){
            return "Erro ao salvar conta";
        }
        return null;
    }

    public ArrayList<Login> retornaTodosLogins() {
        return LoginDao.getInstancia(context).getAll();
    }
}
