package com.example.unistage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static android.widget.LinearLayout.VERTICAL;

public class HomeFrag extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frag_on_crate_view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rv = (RecyclerView) frag_on_crate_view.findViewById(R.id.recycler_homefrag_id);
        final Adapter listAdapter = new Adapter(Walkthrough1Activity.home_card_itiems);
        rv.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        listAdapter.setOnItemClickListener(new Adapter.OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                Walkthrough1Activity.home_card_itiems.get(position).setTitolo("a dave piace il ciccio nero");
                listAdapter.notifyItemChanged(position);
            }

            @Override
            public void onSaveClick(int position) {
                Walkthrough1Activity.home_card_itiems_prefe.add(Walkthrough1Activity.home_card_itiems.get(position));
                Walkthrough1Activity.home_card_itiems.remove(position);
                listAdapter.notifyItemChanged(position);
            }

            @Override
            public void onDetailClick(int position) {
                Intent i = new Intent(getActivity(), PropostaTirocinioActivity.class);
                startActivity(i);
            }
        });

        return frag_on_crate_view;
    }

}
