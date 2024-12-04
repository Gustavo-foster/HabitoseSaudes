package com.example.habitossaude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.habitossaude.model.Usuario;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivityLogin extends AppCompatActivity {

    private EditText emailEditText;
    private EditText senhaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        emailEditText = findViewById(R.id.EmailCadastrado);
        senhaEditText = findViewById(R.id.SenhaCadastrada);

        findViewById(R.id.Acessar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String senha = senhaEditText.getText().toString();

                if (!email.isEmpty() && !senha.isEmpty()) {
                    login(email, senha);
                } else {
                    Toast.makeText(MainActivityLogin.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.Cadastre_se).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityLogin.this, MainActivityCadastro.class);
                startActivity(intent);
            }
        });
    }

    private void login(String email, String senha) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            Usuario usuario = db.usuarioDao().getUsuarioPorEmailESenha(email, senha);

            runOnUiThread(() -> {
                if (usuario != null) {
                    Toast.makeText(MainActivityLogin.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivityLogin.this, MainActivitySelecaoAtividade.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivityLogin.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
