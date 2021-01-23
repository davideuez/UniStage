package com.example.unistage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;
import static android.widget.LinearLayout.VERTICAL;

public class TaskFrag extends Fragment {

    String commentoText;
    View v = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tasks, container, false);

        HomeStudenteDURANTEActivity.mTask = LoginActivity.u_loggato.tirocinio_in_corso.listaTask;
        System.out.println(LoginActivity.u_loggato.tirocinio_in_corso.listaTask);

        final RecyclerView rv = (RecyclerView) v.findViewById(R.id.recycler_taskfrag);
        final AdapterTask listAdapterTask = new AdapterTask(HomeStudenteDURANTEActivity.mTask);
        rv.setAdapter(listAdapterTask);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        listAdapterTask.setOnItemClickListener(new AdapterTask.OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: Cliccata card");
                Context context = v.getContext();
                Intent i = new Intent(context, Commenti.class);
                context.getApplicationContext().startActivity(i);
            }

            @Override
            public void onSaveClick(int position) {

            }

            @Override
            public void onDetailClick(int position) {

            }
        });
        return v;
    }

}