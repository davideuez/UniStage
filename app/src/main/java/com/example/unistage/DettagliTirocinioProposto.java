package com.example.unistage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

public class DettagliTirocinioProposto extends AppCompatActivity {

    int posizione;
    ModuloPropostaTirocinio x;
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli_tirocinio_proposto);

        Intent i = getIntent();
        posizione = i.getIntExtra("position", -1);

        TextView titolo = findViewById(R.id.nome_tirocinio);
        TextView tipologia = findViewById(R.id.interno_esterno);
        TextView luogo = findViewById(R.id.luogo);
        TextView cfu = findViewById(R.id.cfu_modifica_tirocinio);
        TextView dataInizio = findViewById(R.id.inizio);
        TextView dataFine = findViewById(R.id.fine);
        TextView descrizione = findViewById(R.id.descrizione);
        TextView obiettivi = findViewById(R.id.obiettivi);
        ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);
        Button elimina = findViewById(R.id.elimina_tirocinio);
        Button modifica = findViewById(R.id.modifica_tirocinio);

        x = LoginActivity.listaTirociniPropostiSingle.get(posizione);

        titolo.setText(x.getTitolo());
        luogo.setText(x.getLuogo());
        cfu.setText(String.valueOf(x.getCFU()));
        dataInizio.setText(x.getDataInizio());
        dataFine.setText(x.getDataFine());
        descrizione.setText(x.getDescrizione());
        obiettivi.setText(x.getListaObiettivi());

        if(x.getTipologia() == 0){
            tipologia.setText("INTERNO");
        } else {
            tipologia.setText("ESTERNO");
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginActivity.listaTirociniPropostiSingle.remove(posizione);
                dbref.child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_Proposti").child(x.getTitolo()).setValue(null);
                dbref.child("Tirocini_Proposti_Professori").child(x.getTitolo()).setValue(null);

                Toast.makeText(DettagliTirocinioProposto.this, "Il tirocinio è stato eliminato correttamente", Toast.LENGTH_LONG).show();
                Intent i = new Intent(DettagliTirocinioProposto.this, Tirocini_attivi_professore.class);
                startActivity(i);

            }
        });

        modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DettagliTirocinioProposto.this, ModificaTirocinioProposto.class);
                i.putExtra("position", posizione);
                startActivity(i);
            }
        });



    }
}