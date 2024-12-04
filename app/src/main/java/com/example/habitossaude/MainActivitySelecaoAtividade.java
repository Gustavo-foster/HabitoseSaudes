package com.example.habitossaude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivitySelecaoAtividade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_selecao_atividade);

        // Ajuste de padding baseado nos Insets do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuração de navegação
        configurarNavegacao();

        // Configurar botão Logoff
        configurarLogoff();
    }

    private void configurarNavegacao() {
        // Ações para os ícones de atividades
        configurarIcone(R.id.imageWater, MainActivityRegistroAtividadeAgua.class);
        configurarIcone(R.id.imageLazer, MainActivityRegistroAtividadeLazer.class);
        configurarIcone(R.id.imageRedeSocial, MainActivityRegistroAtividadeRedeSocial.class);
        configurarIcone(R.id.imageDormir, MainActivityRegistroAtividadeDormir.class);
        configurarIcone(R.id.imageLer, MainActivityRegistroAtividadeLer.class);
        configurarIcone(R.id.imageTreinar, MainActivityRegistroAtividadeTreinar.class);
        configurarIcone(R.id.imageEstudo, MainActivityRegistroAtividadeEstudar.class);
        configurarIcone(R.id.imageTrabalho, MainActivityRegistroAtividadeTrabalho.class);
        configurarIcone(R.id.imageAlimentacao, MainActivityRegistroAtividadeAlimentacao.class);

        // Navegação inferior
        configurarIcone(R.id.imageHome, MainActivitySelecaoAtividade.class);
        configurarIcone(R.id.imageActivities, MainActivityCadastroMetas.class);
        configurarIcone(R.id.imageProfile, MainActivityRegistroAtividadeAgua.class);
    }

    private void configurarIcone(int imageViewId, Class<?> destino) {
        ImageView icone = findViewById(imageViewId);
        icone.setOnClickListener(v -> abrirTela(destino));
    }

    private void abrirTela(Class<?> destino) {
        Intent intent = new Intent(MainActivitySelecaoAtividade.this, destino);
        startActivity(intent);
    }

    private void configurarLogoff() {
        Button logoffButton = findViewById(R.id.Logoff);
        logoffButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivitySelecaoAtividade.this, MainActivityTelaInicio.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Limpa a pilha de atividades
            startActivity(intent);
        });
    }
}
