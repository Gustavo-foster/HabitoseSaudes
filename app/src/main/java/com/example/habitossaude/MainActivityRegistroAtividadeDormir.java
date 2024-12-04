package com.example.habitossaude;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityRegistroAtividadeDormir extends AppCompatActivity {

    private EditText registroQuantidadeDormir;
    private EditText metaDiariaDormir;
    private TextView resultadoTextView;
    private Button salvarRegistroDormir;
    private Button limpaRegistroDormir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_atividade_dormir);

        // Inicializar os componentes
        registroQuantidadeDormir = findViewById(R.id.RegistroQuantidadeDormir);
        metaDiariaDormir = findViewById(R.id.MetaDiariaDormir);
        resultadoTextView = findViewById(R.id.textView);
        salvarRegistroDormir = findViewById(R.id.SalvarRegistroDormir);
        limpaRegistroDormir = findViewById(R.id.LimpaRegistroDormir);

        // Configuração das imagens de navegação
        ImageView imageHome = findViewById(R.id.imageHome);
        ImageView imageActivities = findViewById(R.id.imageActivities);
        ImageView imageProfile = findViewById(R.id.imageProfile);

        // Navegação para a tela de Início
        imageHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(MainActivityRegistroAtividadeDormir.this, MainActivitySelecaoAtividade.class);
            startActivity(intentHome);
        });

        // Navegação para a tela de Atividades
        imageActivities.setOnClickListener(v -> {
            Intent intentActivities = new Intent(MainActivityRegistroAtividadeDormir.this, MainActivityCadastroMetas.class);
            startActivity(intentActivities);
        });

        // Navegação para a tela de Perfil
        imageProfile.setOnClickListener(v -> {
            Intent intentProfile = new Intent(MainActivityRegistroAtividadeDormir.this, MainActivityRegistroAtividadeEstudar.class);
            startActivity(intentProfile);
        });

        // Ação para o botão "Salvar Registro"
        salvarRegistroDormir.setOnClickListener(v -> calcularMetaSono());

        // Ação para o botão "Limpar Registro"
        limpaRegistroDormir.setOnClickListener(v -> limparCampos());
    }

    // Método para calcular a diferença entre a meta e as horas dormidas
    private void calcularMetaSono() {
        try {
            // Obter os valores inseridos
            int horasDormidas = Integer.parseInt(registroQuantidadeDormir.getText().toString());
            int metaDiaria = Integer.parseInt(metaDiariaDormir.getText().toString());

            // Calcular a diferença
            int resultado = metaDiaria - horasDormidas;

            // Exibir a mensagem conforme o resultado
            if (resultado <= 0) {
                resultadoTextView.setText("Parabéns! Você atingiu sua meta de sono!");
            } else {
                resultadoTextView.setText("Faltam " + resultado + " horas para atingir sua meta.");
            }
        } catch (NumberFormatException e) {
            // Caso os campos não estejam preenchidos corretamente
            Toast.makeText(this, "Por favor, insira valores válidos.", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para limpar os campos de entrada e resultado
    private void limparCampos() {
        registroQuantidadeDormir.setText("");
        metaDiariaDormir.setText("");
        resultadoTextView.setText("");

        // Exibir mensagem de confirmação
        Toast.makeText(this, "Campos limpos com sucesso.", Toast.LENGTH_SHORT).show();
    }
}
