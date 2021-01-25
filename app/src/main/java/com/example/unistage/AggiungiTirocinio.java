package com.example.unistage;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AggiungiTirocinio extends AppCompatActivity {

    DatabaseReference fdbr, fdbr2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_tirocinio);

        final TextInputEditText titolo = findViewById(R.id.titolo_tirocinio_nuovo);
        final TextInputEditText luogo = findViewById(R.id.luogo_tirocinio_nuovo);
        final TextInputEditText dataInizio = findViewById(R.id.datainizio_nuovo);
        final TextInputEditText dataFine = findViewById(R.id.datafine_nuovo);
        final TextInputEditText cfu = findViewById(R.id.cfu_tirocinio_nuovo);
        final TextInputEditText descrizione = findViewById(R.id.descrizione_tirocinio_nuovo);
        final TextInputEditText obiettivi = findViewById(R.id.obiettiviformativi_tirocinio_nuovo);
        Button aggTirocinio = findViewById(R.id.inoltra_tirocinio_nuovo);
        Button annTirocinio = findViewById(R.id.annulla_tirocinio_nuovo);

        fdbr = FirebaseDatabase.getInstance().getReference().child("Tirocini_Proposti_Professori");
        fdbr2 = FirebaseDatabase.getInstance().getReference().child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_Proposti");

        aggTirocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModuloPropostaTirocinio x = new ModuloPropostaTirocinio();

                x.setTitolo(titolo.getText().toString());
                x.setLuogo(luogo.getText().toString());
                x.setDataInizio(dataInizio.getText().toString());
                x.setDataFine(dataFine.getText().toString());
                x.setCFU(Integer.parseInt(cfu.getText().toString()));
                x.setDescrizione(descrizione.getText().toString());
                x.setDocente((LoginActivity.u_loggato.cognome));
                x.setStudente("");
                x.setListaObiettivi(obiettivi.getText().toString());
                x.setTipologia(1);

                fdbr.child(x.getTitolo()).setValue(x);
                fdbr2.child(x.getTitolo()).setValue(x);

                Toast.makeText(AggiungiTirocinio.this, "Il tirocinio è stato aggiunto correttamente", Toast.LENGTH_LONG).show();
                Intent j = new Intent(AggiungiTirocinio.this, Tirocini_attivi_professore.class);
                startActivity(j);

            }
        });

        annTirocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}

