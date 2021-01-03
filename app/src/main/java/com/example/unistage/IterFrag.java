package com.example.unistage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class IterFrag extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_itertirociniodurante, container, false);

        final TextView interno = (TextView)v.findViewById(R.id.tirocinio_interno);
        interno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.container_iter, new IterInternoFrag()).commit();
            }
        });

        final TextView esterno = (TextView)v.findViewById(R.id.tirocinio_esterno);
        esterno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.container_iter, new IterEsternoFrag()).commit();
            }
        });

        return v;


    }
}
