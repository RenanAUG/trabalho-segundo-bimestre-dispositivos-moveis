package com.example.pontovenda.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pontovenda.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edLogin;
    private EditText edSenha;
    private Button btEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edLogin = findViewById(R.id.edLogin);
        edSenha = findViewById(R.id.edSenha);
        btEntrar = findViewById(R.id.btEntrar);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acessarMenu();
            }
        });
    }

    public void acessarMenu() {
        Intent intent = new Intent(LoginActivity.this,
                MainActivity.class);

        startActivity(intent);
    }
}
