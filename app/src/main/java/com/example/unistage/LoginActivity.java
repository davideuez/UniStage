package com.example.unistage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
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

import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    public static Utente u_loggato;
    private DatabaseReference studenti, professori;
    public boolean found = false;

    public static ArrayList<Utente> listaUtenti = new ArrayList<>();
    public static ArrayList<ModuloPropostaTirocinio> listaTirocini = new ArrayList<>();
    public static ArrayList<ModuloPropostaTirocinio> listaTirociniProposti = new ArrayList<>();

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
                        Intent k = new Intent(LoginActivity.this, HomeStudentePREActivity.class);
                        startActivity(k);
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

}
