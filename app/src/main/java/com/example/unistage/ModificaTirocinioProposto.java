package com.example.unistage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class ModificaTirocinioProposto extends AppCompatActivity {

    int posizione;
    ModuloPropostaTirocinio x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_tirocinio_proposto);

        Intent i = getIntent();
        posizione = i.getIntExtra("position", -1);

        final TextInputEditText titolo = findViewById(R.id.titolo_tirocinio_nuovo);
        final TextInputEditText luogo = findViewById(R.id.luogo_tirocinio_nuovo);
        final TextInputEditText dataInizio = findViewById(R.id.datainizio_nuovo);
        final TextInputEditText dataFine = findViewById(R.id.datafine_nuovo);
        final TextInputEditText cfu = findViewById(R.id.cfu_tirocinio_nuovo);
        final RadioButton interno = findViewById(R.id.interno_tirocinio);
        final RadioButton esterno = findViewById(R.id.esterno_tirocinio);
        final TextInputEditText descrizione = findViewById(R.id.descrizione_tirocinio_nuovo);
        final TextInputEditText obiettivi = findViewById(R.id.obiettiviformativi_tirocinio_nuovo);
        final ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);
        Button aggTirocinio = findViewById(R.id.inoltra_tirocinio_nuovo);
        Button annTirocinio = findViewById(R.id.annulla_tirocinio_nuovo);

        x = LoginActivity.listaTirociniPropostiSingle.get(posizione);

        titolo.setText(x.getTitolo());
        luogo.setText(x.getLuogo());
        cfu.setText(String.valueOf(x.getCFU()));
        dataInizio.setText(x.getDataInizio());
        dataFine.setText(x.getDataFine());
        descrizione.setText(x.getDescrizione());
        obiettivi.setText(x.getListaObiettivi());

        if(x.getTipologia() == 0){
            interno.setChecked(true);
            esterno.setChecked(false);
        } else {
            esterno.setChecked(true);
            interno.setChecked(false);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}