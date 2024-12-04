package com.example.habitossaude;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityCadastroMetas extends AppCompatActivity {

    // Definição dos EditTexts
    private EditText metaAguaEditText;
    private EditText metaLazerEditText;
    private EditText metaTrabalhoEditText;
    private EditText metaAlimentacaoEditText;
    private EditText metaEstudoEditText;
    private EditText metaSonoEditText;
    private EditText metaLeituraEditText;
    private EditText metaTreinoEditText;
    private EditText metaRedeSocialEditText;

    // Definição do botão de salvar
    private androidx.appcompat.widget.AppCompatButton salvarMetasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro_metas); // Certifique-se de que o layout correto esteja sendo referenciado

        // Inicializando os EditTexts
        metaAguaEditText = findViewById(R.id.CadastroMetaAgua);
        metaLazerEditText = findViewById(R.id.CadastroMetaLazer);
        metaTrabalhoEditText = findViewById(R.id.CadastroMetaTrabalho);
        metaAlimentacaoEditText = findViewById(R.id.CadastroMetaAlimentacao);
        metaEstudoEditText = findViewById(R.id.CadastroMetaEstudo);
        metaSonoEditText = findViewById(R.id.CadastroMetaSono);
        metaLeituraEditText = findViewById(R.id.CadastroMetaLeitura);
        metaTreinoEditText = findViewById(R.id.CadastroMetaTreino);
        metaRedeSocialEditText = findViewById(R.id.CadastroMetaRedeSocial);

        // Inicializando o botão de salvar
        salvarMetasButton = findViewById(R.id.SalvarMetas);

        // Configurando o clique do botão de salvar
        salvarMetasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obter os valores inseridos pelo usuário
                String metaAgua = metaAguaEditText.getText().toString();
                String metaLazer = metaLazerEditText.getText().toString();
                String metaTrabalho = metaTrabalhoEditText.getText().toString();
                String metaAlimentacao = metaAlimentacaoEditText.getText().toString();
                String metaEstudo = metaEstudoEditText.getText().toString();
                String metaSono = metaSonoEditText.getText().toString();
                String metaLeitura = metaLeituraEditText.getText().toString();
                String metaTreino = metaTreinoEditText.getText().toString();
                String metaRedeSocial = metaRedeSocialEditText.getText().toString();

                // Validando se algum campo está vazio
                if (metaAgua.isEmpty() || metaLazer.isEmpty() || metaTrabalho.isEmpty() ||
                        metaAlimentacao.isEmpty() || metaEstudo.isEmpty() || metaSono.isEmpty() ||
                        metaLeitura.isEmpty() || metaTreino.isEmpty() || metaRedeSocial.isEmpty()) {
                    // Exibir um aviso para o usuário
                    Toast.makeText(MainActivityCadastroMetas.this, "Por favor, preencha todas as metas!", Toast.LENGTH_SHORT).show();
                } else {
                    // Exibir as metas cadastradas (exemplo simples de sucesso)
                    Toast.makeText(MainActivityCadastroMetas.this, "Metas cadastradas com sucesso!", Toast.LENGTH_SHORT).show();

                    // Navegar para a próxima tela (MainActivitySelecaoAtividade)
                    Intent intent = new Intent(MainActivityCadastroMetas.this, MainActivitySelecaoAtividade.class);
                    startActivity(intent);
                }
            }
        });
    }
}
