package com.example.pontovenda.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pontovenda.R;
import com.example.pontovenda.controller.LoginController;
import com.example.pontovenda.model.Login;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private LoginController loginController;
    private EditText edLogin;
    private EditText edSenha;
    private Button btEntrar, btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginController = new LoginController(this);

        edLogin = findViewById(R.id.edLogin);
        edSenha = findViewById(R.id.edSenha);
        btEntrar = findViewById(R.id.btEntrar);
        btCadastrar = findViewById(R.id.btCadastrar);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                efetuarLogin();
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarLogin();
            }
        });
    }

    public void acessarMenu() {
        Intent intent = new Intent(LoginActivity.this,
                MainActivity.class);

        startActivity(intent);
    }

    public void cadastrarLogin(){
        String retorno = loginController.salvarLogin(edLogin.getText().toString(), edSenha.getText().toString());

        if(retorno == null){
            Log.e("teste", "sucesso!");
        }else{
            Log.e("teste", "erro!");
        }
    }

    public void efetuarLogin(){
        ArrayList<Login> listaLogins = loginController.retornaTodosLogins();
        for(Login l : listaLogins){
            if(edLogin.getText().toString().equals(l.getLogin()) &&
                    edSenha.getText().toString().equals(l.getSenha())){
                Log.e("teste", "encontrado");
            }
        }
    }
}
