package com.example.habitossaude;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityRegistroAtividadeAgua extends AppCompatActivity {

    private EditText registroQuantidadeAgua;
    private EditText metaDiariaAgua;
    private TextView resultadoTextView;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro_atividade_agua);

        // Inicializa os campos de entrada
        registroQuantidadeAgua = findViewById(R.id.RegistroQuantidadeAgua);
        metaDiariaAgua = findViewById(R.id.MetaDiariaAgua);
        resultadoTextView = findViewById(R.id.textView);  // Novo TextView para o resultado

        // Configura o botão "Salvar Registro"
        findViewById(R.id.SalvarRegistroAgua).setOnClickListener(v -> {
            atualizarConsumo();
        });

        // Configura o botão "Limpar Registro"
        Button limpaRegistroAgua = findViewById(R.id.LimpaRegistroAgua);
        limpaRegistroAgua.setOnClickListener(v -> {
            limparCampos();
        });

        // Configuração dos ícones de navegação inferior
        configurarNavegacaoInferior();
    }

    private void atualizarConsumo() {
        try {
            // Obtém a meta diária informada pelo usuário (em ml)
            int metaAgua = Integer.parseInt(metaDiariaAgua.getText().toString());

            // Obtém o consumo registrado pelo usuário (em ml)
            int quantidadeConsumida = Integer.parseInt(registroQuantidadeAgua.getText().toString());

            // Calcula a diferença entre a meta e o consumo
            int diferenca = metaAgua - quantidadeConsumida;

            // Exibe o resultado no TextView de feedback
            if (diferenca > 0) {
                resultadoTextView.setText("Você ainda precisa consumir " + diferenca + " ml para atingir a meta.");
            } else {
                resultadoTextView.setText("Parabéns! Você atingiu ou superou sua meta!");
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, insira valores válidos nos campos.", Toast.LENGTH_SHORT).show();
        }
    }

    private void limparCampos() {
        // Limpa os valores dos campos
        registroQuantidadeAgua.setText("");
        metaDiariaAgua.setText("");
        resultadoTextView.setText("");  // Limpa o resultado no TextView

        // Exibe uma mensagem de confirmação para o usuário
        Toast.makeText(this, "Campos limpos com sucesso.", Toast.LENGTH_SHORT).show();
    }

    private void configurarNavegacaoInferior() {
        ImageView imageHome = findViewById(R.id.imageHome);
        ImageView imageActivities = findViewById(R.id.imageActivities);
        ImageView imageProfile = findViewById(R.id.imageProfile);

        imageHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivitySelecaoAtividade.class);
            startActivity(intent);
        });

        imageActivities.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivityCadastroMetas.class);
            startActivity(intent);
        });

        imageProfile.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivityRegistroAtividadeDormir.class);
            startActivity(intent);
        });
    }
}
