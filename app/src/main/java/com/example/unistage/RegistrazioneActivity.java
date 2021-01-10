package com.example.unistage;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrazioneActivity extends AppCompatActivity {
    public static Utente u;
    private DatabaseReference fdbr;
    String emailText, pswText, pswConfermaText;

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

        final TextInputEditText email = findViewById(R.id.registrazione_email);
        final TextInputEditText psw = findViewById(R.id.registrazione_password);
        final TextInputEditText pswConferma = findViewById(R.id.registrazione_confermapassword);


        rbprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
                rbstud.setChecked(false);
                rbtuto.setChecked(false);
            }
        });

        rbstud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=2;
                rbprof.setChecked(false);
                rbtuto.setChecked(false);
            }
        });

        rbtuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=3;
                rbstud.setChecked(false);
                rbprof.setChecked(false);
            }
        });

        accediqui.setPaintFlags(accediqui.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        fdbr = FirebaseDatabase.getInstance().getReference().child("Utenti");

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = new Utente();
                pswText = psw.getText().toString();
                emailText = email.getText().toString();
                pswConfermaText = pswConferma.getText().toString();

                if(i == 1) {

                    if (!TextUtils.isEmpty(pswText) || !TextUtils.isEmpty(emailText) || !TextUtils.isEmpty(pswConfermaText)) {
                        if (pswText.equals(pswConfermaText)) {
                            u.ruolo = "Professore";
                            fdbr.child("email_utente_x").setValue(u);
                            Intent j = new Intent(RegistrazioneActivity.this, Tirocini_attivi_professore.class);
                            startActivity(j);

                        } else
                            Toast.makeText(RegistrazioneActivity.this, "Le password non coincidono.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegistrazioneActivity.this, "Inserisci tutti i valori.", Toast.LENGTH_LONG).show();
                    }

                }


                if(i == 2) {

                    if (!TextUtils.isEmpty(pswText) || !TextUtils.isEmpty(emailText) || !TextUtils.isEmpty(pswConfermaText)) {
                        if (pswText.equals(pswConfermaText)) {
                            u.setEmail(emailText);
                            u.setPassword(pswText);
                            u.ruolo = "Studente";
                            fdbr.child(u.email).setValue(u);
                            Intent k = new Intent(RegistrazioneActivity.this, HomeStudentePREActivity.class);
                            startActivity(k);

                            } else
                                Toast.makeText(RegistrazioneActivity.this, "Le password non coincidono.", Toast.LENGTH_LONG).show();
                        } else {
                        Toast.makeText(RegistrazioneActivity.this, "Inserisci tutti i valori.", Toast.LENGTH_LONG).show();
                    }

                }

                if(i == 3) {

                    if (!TextUtils.isEmpty(pswText) || !TextUtils.isEmpty(emailText) || !TextUtils.isEmpty(pswConfermaText)) {
                        if (pswText.equals(pswConfermaText)) {
                            u.ruolo = "Tutor";
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