package com.projecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText EditUser, EditPass;
    private DB_Helper db;

    ListView listViewDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditUser = findViewById(R.id.LogInUsername);
        EditPass = findViewById(R.id.LogInPassword);

        db = new DB_Helper(this);

    }

    public void btnPressionado(View view){

        if (view.getId() == R.id.buttonLogIn){

            String username = EditUser.getText().toString();
            String password = EditPass.getText().toString();

            if (db.CheckLogin(username, password))
            {
                Intent intent = new Intent(this, HomeActivity.class);

                startActivity(intent);

                Toast.makeText(this, "Login com sucesso!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Login errado!", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId() == R.id.buttonRegisterPri){
            Intent intent = new Intent(this, RegistoActivity.class);

            startActivity(intent);
        }
    }
}


