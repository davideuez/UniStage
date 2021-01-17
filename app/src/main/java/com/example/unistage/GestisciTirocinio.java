package com.example.unistage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GestisciTirocinio extends AppCompatActivity {

    int posizione;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestisci_tirocinio);

        TextView nomeStudente = findViewById(R.id.nome_studente);
        TextView tipologia = findViewById(R.id.tipologia);
        TextView nome_azienda = findViewById(R.id.nome_azienda);
        TextView dataFine = findViewById(R.id.dataFine);
        ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);

        Intent i = getIntent();
        posizione = i.getIntExtra("posizione", -1);

        if(posizione != -1){

            ModuloPropostaTirocinio x = Walkthrough1Activity.listaTirocini.get(posizione);

            nomeStudente.setText(x.getStudente());

            if(x.getTipologia() == 0) {
                tipologia.setText("INTERNO");
            } else {
                tipologia.setText("ESTERNO");
            }

            nome_azienda.setText(x.getLuogo());
            dataFine.setText(x.getDataFine());

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });


        } else {

        }


    }

}