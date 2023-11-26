package com.example.pontovenda.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
        ArrayList<Login> listaLogins = retornaTodosLogins();
        try{
            if(login.equals(" ") || login.isEmpty()){
                return "loginVazio";
            }
            if(senha.equals(" ") || senha.isEmpty()){
                return "senhaVazio";
            }
            for(Login l : listaLogins){
                if(login.equals(l.getLogin())){
                    return "loginExistente";
                }
            }

            Login loginObj = new Login();
            loginObj.setLogin(login);
            loginObj.setSenha(senha);

            LoginDao.getInstancia(context).insert(loginObj);
            return "sucesso";
        }catch(Exception e){
            return e.toString();
        }
    }

    public String efetuaLogin(String login, String senha){
        ArrayList<Login> listaLogins = retornaTodosLogins();
        for(Login l : listaLogins){
            if(login.equals(l.getLogin()) && senha.equals(l.getSenha())){
                Log.e("teste", "encontrado");
                return "sucesso";
            }
        }
        return "não encontrado";
        }



    public ArrayList<Login> retornaTodosLogins() {
        return LoginDao.getInstancia(context).getAll();
    }
}
