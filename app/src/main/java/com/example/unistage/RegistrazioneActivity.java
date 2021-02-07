package com.example.unistage;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class RegistrazioneActivity extends AppCompatActivity {

    private DatabaseReference fdbr;
    String emailText, pswText, pswConfermaText;

    public static int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        final RadioButton rbprof = findViewById(R.id.profrb_signup_id);
        final RadioButton rbstud = findViewById(R.id.studenterb_signup_id);
        final Button reg = findViewById(R.id.registbutton_signup_id);
        final TextView accediqui = findViewById(R.id.accediqui);

        final TextInputEditText email = findViewById(R.id.registrazione_email);
        final TextInputEditText psw = findViewById(R.id.registrazione_password);
        final TextInputEditText pswConferma = findViewById(R.id.registrazione_confermapassword);


        rbprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
                rbstud.setChecked(false);
            }
        });

        rbstud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=2;
                rbprof.setChecked(false);
            }
        });

        accediqui.setPaintFlags(accediqui.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        accediqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrazioneActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        fdbr = FirebaseDatabase.getInstance().getReference().child("Utenti");

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pswText = psw.getText().toString();
                emailText = email.getText().toString();
                pswConfermaText = pswConferma.getText().toString();

                if(i == 1) {

                    if (!TextUtils.isEmpty(pswText) || !TextUtils.isEmpty(emailText) || !TextUtils.isEmpty(pswConfermaText)) {
                        if (pswText.equals(pswConfermaText)) {

                            String[] separaChiocciola = emailText.split("@");
                            String[] separa = separaChiocciola[0].split("\\.");

                            String nome = separa[0].toString();
                            String cognome = separa[1].toString();

                            LoginActivity.u_loggato = new Utente(emailText, nome, cognome, pswText);

                            fdbr.child("Professori").child(LoginActivity.u_loggato.getCognome()).setValue(LoginActivity.u_loggato);
                            Intent j = new Intent(RegistrazioneActivity.this, Tirocini_attivi_professore.class);
                            startActivity(j);

                        } else
                            Toast.makeText(RegistrazioneActivity.this, "Le password non coincidono", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegistrazioneActivity.this, "Completa tutti i campi", Toast.LENGTH_LONG).show();
                    }

                }


                if(i == 2) {

                    if (!TextUtils.isEmpty(pswText) || !TextUtils.isEmpty(emailText) || !TextUtils.isEmpty(pswConfermaText)) {
                        if (pswText.equals(pswConfermaText)) {

                            String[] separaChiocciola = emailText.split("@");
                            String[] separa = separaChiocciola[0].split("\\.");


                            String nome = separa[0].toString();
                            String cognome = separa[1].toString();

                            final Random myRandom = new Random();
                            int matricola = myRandom.nextInt(987654 - 123456) + 123456;

                            LoginActivity.u_loggato = new Utente(nome, cognome, emailText, pswText, matricola);

                            fdbr.child("Studenti").child(LoginActivity.u_loggato.matricola+"").setValue(LoginActivity.u_loggato);
                            Intent k = new Intent(RegistrazioneActivity.this, HomeStudentePREActivity.class);
                            startActivity(k);

                        } else
                            Toast.makeText(RegistrazioneActivity.this, "Le password non coincidono.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegistrazioneActivity.this, "Inserisci tutti i valori.", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}
