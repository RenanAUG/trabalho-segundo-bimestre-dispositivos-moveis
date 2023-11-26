package com.example.pontovenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pontovenda.R;
import com.example.pontovenda.controller.LoginController;
import com.example.pontovenda.dao.CaixaDao;
import com.example.pontovenda.dao.PagamentoDao;
import com.example.pontovenda.dao.PedidoDao;

public class MainActivity extends AppCompatActivity {

    private LoginController loginController;
    private EditText edLogin;
    private EditText edSenha;
    private Button btEntrar, btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Intent intent = new Intent(MainActivity.this,
                MenuActivity.class);

        startActivity(intent);
    }

    public void cadastrarLogin() {
        String retorno = loginController.salvarLogin(edLogin.getText().toString(), edSenha.getText().toString());

        if (retorno.equals("loginVazio")) {
            Toast.makeText(this, "Preencha o campo de login", Toast.LENGTH_LONG).show();
        } else if (retorno.equals("senhaVazio")) {
            Toast.makeText(this, "Preencha o campo de senha", Toast.LENGTH_LONG).show();
        } else if (retorno.equals("loginExistente")) {
            Toast.makeText(this, "Conta já existente no sistema!", Toast.LENGTH_LONG).show();
        } else if (retorno.equals("sucesso")) {
            Toast.makeText(this, "Sucesso ao cadastrar", Toast.LENGTH_LONG).show();
            acessarMenu();
        } else {
            Toast.makeText(this, retorno, Toast.LENGTH_LONG).show();
        }
    }

    public void efetuarLogin() {
        String resultado = loginController.efetuaLogin(edLogin.getText().toString(), edSenha.getText().toString());
        if (resultado == "sucesso") {
            Toast.makeText(this, "Sucesso ao logar", Toast.LENGTH_LONG).show();
            acessarMenu();
        } else {
            Toast.makeText(this, "Conta não encontrada no nosso sistema!", Toast.LENGTH_LONG).show();
        }
    }
}