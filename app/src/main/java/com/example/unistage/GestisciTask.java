package com.example.unistage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.widget.LinearLayout.VERTICAL;

public class GestisciTask extends AppCompatActivity {

    public static int posizione, matricola;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestisci_task);

        Intent i = getIntent();
        posizione = i.getIntExtra("posizione", -1);
        matricola = i.getIntExtra("matricola", -1);


        TextView nomeStud = findViewById(R.id.nome_stud);
        TextView matricolaT = findViewById(R.id.matricola);
        TextView taskAssegnati = findViewById(R.id.task_assegnati);
        TextView taskCompletati = findViewById(R.id.task_completati);
        ImageButton aggTask = findViewById(R.id.aggiungitask);
        ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);

        ModuloPropostaTirocinio x = LoginActivity.listaTirocini.get(posizione);

        int taskCompl = contaCompletati(LoginActivity.listaTask.get(posizione));

        taskAssegnati.setText(String.valueOf(LoginActivity.listaTask.get(posizione).size()));
        taskCompletati.setText(String.valueOf(taskCompl));
        matricolaT.setText("Mat. " + matricola);
        nomeStud.setText(x.getStudente());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent j = new Intent(GestisciTask.this, GestisciTirocinio.class);
                j.putExtra("posizione", posizione);
                j.putExtra("matricola", matricola);
                startActivity(j);

            }
        });

        aggTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent j = new Intent(GestisciTask.this, AggiungiTask.class);
                j.putExtra("posizione", posizione);
                j.putExtra("matricola", matricola);
                startActivity(j);

            }
        });

        final RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_task);
        final AdapterTask listAdapterTask = new AdapterTask(LoginActivity.listaTask.get(posizione));
        rv.setAdapter(listAdapterTask);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        listAdapterTask.setOnItemClickListener(new AdapterTask.OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {

                System.out.println("Cliccata card " + position);

                Intent i = new Intent(GestisciTask.this, ModificaTask.class);
                i.putExtra("position", position);
                startActivity(i);

            }

            @Override
            public void onSaveClick(int position) {

            }

            @Override
            public void onDetailClick(int position) {

            }
        });

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);


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
