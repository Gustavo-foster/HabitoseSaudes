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

public class MainActivityRegistroAtividadeLer extends AppCompatActivity {

    private EditText registroQuantidadeLer;
    private EditText metaDiariaLer;
    private TextView resultadoTextView;
    private Button salvarRegistroLer;
    private Button limparRegistroLer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilita o modo Edge to Edge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_registro_atividade_ler);

        // Inicializar os componentes
        registroQuantidadeLer = findViewById(R.id.RegistroQuantidadeLer);
        metaDiariaLer = findViewById(R.id.MetaDiariaLer);
        resultadoTextView = findViewById(R.id.textView);
        salvarRegistroLer = findViewById(R.id.SalvarRegistroLer);
        limparRegistroLer = findViewById(R.id.LimpaRegistroLer);

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
            Intent intentHome = new Intent(MainActivityRegistroAtividadeLer.this, MainActivitySelecaoAtividade.class);
            startActivity(intentHome);
        });

        // Ação para a tela de Atividades
        imageActivities.setOnClickListener(v -> {
            Intent intentActivities = new Intent(MainActivityRegistroAtividadeLer.this, MainActivityCadastroMetas.class);
            startActivity(intentActivities);
        });

        // Ação para a tela de Perfil
        imageProfile.setOnClickListener(v -> {
            Intent intentProfile = new Intent(MainActivityRegistroAtividadeLer.this, MainActivityRegistroAtividadeRedeSocial.class);
            startActivity(intentProfile);
        });

        // Ação para o botão "Calcular"
        salvarRegistroLer.setOnClickListener(v -> calcularMeta());

        // Ação para o botão "Limpar Registro"
        limparRegistroLer.setOnClickListener(v -> limparCampos());
    }

    // Método para calcular a diferença entre a meta e as páginas lidas
    private void calcularMeta() {
        try {
            // Obter os valores inseridos
            int paginasLidas = Integer.parseInt(registroQuantidadeLer.getText().toString());
            int metaDiaria = Integer.parseInt(metaDiariaLer.getText().toString());

            // Calcular a diferença
            int resultado = metaDiaria - paginasLidas;

            // Exibir a mensagem conforme o resultado
            if (resultado <= 0) {
                resultadoTextView.setText("Parabéns! Você atingiu sua meta de leitura!");
            } else {
                resultadoTextView.setText("Faltam " + resultado + " páginas para atingir sua meta.");
            }
        } catch (NumberFormatException e) {
            // Caso os campos não estejam preenchidos corretamente
            Toast.makeText(this, "Por favor, insira valores válidos.", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para limpar os campos de entrada e o resultado
    private void limparCampos() {
        registroQuantidadeLer.setText("");
        metaDiariaLer.setText("");
        resultadoTextView.setText("");

        // Exibe uma mensagem de confirmação
        Toast.makeText(this, "Campos limpos com sucesso.", Toast.LENGTH_SHORT).show();
    }
}
