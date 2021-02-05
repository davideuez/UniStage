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

import static android.content.ContentValues.TAG;
import static android.widget.LinearLayout.VERTICAL;

public class FragmentSalvatiStudente extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frag_on_crate_view = inflater.inflate(R.layout.fragment_salvati_studente, container, false);

        RecyclerView rv = (RecyclerView) frag_on_crate_view.findViewById(R.id.recycler_salvatistudente_id);

        final AdapterTirociniStudente listAdapterTirociniStudente = new AdapterTirociniStudente(LoginActivity.lista_tirocini_salvati);
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
                SearchFrag.tirocini_salvati.add(LoginActivity.listaTirociniProposti.get(position));
                LoginActivity.listaTirociniProposti.remove(position);
                listAdapterTirociniStudente.notifyItemChanged(position);
            }

            @Override
            public void onDetailClick(int position) {
                Intent i = new Intent(getActivity(), DettagliActivity.class);
                i.putExtra("posizione", position);
                startActivity(i);
            }
        });


        return frag_on_crate_view;
    }
}