package com.example.habitossaude;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityTelaInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Definindo a lógica de padding com base nos systemBars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Usando Handler para aguardar 5 segundos e mudar para a próxima tela
        new Handler().postDelayed(() -> {
            // Intent para a próxima atividade
            Intent intent = new Intent(MainActivityTelaInicio.this, MainActivityLogin.class);
            startActivity(intent);
            finish(); // Opcional, para garantir que a tela atual seja fechada
        }, 5000); // 5000 milissegundos = 5 segundos
    }
}
