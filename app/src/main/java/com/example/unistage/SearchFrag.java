package com.example.unistage;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.widget.LinearLayout.VERTICAL;

public class SearchFrag extends Fragment {

    public static ArrayList<ModuloPropostaTirocinio> tirocini_salvati;
    public static ArrayList<ModuloPropostaTirocinio> tirocini_proposti;
    View frag_on_crate_view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public SearchFrag() { }

    public SearchFrag(ArrayList<ModuloPropostaTirocinio> a){
        tirocini_proposti = a;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        frag_on_crate_view = inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView rv = (RecyclerView) frag_on_crate_view.findViewById(R.id.recycler_homefrag_id);
        tirocini_proposti = LoginActivity.listaTirociniProposti;
        System.out.println("Grandezza array: " + LoginActivity.listaTirociniProposti.size());

        final AdapterTirociniStudente listAdapterTirociniStudente = new AdapterTirociniStudente(tirocini_proposti);
        rv.setAdapter(listAdapterTirociniStudente);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        listAdapterTirociniStudente.setOnItemClickListener(new AdapterTirociniStudente.OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: Cliccata card");
            }

            @Override
            public void onSaveClick(int position) {
                tirocini_salvati.add(LoginActivity.listaTirociniProposti.get(position));
                LoginActivity.listaTirociniProposti.remove(position);
                listAdapterTirociniStudente.notifyItemChanged(position);
            }

            @Override
            public void onDetailClick(int position) {
                Intent i = new Intent(getActivity(), DettagliActivity.class);
                DettagliActivity.setDetail(LoginActivity.listaTirociniProposti.get(position));
                startActivity(i);
            }
        });

        return frag_on_crate_view;
    }


}
