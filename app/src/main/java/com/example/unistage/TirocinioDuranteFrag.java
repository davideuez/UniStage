package com.example.unistage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.widget.LinearLayout.VERTICAL;

public class TirocinioDuranteFrag extends Fragment {

    public static ModuloPropostaTirocinio tirocinio_attivo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frag_on_create_view = inflater.inflate(R.layout.fragment_tirocinio_durante, container, false);
        final TextView azienda = frag_on_create_view.findViewById(R.id.card_azienda_id);
        azienda.setText(RegistrazioneActivity.u.tirocinio_in_corso.luogo.toString());

        final TextView docente = frag_on_create_view.findViewById(R.id.card_tutor_universitario_id);
        docente.setText(RegistrazioneActivity.u.tirocinio_in_corso.docente.toString());
        System.out.println(RegistrazioneActivity.u.tirocinio_in_corso.toString());

        return frag_on_create_view;

    }
}