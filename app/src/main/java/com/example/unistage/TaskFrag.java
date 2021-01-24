package com.example.unistage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

public class TaskFrag extends Fragment {

    String commentoText;
    View v = null;
    int posizione;
    private DatabaseReference tasks;
    ModuloPropostaTirocinio x;

    public static ArrayList<Task> listaTask = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_tasks, container, false);

        HomeStudenteDURANTEActivity.mTask = LoginActivity.u_loggato.tirocinio_in_corso.listaTask;
        /* Intent i = new Intent();
        posizione = i.getIntExtra("posizione", -1);
        x = LoginActivity.listaTirocini.get(posizione);

        tasks = FirebaseDatabase.getInstance().getReference().child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_avviati").child(x.getTitolo()).child("Tasks");
        tasks.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Task y = snapshot.getValue(Task.class);
                //listaTask.add(y);
                System.out.println("Tasks: " + y);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

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
                i.putExtra("posizione",position);
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



