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

public class FragmentTirociniProposti extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frag_on_crate_view = inflater.inflate(R.layout.fragment_tirocini_proposti, container, false);

        RecyclerView rv = (RecyclerView) frag_on_crate_view.findViewById(R.id.recycler_proposti_id);
        final AdapterTirociniStudente listAdapter = new AdapterTirociniStudente(LoginActivity.listaTirociniPropostiSingle);
        rv.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);


        listAdapter.notifyDataSetChanged();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        listAdapter.setOnItemClickListener(new AdapterTirociniStudente.OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: Cliccata card");
            }

            @Override
            public void onSaveClick(int position) {

            }

            @Override
            public void onDetailClick(int position) {
                Intent i = new Intent(getActivity(), DettagliTirocinioProposto.class);
                i.putExtra("position", position);
                startActivity(i);
            }
        });

        return frag_on_crate_view;
    }
}