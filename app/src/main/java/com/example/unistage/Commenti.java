package com.example.unistage;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Commenti extends AppCompatActivity {
    String commentoText;
    int posizione;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent i = getIntent();
        posizione = i.getIntExtra("posizione", -1);

        Task t = LoginActivity.u_loggato.tirocinio_in_corso.listaTask.get(posizione);
        System.out.println(t.toString());

        final TextView titolo = findViewById(R.id.titolo);
        titolo.setText(t.getTitolo());
        final TextView data_inizio = findViewById(R.id.card_data_inizio);
        data_inizio.setText(t.getAssegnataIl());
        final TextView data_fine = findViewById(R.id.card_data_fine);
        data_fine.setText(t.getDataScadenza());
        final TextView descrizione = findViewById(R.id.descrizione_tirocinio_dettagli_id);
        //descrizione.setText(t.getDescrizione());
        final ImageView checkComment = findViewById(R.id.check_comment);
        if(t.getCompletata() == 0)
            checkComment.setImageResource(R.drawable.listgray);


        final EditText commento = findViewById(R.id.commento);
        final ImageView commenta = findViewById(R.id.commenta);
        final ArrayList<String> mComment = new ArrayList<String>();

        commenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentoText = commento.getText().toString();
                if(!TextUtils.isEmpty(commentoText)){

                    mComment.add(commentoText);
                    System.out.println(mComment.toString());
                }
                else{
                    Toast.makeText(Commenti.this, "Commento vuoto", Toast.LENGTH_LONG).show();
                }
            }
        });

        final RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_commenttask);
        final AdapterCommenti listAdapterCommenti = new AdapterCommenti(mComment);
        rv.setAdapter(listAdapterCommenti);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Commenti.this);
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

    }
}
