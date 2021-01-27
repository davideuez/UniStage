package com.example.unistage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    public static Utente u_loggato;
    private DatabaseReference studenti, professori;
    public boolean found = false;
    public static String CHANNEL_1_ID = "channel1";

    public static ArrayList<Utente> listaUtenti = new ArrayList<>();
    public static ArrayList<ModuloPropostaTirocinio> listaTirocini = new ArrayList<>();
    public static ArrayList<ModuloPropostaTirocinio> listaTirociniProposti = new ArrayList<>();
    public static ArrayList<ArrayList<Task>> listaTask = new ArrayList<>();
    public static ArrayList<Notifiche> notifiche = new ArrayList<>();

    static SimpleDateFormat sdfStopTime = new SimpleDateFormat("dd/MM/yy - HH:mm:ss", Locale.ITALY);
    public static String currentTime = sdfStopTime.format(new Date(System.currentTimeMillis() + 3600000));

    void init(){
        studenti = FirebaseDatabase.getInstance().getReference().child("Utenti").child("Studenti");
        studenti.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Utente x = snapshot.getValue(Utente.class);
                listaUtenti.add(x);
                System.out.println("Studenti" + x);
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

        professori = FirebaseDatabase.getInstance().getReference().child("Utenti").child("Professori");
        professori.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Utente x = snapshot.getValue(Utente.class);
                listaUtenti.add(x);
                System.out.println("Professori" + x);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createNotificationChannel();

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            startActivity(new Intent(LoginActivity.this, Walkthrough1Activity.class));
        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).commit();

        final TextView email = findViewById(R.id.email_login_id);
        final TextView password = findViewById(R.id.password_login_id);
        final Button loginbutton = findViewById(R.id.accedi_btn);
        final TextView regbutton = findViewById(R.id.registratiqui);

        init();
        inizializzaTirociniProposti();
        System.out.println(listaUtenti.toString());

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Tentato accesso");

                String emailIns = email.getText().toString();
                String passIns = password.getText().toString();

                System.out.println("Email inserita: " + emailIns);
                System.out.println("Pass inserita: " + passIns);

                isRegistered(emailIns, passIns);

                if(found == true) {

                    if(u_loggato.getRuolo().equals("studente")){
                        if(u_loggato.isTirocinio_avviato() == false){
                            Intent k = new Intent(LoginActivity.this, HomeStudentePREActivity.class);
                            startActivity(k);
                        } else {
                            inizializzaTirocinioInCorso();
                            Intent m = new Intent(LoginActivity.this, HomeStudenteDURANTEActivity.class);
                            startActivity(m);
                        }

                    } else {
                        inizializzaTirociniAttivi();
                        Intent j = new Intent(LoginActivity.this, Tirocini_attivi_professore.class);
                        startActivity(j);
                    }

                } else {
                    System.out.println("Credenziali errate sopra");
                    Toast.makeText(LoginActivity.this, "Credenziali errate", Toast.LENGTH_SHORT).show();
                }

            }
        });

        regbutton.setPaintFlags(regbutton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, RegistrazioneActivity.class);
                startActivity(i);
            }
        });

    }

    public void isRegistered(String emailI, String passI){
        for(int i=0; i<listaUtenti.size(); i++){

            if(emailI.equals(listaUtenti.get(i).getEmail())){
                System.out.println("mail coincidono");
                String dbPass = listaUtenti.get(i).getPassword();

                if (passI.equals(dbPass)){
                    System.out.println("Accesso consentito");
                    u_loggato = listaUtenti.get(i);
                    found = true;

                } else {
                    System.out.println("Password errata");
                }

            } else {
                System.out.println("Email errata");
            }
        }
    }

    void inizializzaTirociniAttivi(){
        DatabaseReference tirocini = FirebaseDatabase.getInstance().getReference().child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_avviati");
        tirocini.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("Snapshot: " + snapshot.toString());
                ModuloPropostaTirocinio x = snapshot.getValue(ModuloPropostaTirocinio.class);
                getTasks(x.titolo, x);
                listaTirocini.add(x);
                System.out.println("Elemento array: " + x);
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

    void inizializzaTirocinioInCorso(){

        DatabaseReference tirocinioInCorso = FirebaseDatabase.getInstance().getReference().child("Utenti").child("Studenti").child(String.valueOf(LoginActivity.u_loggato.getMatricola())).child("tirocinio_in_corso");
        tirocinioInCorso.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("Tirocinio in corso snap: " + snapshot.toString());
                ModuloPropostaTirocinio x = snapshot.getValue(ModuloPropostaTirocinio.class);
                getTasks(x.titolo, x);
                u_loggato.tirocinio_in_corso = x;
                System.out.println("Tirocinio in corso oggetto: " + x.toString());
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

    public void getTasks(String titolo, final ModuloPropostaTirocinio tir){

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_avviati").child(titolo);
        DatabaseReference friendsRef = rootRef.child("listaTask");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Task> listaProvvisoria = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Task task = ds.getValue(Task.class);
                    listaProvvisoria.add(task);
                    tir.listaTask.add(task);
                    Log.d("Singolo Task", task.toString());
                }
                Log.d("Singola", String.valueOf(listaProvvisoria));
                listaTask.add(listaProvvisoria);
                Log.d("Gruppo", String.valueOf(listaTask));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        friendsRef.addListenerForSingleValueEvent(eventListener);

    }

    public void inizializzaTirociniProposti() {
        DatabaseReference tirociniProposti = FirebaseDatabase.getInstance().getReference().child("Tirocini_Proposti_Professori");
        tirociniProposti.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("Snapshot: " + snapshot.toString());
                ModuloPropostaTirocinio x = snapshot.getValue(ModuloPropostaTirocinio.class);
                listaTirociniProposti.add(x);
                System.out.println("Elemento array: " + x);
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

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Student Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }


}