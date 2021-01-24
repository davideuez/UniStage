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

import static android.widget.LinearLayout.VERTICAL;

public class GestisciTask extends AppCompatActivity {

    int posizione, matricola;

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
        ImageButton back = findViewById(R.id.backarrow_proposta_tirocinio_id);

        ModuloPropostaTirocinio x = LoginActivity.listaTirocini.get(posizione);

        taskAssegnati.setText(GestisciTirocinio.listaTask.size());
        matricolaT.setText("Mat. " + matricola);
        nomeStud.setText(x.getStudente());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_task);
        final AdapterTask listAdapterTask = new AdapterTask(GestisciTirocinio.listaTask);
        rv.setAdapter(listAdapterTask);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);


    }
}
