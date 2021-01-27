package com.example.unistage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AggiungiTask extends AppCompatActivity {

    int posizione, matricola;
    private DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_task);

        Intent i = getIntent();
        posizione = i.getIntExtra("posizione", -1);
        matricola = i.getIntExtra("matricola", -1);

        TextView nomeStud = findViewById(R.id.nome_stud);
        TextView matricolaT = findViewById(R.id.matricola);
        final EditText titolo = findViewById(R.id.titolo_new_task);
        final EditText assegnatoIl = findViewById(R.id.assegnatoIl_new_task);
        final EditText entroIl = findViewById(R.id.entroIl_new_task);
        final EditText descrizione = findViewById(R.id.descrizione_new_task);
        Button annulla = findViewById(R.id.annulla_task_new);
        Button assegna = findViewById(R.id.assegna_new);
        ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);

        ModuloPropostaTirocinio x = LoginActivity.listaTirocini.get(posizione);

        matricolaT.setText("Mat. " + matricola);
        nomeStud.setText(x.getStudente());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        annulla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        assegna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ModuloPropostaTirocinio y = LoginActivity.listaTirocini.get(posizione);
                Task x = new Task();

                x.setTitolo(titolo.getText().toString());
                x.setAssegnataIl(assegnatoIl.getText().toString());
                x.setDataScadenza(entroIl.getText().toString());
                x.setDescrizione(descrizione.getText().toString());
                x.setCompletata(0);

                LoginActivity.listaTask.get(posizione).add(x);
                //dbref.child("Utenti").child("Studenti").child(LoginActivity.u_loggato.getMatricola()+"").child("tirocinio_avviato").setValue(true);
                dbref.child("Utenti").child("Studenti").child(matricola+"").child("tirocinio_in_corso").child(y.getTitolo()).child("listaTask").child(x.titolo).setValue(x);
                dbref.child("Utenti").child("Professori").child(y.docente).child("Tirocini_avviati").child(y.getTitolo()).child("listaTask").child(x.titolo).setValue(x);

                Toast.makeText(AggiungiTask.this, "Task assegnato", Toast.LENGTH_LONG).show();

                Intent j = new Intent(AggiungiTask.this, GestisciTirocinio.class);
                j.putExtra("posizione", posizione);
                j.putExtra("matricola", matricola);
                startActivity(j);

            }
        });

    }
}