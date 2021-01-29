package com.example.unistage;

import android.app.ActivityManager;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.unistage.LoginActivity.CHANNEL_1_ID;
import static com.example.unistage.LoginActivity.currentTime;
import static com.example.unistage.LoginActivity.notifiche;


public class HomeStudenteDURANTEActivity extends AppCompatActivity {
    int i=0;
    String nc;
    int m;
    public static ArrayList<Task> mTask;
    private NotificationManagerCompat nmc;
    int posizione;
    private DatabaseReference tasks;
    ModuloPropostaTirocinio x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homestudente_durante);
        if(i==0) {
            i++;
            nmc = NotificationManagerCompat.from(this);
            sendOnChannel();
        }

        HomeStudenteDURANTEActivity.mTask = LoginActivity.u_loggato.tirocinio_in_corso.listaTask;
        //x = LoginActivity.listaTirocini.get(posizione);

        mTask = new ArrayList<Task>();

        nc = LoginActivity.u_loggato.nome.substring(0,1).toUpperCase() + LoginActivity.u_loggato.nome.substring(1).toLowerCase() + " " + LoginActivity.u_loggato.cognome.substring(0,1).toUpperCase() + LoginActivity.u_loggato.cognome.substring(1).toLowerCase();
        final TextView nomeCognome = findViewById(R.id.nomecognome_durante);
        nomeCognome.setText(nc);

        m = LoginActivity.u_loggato.matricola;
        final TextView matricolaDurante = findViewById(R.id.matricola_durante);
        matricolaDurante.setText("Mat. "+m);


        final BottomNavigationView btv = findViewById(R.id.bottombar_studentedurante);
        getSupportFragmentManager().beginTransaction().replace(R.id.card_container_durante, new TirocinioDuranteFrag()).commit();
        btv.setOnNavigationItemSelectedListener(btnm);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener btnm = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.home:
                    fragment = new TirocinioDuranteFrag();
                    System.out.println("Home");
                    break;

                case R.id.tasks:
                    fragment = new TaskFrag();
                    System.out.println("Tasks");
                    break;

                case R.id.iter_tirocinio_durante:
                    fragment = new IterFrag();
                    System.out.println("Iter Tirocinio");
                    break;

                case R.id.notifiche:
                    fragment = new FragmentNotifiche();
                    System.out.println("Notifiche");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.card_container_durante, fragment).commit();
            return true;
        }
    };

    public void getTasks(){

        tasks = FirebaseDatabase.getInstance().getReference().child("Utenti").child("Studenti").child(String.valueOf(LoginActivity.u_loggato.getMatricola())).child("tirocinio_in_corso").child(x.getTitolo()).child("listaTask");
        tasks.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Task y = snapshot.getValue(Task.class);
                mTask.add(y);
                System.out.println("Tasks: " + y);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void sendOnChannel(){

        String messaggio = LoginActivity.u_loggato.tirocinio_in_corso.getTitolo();
        String titolo = "Tirocinio avviato ";
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
