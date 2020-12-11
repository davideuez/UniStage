package com.example.unistage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrazioneActivity extends AppCompatActivity {

    private DatabaseReference fdbr;
    public static int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        final RadioButton rbprof = findViewById(R.id.profrb_signup_id);
        final RadioButton rbstud = findViewById(R.id.studenterb_signup_id);
        final RadioButton rbtuto = findViewById(R.id.tutorrb_signup_id);
        final Button reg = findViewById(R.id.registbutton_signup_id);
        final TextView accediqui = findViewById(R.id.accediqui);


        rbprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
                if(rbprof.isChecked()){
                    if (rbstud.isChecked()){
                        rbstud.toggle();
                    }
                    if (rbtuto.isChecked()) {
                        rbtuto.toggle();
                    }
                }
            }
        });

        rbstud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=2;
                if(rbstud.isChecked()){
                    if (rbprof.isChecked()){
                        rbprof.toggle();
                    }
                    if (rbtuto.isChecked()) {
                        rbtuto.toggle();
                    }
                }
            }
        });

        rbtuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=3;
                if(rbtuto.isChecked()){
                    if (rbstud.isChecked()){
                        rbstud.toggle();
                    }
                    if (rbprof.isChecked()) {
                        rbprof.toggle();
                    }
                }
            }
        });

        accediqui.setPaintFlags(accediqui.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        fdbr = FirebaseDatabase.getInstance().getReference().child("Utenti");

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utente u = new Utente();
                switch(i){
                    case 1:
                        u.ruolo = "Professore";
                        fdbr.child("email_utente_x").setValue(u);
                        Intent j = new Intent(RegistrazioneActivity.this, HomeProfessoreActivity.class);
                        startActivity(j);
                        break;

                    case 2:
                        u.ruolo = "Studente";
                        fdbr.child("email_utente_x").setValue(u);
                        Intent k = new Intent(RegistrazioneActivity.this, HomeActivity.class);
                        startActivity(k);
                        break;

                    case 3:
                        u.ruolo ="Tutor";
                        break;

                    case 0:
                        break;
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        
    }
}