package com.example.unistage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.widget.LinearLayout.VERTICAL;

public class TirocinioDuranteFrag extends Fragment {

    private DatabaseReference fdbr;
    String email, nome, cognome;
    String obb;
    View frag_on_create_view;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        frag_on_create_view = inflater.inflate(R.layout.fragment_tirocinio_durante, container, false);

        DatabaseReference fdbr = FirebaseDatabase.getInstance().getReference().child("Utenti").child("Professori").child(LoginActivity.u_loggato.tirocinio_in_corso.docente);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                email = dataSnapshot.child("email").getValue(String.class);
                nome = dataSnapshot.child("nome").getValue(String.class);
                cognome = dataSnapshot.child("cognome").getValue(String.class);

                final TextView email_docente = frag_on_create_view.findViewById(R.id.card_email_tutor_universitario_id);
                final TextView docente = frag_on_create_view.findViewById(R.id.card_tutor_universitario_id);

                String nome_completo = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase() + " " + cognome.substring(0, 1).toUpperCase() + cognome.substring(1).toLowerCase();

                email_docente.setText(email);
                docente.setText(nome_completo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };
        fdbr.addValueEventListener(postListener);

        final TextView obiettivi_formativi = frag_on_create_view.findViewById(R.id.obiettivo_formativo);
        obiettivi_formativi.setText(LoginActivity.u_loggato.tirocinio_in_corso.listaObiettivi);

        final TextView descrizione = frag_on_create_view.findViewById(R.id.descrizione);
        descrizione.setText(LoginActivity.u_loggato.tirocinio_in_corso.descrizione);

        final TextView azienda = frag_on_create_view.findViewById(R.id.card_azienda_id);
        azienda.setText(LoginActivity.u_loggato.tirocinio_in_corso.luogo);

        final TextView data_inizio = frag_on_create_view.findViewById(R.id.card_data_inizio_id);
        data_inizio.setText(LoginActivity.u_loggato.tirocinio_in_corso.dataInizio);

        final TextView data_fine = frag_on_create_view.findViewById(R.id.card_data_fine_id);
        data_fine.setText(LoginActivity.u_loggato.tirocinio_in_corso.dataFine);

        final TextView tipo_tirocinio = frag_on_create_view.findViewById(R.id.card_tipotirocinio_id);
        final int tipo = LoginActivity.u_loggato.tirocinio_in_corso.tipologia;
        if(tipo == 0)
            tipo_tirocinio.setText("INTERNO");
        else
            tipo_tirocinio.setText("ESTERNO");



        return frag_on_create_view;

    }
}