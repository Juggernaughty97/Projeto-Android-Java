package com.projecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistoActivity extends AppCompatActivity {

    private EditText EditUser, EditPass, EditEmail;
    private DB_Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);

        EditUser = findViewById(R.id.editTextUsername);
        EditPass = findViewById(R.id.editTextPassword);
        EditEmail = findViewById(R.id.editTextEmailAddress);

        db = new DB_Helper(this);

    }

    public void btnPressionado(View view) {
        if (view.getId() == R.id.buttonRegisterSeg) {

            String Username = EditUser.getText().toString();
            String Password = EditPass.getText().toString();
            String Email = EditEmail.getText().toString();

            if(Username.isEmpty() || Password.isEmpty() || Email.isEmpty()) {
                Toast.makeText(RegistoActivity.this, "Dados para Utilizador Inválidos", Toast.LENGTH_SHORT).show();
            }

            else if (db.InserUser(Username, Password, Email)) {
                Toast.makeText(RegistoActivity.this, "Utilizador Inserido com SUCESSO", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                Toast.makeText(RegistoActivity.this, "ERRO na Inserção do Utilizador", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        else if (view.getId() == R.id.buttonCancel) {
            finish();
        }
    }
}
