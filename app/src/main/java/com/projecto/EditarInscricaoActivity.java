package com.projecto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditarInscricaoActivity extends AppCompatActivity {

    private EditText EditNumero, EditNome, EditTelefone, EditIdade, EditEmail;
    private DB_Helper db;

    int DatabaseTableItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_inscricao);

        EditNumero = findViewById(R.id.editTextNumeroInsc);
        EditNome = findViewById(R.id.editTextNomeInsc);
        EditTelefone = findViewById(R.id.editTextTelefoneInsc);
        EditIdade = findViewById(R.id.editTextIdadeInsc);
        EditEmail = findViewById(R.id.editTextEmailInsc);

        db = new DB_Helper(this);

        Intent intent = getIntent();

        DatabaseTableItemId = Integer.parseInt(intent.getStringExtra("DadosSelectedId"));

        Dados dados = db.GetDadosById(DatabaseTableItemId);

        EditNumero.setText(String.valueOf(dados.getNumero()));
        EditNome.setText(dados.getNome());
        EditTelefone.setText(dados.getTelefone());
        EditIdade.setText(String.valueOf(dados.getIdade()));
        EditEmail.setText(dados.getEmail());
    }

    public void verFormando(View view)
    {
        Dados dados = db.GetDadosById(DatabaseTableItemId);

        StringBuffer buffer = new StringBuffer();

        buffer.append("Numero: " + dados.getNumero() + "\n");
        buffer.append("Nome: " + dados.getNome() + "\n");
        buffer.append("Telefone: " + dados.getTelefone() + "\n");
        buffer.append("Idade: " + dados.getIdade() + "\n");
        buffer.append("Email: " + dados.getEmail() + "\n");

        ShowAlertMessage("Dados:", buffer.toString());
    }

    private void ShowAlertMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }

    public void btnPressionado(View view) {
        if (view.getId() == R.id.buttonUpdate) {

            String Nome = EditNome.getText().toString();
            int Numero = Integer.parseInt(EditNumero.getText().toString());
            String Telefone = EditTelefone.getText().toString();
            int Idade = Integer.parseInt(EditIdade.getText().toString());
            String Email = EditEmail.getText().toString();

            if (db.UpdateFormando(DatabaseTableItemId, Numero, Nome, Telefone, Idade, Email)) {
                Toast.makeText(EditarInscricaoActivity.this, "Formando Inserido com SUCESSO", Toast.LENGTH_SHORT).show();
                finish();
            }

            Toast.makeText(EditarInscricaoActivity.this, "ERRO ao Inserir o Formando", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if (view.getId() == R.id.buttonDelete) {

            Integer DeletedRows = db.DeleteFormando(DatabaseTableItemId);

            if (DeletedRows > 0){
                Toast.makeText(EditarInscricaoActivity.this, "Formando Apagado com SUCESSO", Toast.LENGTH_SHORT).show();
                finish();
            }
            Toast.makeText(EditarInscricaoActivity.this, "ERRO ao Apagar o Formando", Toast.LENGTH_SHORT).show();
            finish();

            }
        else if (view.getId() == R.id.buttonVoltarSeg) {
                finish();
        }
        else if (view.getId() == R.id.buttonVer){
            db.LerFormandos();
            verFormando(view);
        }

    }


}
