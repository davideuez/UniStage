package com.example.unistage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PropostaTirocinioActivity extends AppCompatActivity {
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposta_tirocinio);

        final ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);
        final Button inoltra = findViewById(R.id.inoltra_button_tirocinio_proposta_id);
        final TextView titolo = findViewById(R.id.titolo_tirocinio_proposta_id);
        final TextView docente = findViewById(R.id.docente_tirocinio_proposta_id);
        final TextView durata = findViewById(R.id.durata_tirocinio_proposta_id);
        final TextView cfu = findViewById(R.id.cfu_tirocinio_proposta_id);
        final TextView descrizione = findViewById(R.id.descrizione_tirocinio_proposta_id);
        final TextView luogo = findViewById(R.id.luogo_tirocinio_proposta_id);

        final Intent extra = getIntent();
        final int k = extra.getIntExtra("pos",0);

        reference = FirebaseDatabase.getInstance().getReference().child("Tirocini_Proposti_Professori");

        inoltra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final ModuloPropostaTirocinio moduloPropostaTirocinio = new ModuloPropostaTirocinio(titolo.getText().toString(), luogo.getText().toString(), Integer.parseInt(cfu.getText().toString()), Integer.parseInt(durata.getText().toString()),  descrizione.getText().toString());
                    reference.child(titolo.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            System.out.println("Datatchange");
                            if (snapshot.exists()) {
                                Toast.makeText(PropostaTirocinioActivity.this, "Titolo non valido, scegliere un altro titolo", Toast.LENGTH_LONG).show();
                            } else if (!snapshot.exists()) {
                                reference.child(titolo.getText().toString()).setValue(moduloPropostaTirocinio);
                                Intent e = new Intent(PropostaTirocinioActivity.this, HomeProfessoreActivity.class);
                                startActivity(e);
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                catch (Exception np){
                    Toast.makeText(PropostaTirocinioActivity.this, "Completare tutti i campi", Toast.LENGTH_LONG).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}