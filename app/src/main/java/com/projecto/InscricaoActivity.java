package com.projecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InscricaoActivity extends AppCompatActivity {


    private EditText EditNome, EditNumero, EditTelefone, EditIdade, EditEmail;
    private DB_Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscricao);

        EditNome = findViewById(R.id.editTextNome);
        EditNumero = findViewById(R.id.editTextNumero);
        EditTelefone = findViewById(R.id.editTextTelefone);
        EditIdade = findViewById(R.id.editTextIdade);
        EditEmail = findViewById(R.id.editTextEmail);

        db = new DB_Helper(this);
    }


    public void btnPressionado(View view) {
        if (view.getId() == R.id.buttonInserir) {

            String Nome = EditNome.getText().toString();
            int Numero = Integer.parseInt(EditNumero.getText().toString());
            String Telefone = EditTelefone.getText().toString();
            int Idade = Integer.parseInt(EditIdade.getText().toString());
            String Email = EditEmail.getText().toString();

            if (db.InserFormando(Numero, Nome, Telefone, Idade, Email)) {
                Toast.makeText(InscricaoActivity.this, "Formando Inserido com SUCESSO", Toast.LENGTH_SHORT).show();
                finish();
            }

            Toast.makeText(InscricaoActivity.this, "ERRO ao Inserir o Formando", Toast.LENGTH_SHORT).show();

            finish();
        }

        else if (view.getId() == R.id.buttonVoltar) {
            finish();
        }
    }
}