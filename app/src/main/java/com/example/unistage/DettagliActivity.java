package com.example.unistage;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class DettagliActivity extends AppCompatActivity {
    public static String luogo_s;
    public static String responsabile_s;
    public static int data_s;
    public static ModuloPropostaTirocinio mpt;
    public static ArrayList<ModuloPropostaTirocinio> tirocini_salvati = new ArrayList<>();
    private DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli);
        final Task t = new Task("diocan", "22/02/22", 1, "ciao dave", "22/03/22");
        final TextView luogo = findViewById(R.id.luogo_dettagli_id);
        luogo.setText(luogo_s);
        final TextView responsabile = findViewById(R.id.responsabile_dettagli_id);
        responsabile.setText(responsabile_s);
        final TextView data = findViewById(R.id.apertura_iscrizioni_dettagli_id);
        data.setText(String.valueOf(data_s));
        final ImageButton back = findViewById(R.id.back_button_dettagli);
        final Button salva = findViewById(R.id.salva_dettagli_id);
        final Button candidati = findViewById(R.id.candidati_dettagli_id);

        candidati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mpt.setStudente(LoginActivity.u_loggato.nome + " " + LoginActivity.u_loggato.cognome);
                dbref.child("Utenti").child("Studenti").child(LoginActivity.u_loggato.getMatricola()+"").child("tirocinio_avviato").setValue(true);
                dbref.child("Utenti").child("Studenti").child(LoginActivity.u_loggato.getMatricola()+"").child("tirocinio_in_corso").child(mpt.getTitolo()).setValue(mpt);
                dbref.child("Utenti").child("Professori").child(mpt.docente).child("Tirocini_avviati").child(mpt.getTitolo()).setValue(mpt);
                mpt.listaTask.add(t);
                LoginActivity.u_loggato.tirocinio_in_corso = mpt;
                Intent i = new Intent(DettagliActivity.this, HomeStudenteDURANTEActivity.class);
                startActivity(i);
            }
        });


        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginActivity.u_loggato.tirocinio_in_corso = mpt;
                System.out.println("Tirocinio salvato: " + mpt);
                dbref.child("Utenti").child("Studenti").child(LoginActivity.u_loggato.getMatricola()+"").child("tirocini_salvati").child(mpt.getTitolo()).setValue(mpt);

                tirocini_salvati.add(mpt);

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

    public static void setDetail(ModuloPropostaTirocinio moduloPropostaTirocinio){
        mpt = moduloPropostaTirocinio;
        luogo_s = moduloPropostaTirocinio.luogo;
        responsabile_s = moduloPropostaTirocinio.docente;
        data_s = moduloPropostaTirocinio.durata;
        System.out.println(moduloPropostaTirocinio);
    }


}
