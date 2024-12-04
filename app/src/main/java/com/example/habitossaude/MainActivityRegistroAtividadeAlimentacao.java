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

public class MainActivityRegistroAtividadeAlimentacao extends AppCompatActivity {

    private EditText registroQuantidadeAlimentacao;
    private EditText metaDiariaAlimentacao;
    private TextView resultadoTextView;
    private Button salvarRegistroAlimentacao;
    private Button limpaRegistroAlimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ativando o modo Edge to Edge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_registro_atividade_alimentacao);

        // Configurando insets para ajuste das margens com o sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializando os componentes
        registroQuantidadeAlimentacao = findViewById(R.id.RegistroQuantidadeAlimentacao);
        metaDiariaAlimentacao = findViewById(R.id.MetaDiariaAlimentacao);
        resultadoTextView = findViewById(R.id.textView);
        salvarRegistroAlimentacao = findViewById(R.id.SalvarRegistroAlimentacao);
        limpaRegistroAlimentacao = findViewById(R.id.LimpaRegistroAlimentacao);

        // Configuração das imagens de navegação
        ImageView imageHome = findViewById(R.id.imageHome);
        ImageView imageActivities = findViewById(R.id.imageActivities);
        ImageView imageProfile = findViewById(R.id.imageProfile);

        // Navegação para a tela de Início
        imageHome.setOnClickListener(v -> {
            Intent intentHome = new Intent(MainActivityRegistroAtividadeAlimentacao.this, MainActivitySelecaoAtividade.class);
            startActivity(intentHome);
        });

        // Navegação para a tela de Atividades
        imageActivities.setOnClickListener(v -> {
            Intent intentActivities = new Intent(MainActivityRegistroAtividadeAlimentacao.this, MainActivityCadastroMetas.class);
            startActivity(intentActivities);
        });

        // Navegação para a tela de Perfil
        imageProfile.setOnClickListener(v -> {
            Intent intentProfile = new Intent(MainActivityRegistroAtividadeAlimentacao.this, MainActivityRegistroAtividadeAgua.class);
            startActivity(intentProfile);
        });

        // Ação para o botão "Salvar Registro"
        salvarRegistroAlimentacao.setOnClickListener(v -> calcularMetaAlimentacao());

        // Ação para o botão "Limpar Registro"
        limpaRegistroAlimentacao.setOnClickListener(v -> limparCampos());
    }

    // Método para calcular a diferença entre a meta e a quantidade de refeições feitas
    private void calcularMetaAlimentacao() {
        try {
            // Obtendo os valores inseridos pelo usuário
            int quantidadeAlimentacao = Integer.parseInt(registroQuantidadeAlimentacao.getText().toString());
            int metaDiaria = Integer.parseInt(metaDiariaAlimentacao.getText().toString());

            // Calculando a diferença
            int resultado = metaDiaria - quantidadeAlimentacao;

            // Atualizando o TextView com o resultado
            if (resultado <= 0) {
                resultadoTextView.setText("Parabéns! Você atingiu sua meta alimentar.");
            } else {
                resultadoTextView.setText("Faltam " + resultado + " refeições para atingir sua meta.");
            }
        } catch (NumberFormatException e) {
            // Exibindo uma mensagem de erro caso os campos estejam vazios ou inválidos
            Toast.makeText(this, "Por favor, insira valores válidos.", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para limpar os campos de entrada e resultado
    private void limparCampos() {
        registroQuantidadeAlimentacao.setText("");
        metaDiariaAlimentacao.setText("");
        resultadoTextView.setText("");

        // Exibindo mensagem de confirmação
        Toast.makeText(this, "Campos limpos com sucesso.", Toast.LENGTH_SHORT).show();
    }
}
