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

public class MainActivityRegistroAtividadeLazer extends AppCompatActivity {

    private EditText registroQuantidadeLazer;
    private EditText metaDiariaLazer;
    private TextView resultadoTextView;
    private Button salvarRegistroLazer;
    private Button limpaRegistroLazer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilita o modo Edge to Edge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_registro_atividade_lazer);

        // Inicializar os componentes
        registroQuantidadeLazer = findViewById(R.id.RegistroQuantidadeLazer);
        metaDiariaLazer = findViewById(R.id.MetaDiariaLazer);
        resultadoTextView = findViewById(R.id.textView);
        salvarRegistroLazer = findViewById(R.id.SalvarRegistroEstudo);
        limpaRegistroLazer = findViewById(R.id.LimpaRegistroLazer);

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
            Intent intentHome = new Intent(MainActivityRegistroAtividadeLazer.this, MainActivitySelecaoAtividade.class);
            startActivity(intentHome);
        });

        // Ação para a tela de Atividades
        imageActivities.setOnClickListener(v -> {
            Intent intentActivities = new Intent(MainActivityRegistroAtividadeLazer.this, MainActivityCadastroMetas.class);
            startActivity(intentActivities);
        });

        // Ação para a tela de Perfil
        imageProfile.setOnClickListener(v -> {
            Intent intentProfile = new Intent(MainActivityRegistroAtividadeLazer.this, MainActivityRegistroAtividadeLer.class);
            startActivity(intentProfile);
        });

        // Ação para o botão "Calcular"
        salvarRegistroLazer.setOnClickListener(v -> calcularMeta());

        // Ação para o botão "Limpar Registro"
        limpaRegistroLazer.setOnClickListener(v -> limparCampos());
    }

    // Método para calcular a diferença entre a meta e os minutos de lazer
    private void calcularMeta() {
        try {
            // Obter os valores inseridos
            int minutosLazer = Integer.parseInt(registroQuantidadeLazer.getText().toString());
            int metaDiaria = Integer.parseInt(metaDiariaLazer.getText().toString());

            // Calcular a diferença
            int resultado = metaDiaria - minutosLazer;

            // Exibir a mensagem conforme o resultado
            if (resultado <= 0) {
                resultadoTextView.setText("Parabéns! Você alcançou sua meta de lazer!");
            } else {
                resultadoTextView.setText("Faltam " + resultado + " minutos para atingir sua meta.");
            }
        } catch (NumberFormatException e) {
            // Caso os campos não estejam preenchidos corretamente
            Toast.makeText(this, "Por favor, insira valores válidos.", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para limpar os campos de entrada e o resultado
    private void limparCampos() {
        registroQuantidadeLazer.setText("");
        metaDiariaLazer.setText("");
        resultadoTextView.setText("");

        // Exibe uma mensagem de confirmação
        Toast.makeText(this, "Campos limpos com sucesso.", Toast.LENGTH_SHORT).show();
    }
}
