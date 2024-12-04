package com.example.habitossaude;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityRegistroAtividadeEstudar extends AppCompatActivity {

    private EditText registroQuantidadeEstudo;
    private EditText metaDiariaEstudo;
    private TextView resultadoTextView;
    private Button salvarRegistroEstudo;
    private Button limpaRegistroEstudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_atividade_estudar);

        // Inicializar os componentes
        registroQuantidadeEstudo = findViewById(R.id.RegistroQuantidadeEstudo);
        metaDiariaEstudo = findViewById(R.id.MetaDiariaEstudo);
        resultadoTextView = findViewById(R.id.textView);
        salvarRegistroEstudo = findViewById(R.id.SalvarRegistroEstudo);
        limpaRegistroEstudo = findViewById(R.id.LimpaRegistroEstudar);

        // Configuração das imagens de navegação
        ImageView imageHome = findViewById(R.id.imageHome);
        ImageView imageActivities = findViewById(R.id.imageActivities);
        ImageView imageProfile = findViewById(R.id.imageProfile);

        // Ação para redirecionar para a tela de Início
        imageHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(MainActivityRegistroAtividadeEstudar.this, MainActivitySelecaoAtividade.class);
            startActivity(intentHome);
        });

        // Ação para redirecionar para a tela de Atividades
        imageActivities.setOnClickListener(v -> {
            Intent intentActivities = new Intent(MainActivityRegistroAtividadeEstudar.this, MainActivityCadastroMetas.class);
            startActivity(intentActivities);
        });

        // Ação para redirecionar para a tela de Perfil
        imageProfile.setOnClickListener(v -> {
            Intent intentProfile = new Intent(MainActivityRegistroAtividadeEstudar.this, MainActivityRegistroAtividadeLazer.class);
            startActivity(intentProfile);
        });

        // Ação para o botão "Calcular"
        salvarRegistroEstudo.setOnClickListener(v -> calcularMetaEstudo());

        // Ação para o botão "Limpar Registro"
        limpaRegistroEstudo.setOnClickListener(v -> limparCampos());
    }

    // Método para calcular a diferença entre a meta e os minutos de estudo
    private void calcularMetaEstudo() {
        try {
            // Obter os valores inseridos
            int minutosEstudo = Integer.parseInt(registroQuantidadeEstudo.getText().toString());
            int metaDiaria = Integer.parseInt(metaDiariaEstudo.getText().toString());

            // Calcular a diferença
            int resultado = metaDiaria - minutosEstudo;

            // Exibir a mensagem conforme o resultado
            if (resultado <= 0) {
                resultadoTextView.setText("Parabéns! Você atingiu sua meta de estudo!");
            } else {
                resultadoTextView.setText("Faltam " + resultado + " minutos para atingir sua meta.");
            }
        } catch (NumberFormatException e) {
            // Caso os campos não estejam preenchidos corretamente
            Toast.makeText(this, "Por favor, insira valores válidos.", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para limpar os campos de entrada e resultado
    private void limparCampos() {
        registroQuantidadeEstudo.setText("");
        metaDiariaEstudo.setText("");
        resultadoTextView.setText("");

        // Exibir mensagem de confirmação
        Toast.makeText(this, "Campos limpos com sucesso.", Toast.LENGTH_SHORT).show();
    }
}
