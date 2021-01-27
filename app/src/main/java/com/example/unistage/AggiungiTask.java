package com.example.unistage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class AggiungiTask extends AppCompatActivity {

    int posizione, matricola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_task);

        Intent i = getIntent();
        posizione = i.getIntExtra("posizione", -1);
        matricola = i.getIntExtra("matricola", -1);

        TextView nomeStud = findViewById(R.id.nome_stud);
        TextView matricolaT = findViewById(R.id.matricola);
        EditText titolo = findViewById(R.id.titolo_new_task);
        EditText assegnatoIl = findViewById(R.id.assegnatoIl_new_task);
        EditText entroIl = findViewById(R.id.entroIl_new_task);
        EditText descrizione = findViewById(R.id.descrizione_new_task);
        ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);

        ModuloPropostaTirocinio x = LoginActivity.listaTirocini.get(posizione);

        matricolaT.setText("Mat. " + matricola);
        nomeStud.setText(x.getStudente());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}