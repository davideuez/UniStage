package com.example.unistage;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

        final EditText commento = v.findViewById(R.id.commento);
        final ImageView commenta = v.findViewById(R.id.commenta);
        final ArrayList<String> mComment = new ArrayList<String>();


        commenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                commentoText = commento.getText().toString();

                if(!TextUtils.isEmpty(commentoText)){

                    mComment.add(commento.getText().toString());
                    RecyclerView rv = (RecyclerView) v.findViewById(R.id.recycler_commenttask);

                    final AdapterCommenti listAdapterCommenti = new AdapterCommenti(mComment);
                    rv.setAdapter(listAdapterCommenti);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(layoutManager);

                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
                    rv.addItemDecoration(dividerItemDecoration);

                }
                else{
                    Toast.makeText(getActivity(), "Commento vuoto", Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }

}
