package com.example.unistage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DettagliActivity extends AppCompatActivity {
    public static String luogo_s;
    public static String responsabile_s;
    public static int data_s;
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static void setDetail(ModuloPropostaTirocinio moduloPropostaTirocinio){
        luogo_s = moduloPropostaTirocinio.luogo;
        responsabile_s = moduloPropostaTirocinio.docente;
        data_s = moduloPropostaTirocinio.durata;
        System.out.println(moduloPropostaTirocinio);
    }
}