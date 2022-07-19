package com.projecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ListView listViewDados;
    DB_Helper DB;
    MyCustomAdapter myCustomAdapter;
    ArrayList<Dados> ArrayDados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DB = new DB_Helper(this);

        //listViewDados = (ListView)findViewById(R.id.ListViewDadosFor);

        listViewDados = findViewById(R.id.ListViewDadosFor);

        listViewDados.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Dados dados = (Dados) parent.getItemAtPosition(position);

                Intent intent = new Intent(HomeActivity.this, EditarInscricaoActivity.class);

                intent.putExtra("DadosSelectedId", String.valueOf(dados.getId()));

                startActivity(intent);
            }
        });
    }

    public void btnPressionado(View view){
        if (view.getId() == R.id.buttonInserir) {

            Intent intent = new Intent(this, InscricaoActivity.class);

            startActivity(intent);

        }
        else if (view.getId() == R.id.buttonLogout){
            finish();
        }
    }

    public void loadDados(){
        ArrayDados = DB.GetAllDados();

        myCustomAdapter = new MyCustomAdapter(this, ArrayDados);

        listViewDados.setAdapter(myCustomAdapter);

        myCustomAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDados();
    }
}