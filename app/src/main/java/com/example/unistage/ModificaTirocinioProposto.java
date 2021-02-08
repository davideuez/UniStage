package com.example.unistage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModificaTirocinioProposto extends AppCompatActivity {

    int posizione;
    private DatabaseReference frdb = FirebaseDatabase.getInstance().getReference();
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
        Button annulla = findViewById(R.id.annulla_modifica_tirocinio);
        Button salva = findViewById(R.id.salva_modifica_tirocinio);

        x = LoginActivity.listaTirociniPropostiSingle.get(posizione);

        try {
            titolo.setText(x.getTitolo());
            luogo.setText(x.getLuogo());
            cfu.setText(String.valueOf(x.getCFU()));
            dataInizio.setText(x.getDataInizio());
            dataFine.setText(x.getDataFine());
            descrizione.setText(x.getDescrizione());
            obiettivi.setText(x.getListaObiettivi());
        }
        catch (Exception e){
            Toast.makeText(ModificaTirocinioProposto.this, "errore", Toast.LENGTH_LONG).show();
            finish();
        }


        if(x.getTipologia() == 0){
            interno.setChecked(true);
            esterno.setChecked(false);
        } else {
            esterno.setChecked(true);
            interno.setChecked(false);
        }

        interno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esterno.setChecked(false);
            }
        });

        esterno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interno.setChecked(false);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModuloPropostaTirocinio y = new ModuloPropostaTirocinio();

                y.titolo = titolo.getText().toString();

                // se i titoli sono uguali vengono aggiornati normalmente i dati nel db
                if(y.titolo.equals(x.getTitolo())){

                    y.luogo = luogo.getText().toString();
                    y.CFU = Integer.parseInt(cfu.getText().toString());
                    y.dataInizio = dataInizio.getText().toString();
                    y.dataFine = dataFine.getText().toString();
                    y.descrizione = descrizione.getText().toString();
                    y.listaObiettivi = obiettivi.getText().toString();
                    y.docente = LoginActivity.u_loggato.getCognome();

                    LoginActivity.listaTirociniPropostiSingle.remove(posizione);
                    LoginActivity.listaTirociniPropostiSingle.add(y);

                    frdb.child("Tirocini_Proposti_Professori").child(y.titolo).setValue(y);
                    frdb.child("Utenti").child("Professori").child(LoginActivity.u_loggato.cognome).child("Tirocini_Proposti").child(y.titolo).setValue(y);

                }

            }
        });



    }
}