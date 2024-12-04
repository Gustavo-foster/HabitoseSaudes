package com.example.habitossaude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.habitossaude.model.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivityCadastro extends AppCompatActivity {

    private EditText nomeEditText;
    private EditText apelidoEditText;
    private EditText sexoEditText;
    private EditText emailEditText;
    private EditText senhaEditText;
    private EditText dataNascimentoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro);

        nomeEditText = findViewById(R.id.NomeCadastro);
        apelidoEditText = findViewById(R.id.ApelidoCadastro);
        sexoEditText = findViewById(R.id.SexoCadastro);
        emailEditText = findViewById(R.id.EmailCadastro);
        senhaEditText = findViewById(R.id.SenhaCadastro);
        dataNascimentoEditText = findViewById(R.id.DtNascimento);

        // Adicionando a funcionalidade ao botão "Criar Conta"
        TextView gravarCadastroButton = findViewById(R.id.CriarConta);
        gravarCadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areFieldsValid()) {
                    Usuario usuario = createUsuarioFromInput();
                    if (usuario != null) {
                        saveUsuarioToDatabase(usuario);
                    }
                }
            }
        });
    }

    private boolean areFieldsValid() {
        if (nomeEditText.getText().toString().isEmpty() ||
                apelidoEditText.getText().toString().isEmpty() ||
                sexoEditText.getText().toString().isEmpty() ||
                emailEditText.getText().toString().isEmpty() ||
                senhaEditText.getText().toString().isEmpty() ||
                dataNascimentoEditText.getText().toString().isEmpty()) {
            Toast.makeText(MainActivityCadastro.this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private Usuario createUsuarioFromInput() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento;
        try {
            dataNascimento = sdf.parse(dataNascimentoEditText.getText().toString());
        } catch (ParseException e) {
            Toast.makeText(MainActivityCadastro.this, "Data inválida. Use o formato dd/MM/yyyy.", Toast.LENGTH_SHORT).show();
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nomeEditText.getText().toString());
        usuario.setApelido(apelidoEditText.getText().toString());
        usuario.setSexo(sexoEditText.getText().toString());
        usuario.setEmail(emailEditText.getText().toString());
        usuario.setSenha(senhaEditText.getText().toString());
        usuario.setDataNascimento(dataNascimento);
        return usuario;
    }

    private void saveUsuarioToDatabase(Usuario usuario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            db.usuarioDao().inserirUsuario(usuario);

            runOnUiThread(() -> {
                Toast.makeText(MainActivityCadastro.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivityCadastro.this, MainActivityLogin.class);
                startActivity(intent);
            });
        });
    }
}
