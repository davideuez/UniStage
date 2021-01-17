package com.example.unistage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.widget.LinearLayout.VERTICAL;

public class T_incorso_professore extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_t_incorso_professore, container, false);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.card_gestione_tirocini);

        final AdapterTirociniProfessore listAdapterTirociniProfessore = new AdapterTirociniProfessore(Walkthrough1Activity.listaTirocini);
        rv.setAdapter(listAdapterTirociniProfessore);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        listAdapterTirociniProfessore.SetTheClick(new AdapterTirociniProfessore.OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: Cliccata card " + position);
                Intent i = new Intent(getActivity(), GestisciTirocinio.class);
                i.putExtra("posizione", position);
                startActivity(i);
            }

            @Override
            public void onSaveClick(int position) {
                Log.d(TAG, "onItemClick: Cliccata card " + position);
            }

            @Override
            public void onDetailClick(int position) {

            }
        });

        return v;
    }
}