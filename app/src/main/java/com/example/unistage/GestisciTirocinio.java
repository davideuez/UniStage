package com.example.unistage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GestisciTirocinio extends AppCompatActivity {

    int posizione;
    int matrix;
    private DatabaseReference tasks;
    ModuloPropostaTirocinio x;

    public static ArrayList<Task> listaTask = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestisci_tirocinio);

        TextView nomeStudente = findViewById(R.id.nome_studente);
        TextView nomeStud = findViewById(R.id.nome_stud);
        TextView matricola = findViewById(R.id.matricola);
        TextView tipologia = findViewById(R.id.tipologia);
        TextView nome_azienda = findViewById(R.id.nome_azienda);
        TextView dataFine = findViewById(R.id.dataFine);
        TextView taskAssegnati = findViewById(R.id.task_assegnati);
        TextView taskComp = findViewById(R.id.task_completati);
        Button details = findViewById(R.id.vedi_dettagli);
        Button gestTask = findViewById(R.id.card_gestiscitask_button);
        ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);

        Intent i = getIntent();
        posizione = i.getIntExtra("posizione", -1);

        if(posizione != -1){

            x = LoginActivity.listaTirocini.get(posizione);

            init();

            nomeStudente.setText(x.getStudente());
            nomeStud.setText(x.getStudente());


            String[] separated = x.getStudente().split(" ");
            System.out.println("Cognome x matricola: " + separated[1].toLowerCase());

            matrix = getMatricola(separated[1].toLowerCase());
            matricola.setText("Mat. " + matrix);

            taskAssegnati.setText(String.valueOf(LoginActivity.listaTask.get(posizione).size()));
            taskComp.setText(String.valueOf(contaCompletati(LoginActivity.listaTask.get(posizione))));

            if(x.getTipologia() == 0) {
                tipologia.setText("INTERNO");
            } else {
                tipologia.setText("ESTERNO");
            }

            nome_azienda.setText(x.getLuogo());
            dataFine.setText(x.getDataFine());

            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent j = new Intent(GestisciTirocinio.this, DettagliTirocinio.class);
                    j.putExtra("posizione", posizione);
                    j.putExtra("matricola", matrix);
                    startActivity(j);
                }
            });

            gestTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent k = new Intent(GestisciTirocinio.this, GestisciTask.class);
                    k.putExtra("posizione", posizione);
                    k.putExtra("matricola", matrix);
                    startActivity(k);
                }
            });

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent k = new Intent(GestisciTirocinio.this, Tirocini_attivi_professore.class);
                    startActivity(k);
                }
            });


        } else {

        }


    }

    public int getMatricola(String cognomeI) {

        for(int i=0; i<LoginActivity.listaUtenti.size(); i++){

            if(cognomeI.equals(LoginActivity.listaUtenti.get(i).getCognome())){
                System.out.println("cognomi coincidono " + cognomeI);
                System.out.println("Matricola" + LoginActivity.listaUtenti.get(i).getMatricola());
                return LoginActivity.listaUtenti.get(i).getMatricola();


            } else {
                System.out.println("Cognome non presente");
            }
        }

        return -1;

    }

    void init() {
        listaTask.clear();
        tasks = FirebaseDatabase.getInstance().getReference().child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_avviati").child(x.getTitolo()).child("listaTask");
        System.out.println(tasks.toString());
        tasks.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Task y = snapshot.getValue(Task.class);
                listaTask.add(y);
                System.out.println("Tasks: " + y);
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

    public int contaCompletati(ArrayList<Task> x){

        int j=0;

        for(int i=0; i<x.size(); i++) {

            if(x.get(i).getCompletata() == 1){
                j++;
            }

        }

        return j;

    }

}
