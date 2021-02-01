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


public class FragmentTirociniCandidati extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tirocini_candidati, container, false);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.card_candidature_tirocini);

        final AdapterCandidature listAdapterCandidature = new AdapterCandidature(LoginActivity.listaTirociniCandidati);
        rv.setAdapter(listAdapterCandidature);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        listAdapterCandidature.setOnItemClickListener(new AdapterCandidature.OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: Cliccata card");
            }

            @Override
            public void onSaveClick(int position) {
                SearchFrag.tirocini_salvati.add(LoginActivity.listaTirociniProposti.get(position));
                LoginActivity.listaTirociniProposti.remove(position);
                listAdapterCandidature.notifyItemChanged(position);
            }

            @Override
            public void onDetailClick(int position) {
                Intent i = new Intent(getActivity(), DettagliActivity.class);
                DettagliActivity.setDetail(LoginActivity.listaTirociniProposti.get(position));
                startActivity(i);
            }
        });

        return v;
    }
}