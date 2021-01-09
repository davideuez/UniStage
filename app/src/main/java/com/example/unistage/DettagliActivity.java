package com.example.unistage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DettagliActivity extends AppCompatActivity {
    public static String luogo_s;
    public static String responsabile_s;
    public static int data_s;
    public static ModuloPropostaTirocinio mpt;
    private DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettagli);

        final TextView luogo = findViewById(R.id.luogo_dettagli_id);
        luogo.setText(luogo_s);
        final TextView responsabile = findViewById(R.id.responsabile_dettagli_id);
        responsabile.setText(responsabile_s);
        final TextView data = findViewById(R.id.apertura_iscrizioni_dettagli_id);
        data.setText(String.valueOf(data_s));
        final ImageButton back = findViewById(R.id.back_button_dettagli);
        final Button salva = findViewById(R.id.salva_dettagli_id);
        final Button candidati = findViewById(R.id.candidati_dettagli_id);

        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModuloPropostaTirocinio t = new ModuloPropostaTirocinio();
                t = mpt;
                System.out.println(t);
                System.out.println(RegistrazioneActivity.u);
                dbref.child("Utenti").child(RegistrazioneActivity.u.getEmail()).child("tirocini salvati").child(t.getTitolo()+" a "+t.getLuogo()).setValue(t);

                RegistrazioneActivity.u.tirocini_salvati.add(t);
                finish();
            }
        });

        candidati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
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

    public void openDialog() {
        PopUp exampleDialog = new PopUp();
        exampleDialog.show(getSupportFragmentManager(), "popup");
    }
}