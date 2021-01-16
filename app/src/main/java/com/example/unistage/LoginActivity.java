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

    public static Utente u;
    private DatabaseReference fdbr;

    public ArrayList<Utente> listaUtenti = new ArrayList<>();

    void init(){
        fdbr = FirebaseDatabase.getInstance().getReference().child("Utenti").child("Studenti");
        fdbr.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Utente x = snapshot.getValue(Utente.class);
                listaUtenti.add(x);
                System.out.println(x);
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

        final TextView email = findViewById(R.id.email_login_id);
        final TextView password = findViewById(R.id.password_login_id);
        final Button loginbutton = findViewById(R.id.accedi_btn);
        final TextView regbutton = findViewById(R.id.registratiqui);

        init();
        System.out.println(listaUtenti.toString());

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailIns = email.getText().toString();
                String passIns = password.getText().toString();

                String[] separaChiocciola = emailIns.split("@");
                String[] separa = separaChiocciola[0].split("\\.");

                for(int i=0; i<listaUtenti.size(); i++){

                    if(separa[1].toString().equals(listaUtenti.get(i).getCognome())){
                        String dbEmail = listaUtenti.get(i).getEmail();
                        String dbPass = listaUtenti.get(i).getPassword();

                        if (emailIns.equals(dbEmail) && passIns.equals(dbPass)){
                            System.out.println("Accesso consentito");
                        } else {
                            System.out.println("Credenziali errate");
                            Toast.makeText(getApplication(), "Credenziali errate", Toast.LENGTH_LONG).show();
                        }

                    }
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
}
