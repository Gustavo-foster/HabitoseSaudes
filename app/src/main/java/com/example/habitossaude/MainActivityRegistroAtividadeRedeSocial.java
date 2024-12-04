package com.example.habitossaude;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityRegistroAtividadeRedeSocial extends AppCompatActivity {

    private EditText registroQuantidadeRedeSocial;
    private EditText metaDiariaRedeSocial;
    private TextView resultadoTextView;
    private Button salvarRegistroRedeSocial;
    private Button limparRegistroRedeSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilita o modo Edge to Edge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_registro_atividade_rede_social);

        // Inicializar os componentes
        registroQuantidadeRedeSocial = findViewById(R.id.RegistroQuantidade_rede_social);
        metaDiariaRedeSocial = findViewById(R.id.MetaDiaria_rede_social);
        resultadoTextView = findViewById(R.id.textView);
        salvarRegistroRedeSocial = findViewById(R.id.SalvarRegistro_rede_social);
        limparRegistroRedeSocial = findViewById(R.id.LimpaRegistroRedeSocial);

        // Aplica os insets do sistema para ajuste correto
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuração das imagens de navegação
        ImageView imageHome = findViewById(R.id.imageHome);
        ImageView imageActivities = findViewById(R.id.imageActivities);
        ImageView imageProfile = findViewById(R.id.imageProfile);

        // Ação para a tela de Início
        imageHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(MainActivityRegistroAtividadeRedeSocial.this, MainActivitySelecaoAtividade.class);
            startActivity(intentHome);
        });

        // Ação para a tela de Atividades
        imageActivities.setOnClickListener(v -> {
            Intent intentActivities = new Intent(MainActivityRegistroAtividadeRedeSocial.this, MainActivityCadastroMetas.class);
            startActivity(intentActivities);
        });

        // Ação para a tela de Perfil
        imageProfile.setOnClickListener(v -> {
            Intent intentProfile = new Intent(MainActivityRegistroAtividadeRedeSocial.this, MainActivityRegistroAtividadeTreinar.class);
            startActivity(intentProfile);
        });

        // Ação para o botão "Calcular"
        salvarRegistroRedeSocial.setOnClickListener(v -> calcularMeta());

        // Ação para o botão "Limpar Registro"
        limparRegistroRedeSocial.setOnClickListener(v -> limparCampos());
    }

    // Método para calcular a diferença entre a meta e o tempo registrado
    private void calcularMeta() {
        try {
            // Obter os valores inseridos
            int minutosRedeSocial = Integer.parseInt(registroQuantidadeRedeSocial.getText().toString());
            int metaDiaria = Integer.parseInt(metaDiariaRedeSocial.getText().toString());

            // Calcular a diferença
            int resultado = metaDiaria - minutosRedeSocial;

            // Exibir a mensagem conforme o resultado
            if (resultado <= 0) {
                resultadoTextView.setText("Parabéns! Você bateu a meta de hoje!");
            } else {
                resultadoTextView.setText("Faltam " + resultado + " minutos para atingir a meta.");
            }
        } catch (NumberFormatException e) {
            // Caso os campos não estejam preenchidos corretamente
            Toast.makeText(this, "Por favor, insira valores válidos.", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para limpar os campos de entrada e o resultado
    private void limparCampos() {
        registroQuantidadeRedeSocial.setText("");
        metaDiariaRedeSocial.setText("");
        resultadoTextView.setText("");

        // Exibe uma mensagem de confirmação
        Toast.makeText(this, "Campos limpos com sucesso.", Toast.LENGTH_SHORT).show();
    }
}
