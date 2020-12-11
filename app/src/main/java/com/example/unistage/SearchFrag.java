package com.example.unistage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.widget.LinearLayout.VERTICAL;

public class SearchFrag extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View frag_on_crate_view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView rv = (RecyclerView) frag_on_crate_view.findViewById(R.id.recycler_homefrag_id);

        Adapter listAdapter = new Adapter(Walkthrough1Activity.home_card_itiems_prefe);
        rv.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        return frag_on_crate_view;
    }
}