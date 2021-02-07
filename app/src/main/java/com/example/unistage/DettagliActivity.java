package com.example.unistage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.unistage.LoginActivity.CHANNEL_1_ID;
import static com.example.unistage.LoginActivity.currentTime;
import static com.example.unistage.LoginActivity.listaUtenti;
import static com.example.unistage.LoginActivity.notifiche;


public class DettagliActivity extends AppCompatActivity {

    public static ModuloPropostaTirocinio mpt;
    private DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
    private NotificationManagerCompat nmc;
    public String n_prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli);
        nmc = NotificationManagerCompat.from(this);

        Intent i = getIntent();
        int pos = i.getIntExtra("posizione", -1);
        int calledFrom = i.getIntExtra("callFrom", -1);

        if(calledFrom == 0){
            mpt = LoginActivity.listaTirociniProposti.get(pos);
        } else {
            mpt = LoginActivity.lista_tirocini_salvati.get(pos);
        }


        final TextView titoloSopra = findViewById(R.id.nome_tirocinio);
        final TextView tipologia = findViewById(R.id.interno_est);
        final TextView prof = findViewById(R.id.detail_nomeProf);
        final TextView dataInizio = findViewById(R.id.detail_dataInizio);
        final TextView dataFine = findViewById(R.id.detail_dataFine);
        final TextView luogo = findViewById(R.id.detail_luogo);
        final TextView CFU = findViewById(R.id.CFU);
        final TextView descrizione = findViewById(R.id.descrizione_tir);
        final TextView obiettivi = findViewById(R.id.detail_obiettivi);
        final ImageButton back = findViewById(R.id.back_button_dettagli);
        final Button salva = findViewById(R.id.salva_dettagli_id);
        final Button candidati = findViewById(R.id.candidati_dettagli_id);

        titoloSopra.setText(mpt.getTitolo());

        if(mpt.getTipologia() == 0) {
            tipologia.setText("INTERNO");
        } else {
            tipologia.setText("ESTERNO");
        }

        for(int j=0; j<LoginActivity.listaUtenti.size(); j++){

            if(LoginActivity.listaUtenti.get(j).getCognome().equals(mpt.docente)){
                n_prof = LoginActivity.listaUtenti.get(j).getNome();
            }

        }


        String cognome_prof = mpt.docente;
        String nome_prof = n_prof;

        String nome_completo = nome_prof.substring(0, 1).toUpperCase() + nome_prof.substring(1).toLowerCase() + " " + cognome_prof.substring(0, 1).toUpperCase() + cognome_prof.substring(1).toLowerCase();

        prof.setText(nome_completo);
        dataInizio.setText(mpt.getDataInizio());
        dataFine.setText(mpt.getDataFine());
        luogo.setText(mpt.luogo);
        CFU.setText(String.valueOf(mpt.getCFU()));
        descrizione.setText(mpt.getDescrizione());
        obiettivi.setText(mpt.getListaObiettivi());


        candidati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendOnChannel();
                mpt.setStudente(LoginActivity.u_loggato.nome + " " + LoginActivity.u_loggato.cognome);
                dbref.child("Utenti").child("Professori").child(mpt.docente).child("Tirocini_candidati").child(LoginActivity.u_loggato.getMatricola()+"").setValue(mpt);
                Toast.makeText(DettagliActivity.this, "Ti sei candidato al tirocinio", Toast.LENGTH_LONG).show();
                Intent i = new Intent(DettagliActivity.this, HomeStudentePREActivity.class);
                startActivity(i);
            }
        });


        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.lista_tirocini_salvati.add(mpt);
                System.out.println("Tirocinio salvato: " + mpt);
                dbref.child("Utenti").child("Studenti").child(LoginActivity.u_loggato.getMatricola()+"").child("tirocini_salvati").child(mpt.getTitolo()).setValue(mpt);
                System.out.println(LoginActivity.u_loggato.toString());
                Toast.makeText(DettagliActivity.this, "Tirocinio salvato", Toast.LENGTH_LONG).show();
                Intent i = new Intent(DettagliActivity.this, HomeStudentePREActivity.class);
                startActivity(i);

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void sendOnChannel(){
        String messaggio = mpt.titolo;
        String titolo = "Ti sei candidato a ";
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle(titolo)
                .setContentText(messaggio)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();
        LoginActivity.notifiche.add(new Notifiche(titolo,messaggio,currentTime));
        System.out.println(notifiche.toString());
        nmc.notify(1, notification);
    }


}
