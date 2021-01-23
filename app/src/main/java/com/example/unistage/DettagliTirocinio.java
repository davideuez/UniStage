package com.example.unistage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DettagliTirocinio extends AppCompatActivity {

    int posizione;
    int matricola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_tirocini);

        Intent i = getIntent();
        posizione = i.getIntExtra("posizione", -1);
        matricola = i.getIntExtra("matricola", -1);

        TextView nomeStud = findViewById(R.id.nome_stud);
        TextView matricolaT = findViewById(R.id.matricola);
        TextView titolo = findViewById(R.id.detail_titolo);
        TextView nomeProf = findViewById(R.id.detail_nomeProf);
        TextView dataInizio = findViewById(R.id.detail_dataInizio);
        TextView dataFine = findViewById(R.id.detail_dataFine);
        TextView luogo = findViewById(R.id.detail_luogo);
        TextView obiettiviFormativi = findViewById(R.id.detail_obiettivi);
        ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);

        matricolaT.setText("Mat." + matricola);

        ModuloPropostaTirocinio x = LoginActivity.listaTirocini.get(posizione);

        nomeStud.setText(x.getStudente());
        titolo.setText(x.getTitolo());
        nomeProf.setText("Gianni Bianchi");
        dataInizio.setText(x.getDataInizio());
        dataFine.setText(x.getDataFine());
        luogo.setText(x.getLuogo());
        obiettiviFormativi.setText(x.getListaObiettivi());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


}