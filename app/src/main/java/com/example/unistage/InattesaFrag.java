package com.example.unistage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.widget.LinearLayout.VERTICAL;

public class InattesaFrag extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frag_on_crate_view = inflater.inflate(R.layout.fragment_inattesa, container, false);

        RecyclerView rv = (RecyclerView) frag_on_crate_view.findViewById(R.id.recycler_inattesa_id);
        final AdapterTirociniProfessore listAdapter = new AdapterTirociniProfessore(Walkthrough1Activity.moduloPropostaTirocinio);
        rv.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        listAdapter.SetTheClick(new AdapterTirociniProfessore.ClickedListener() {
            @Override
            public void ClickDettagli(int position) {
                DettagliActivity.setDetail(Walkthrough1Activity.moduloPropostaTirocinio.get(position));
                Intent i = new Intent(getActivity(),DettagliActivity.class);
                startActivity(i);
            }
        });

        return frag_on_crate_view;
    }
}