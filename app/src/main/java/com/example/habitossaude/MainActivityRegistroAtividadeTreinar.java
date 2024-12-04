package com.example.habitossaude;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivityRegistroAtividadeTreinar extends AppCompatActivity {

    private EditText registroQuantidadeTreinar;
    private EditText metaDiariaTreinar;
    private TextView resultadoTextView;
    private Button salvarRegistroTreinar;
    private Button limparRegistroTreinar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilita o modo Edge to Edge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_registro_atividade_treinar);

        // Inicializar os componentes
        registroQuantidadeTreinar = findViewById(R.id.RegistroQuantidadeTreinar);
        metaDiariaTreinar = findViewById(R.id.MetaDiariaTreinar);
        resultadoTextView = findViewById(R.id.textView);
        salvarRegistroTreinar = findViewById(R.id.SalvarRegistroTreinar);
        limparRegistroTreinar = findViewById(R.id.LimpaRegistroTreinar);

        // Aplica os insets do sistema para garantir o ajuste correto nas margens da tela
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
            Intent intentHome = new Intent(MainActivityRegistroAtividadeTreinar.this, MainActivitySelecaoAtividade.class);
            startActivity(intentHome);
        });

        // Ação para a tela de Atividades
        imageActivities.setOnClickListener(v -> {
            Intent intentActivities = new Intent(MainActivityRegistroAtividadeTreinar.this, MainActivityCadastroMetas.class);
            startActivity(intentActivities);
        });

        // Ação para a tela de Perfil
        imageProfile.setOnClickListener(v -> {
            Intent intentProfile = new Intent(MainActivityRegistroAtividadeTreinar.this, MainActivityRegistroAtividadeAlimentacao.class);
            startActivity(intentProfile);
        });

        // Ação para o botão "Calcular"
        salvarRegistroTreinar.setOnClickListener(v -> {
            calcularMeta();
        });

        // Ação para o botão "Limpar Registro"
        limparRegistroTreinar.setOnClickListener(v -> {
            limparCampos();
        });
    }

    // Método para calcular a diferença entre meta e valor registrado
    private void calcularMeta() {
        try {
            // Obter os valores inseridos
            int minutosTreinados = Integer.parseInt(registroQuantidadeTreinar.getText().toString());
            int metaDiaria = Integer.parseInt(metaDiariaTreinar.getText().toString());

            // Calcular a diferença
            int resultado = metaDiaria - minutosTreinados;

            // Exibir a mensagem conforme o resultado
            if (resultado <= 0) {
                resultadoTextView.setText("Você bateu a meta do dia!");
            } else {
                resultadoTextView.setText("Ainda não concluiu a meta");
            }
        } catch (NumberFormatException e) {
            // Caso os campos não estejam preenchidos corretamente
            resultadoTextView.setText("Por favor, insira valores válidos.");
        }
    }

    // Método para limpar os campos de entrada e o resultado
    private void limparCampos() {
        registroQuantidadeTreinar.setText("");
        metaDiariaTreinar.setText("");
        resultadoTextView.setText("");

        // Exibe uma mensagem de confirmação (opcional)
        resultadoTextView.setText("Campos limpos!");
    }
}
