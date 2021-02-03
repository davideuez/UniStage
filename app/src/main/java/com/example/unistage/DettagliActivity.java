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

import static com.example.unistage.LoginActivity.CHANNEL_1_ID;
import static com.example.unistage.LoginActivity.currentTime;
import static com.example.unistage.LoginActivity.notifiche;


public class DettagliActivity extends AppCompatActivity {
    public static String luogo_s;
    public static String responsabile_s;
    public static int data_s;
    public static ModuloPropostaTirocinio mpt;
    private DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
    private NotificationManagerCompat nmc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli);
        nmc = NotificationManagerCompat.from(this);
        
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
                sendOnChannel();
                mpt.setStudente(LoginActivity.u_loggato.nome + " " + LoginActivity.u_loggato.cognome);
                //dbref.child("Utenti").child("Studenti").child(LoginActivity.u_loggato.getMatricola()+"").child("tirocinio_avviato").setValue(true);
                //dbref.child("Utenti").child("Studenti").child(LoginActivity.u_loggato.getMatricola()+"").child("tirocinio_in_corso").child(mpt.getTitolo()).setValue(mpt);
                dbref.child("Utenti").child("Professori").child(mpt.docente).child("Tirocini_candidati").child(LoginActivity.u_loggato.getMatricola()+"").setValue(mpt);
                //mpt.listaTask.add(t);
                //LoginActivity.u_loggato.tirocinio_in_corso = mpt;
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

    public static void setDetail(ModuloPropostaTirocinio moduloPropostaTirocinio){
        mpt = moduloPropostaTirocinio;
        luogo_s = moduloPropostaTirocinio.luogo;
        responsabile_s = moduloPropostaTirocinio.docente;
        data_s = moduloPropostaTirocinio.durata;
        System.out.println(moduloPropostaTirocinio);
    }

    public void sendOnChannel(){
        String messaggio = luogo_s;
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
