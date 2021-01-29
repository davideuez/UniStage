package com.example.unistage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModificaTask extends AppCompatActivity {

    Task x;
    int posizione;
    private DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_task);

        Intent i = getIntent();
        posizione = i.getIntExtra("position", -1);

        TextView nomeStud = findViewById(R.id.nome_stud);
        TextView matricolaT = findViewById(R.id.matricola);
        final EditText titolo = findViewById(R.id.titolo_mod_task);
        final EditText assegnatoIl = findViewById(R.id.assegnatoIl_mod_task);
        final EditText entroIl = findViewById(R.id.entroIl_mod_task);
        final EditText descrizione = findViewById(R.id.descrizione_mod_task);
        Button elimina = findViewById(R.id.elimina_task);
        Button salva = findViewById(R.id.salva_task);
        ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        x = LoginActivity.listaTask.get(GestisciTask.posizione).get(posizione);

        nomeStud.setText(GestisciTirocinio.x.getStudente());
        matricolaT.setText("Mat. " + String.valueOf(GestisciTask.matricola));
        titolo.setText(x.getTitolo());
        assegnatoIl.setText(x.getAssegnataIl());
        entroIl.setText(x.getDataScadenza());
        descrizione.setText(x.getDescrizione());

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginActivity.listaTask.get(GestisciTask.posizione).remove(posizione);
                dbref.child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_avviati").child(GestisciTirocinio.x.getTitolo()).child("listaTask").child(x.getTitolo()).setValue(null);
                dbref.child("Utenti").child("Studenti").child(String.valueOf(GestisciTask.matricola)).child("tirocinio_in_corso").child(GestisciTirocinio.x.getTitolo()).child("listaTask").child(x.getTitolo()).setValue(null);

                Intent i = new Intent(ModificaTask.this, GestisciTask.class);
                i.putExtra("posizione", GestisciTask.posizione);
                i.putExtra("matricola", GestisciTask.matricola);
                startActivity(i);

            }
        });




    }

}
