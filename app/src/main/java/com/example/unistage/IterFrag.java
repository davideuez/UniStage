package com.example.unistage;

import android.graphics.Color;
import android.graphics.Typeface;
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

        final Typeface bold = Typeface.createFromAsset(getActivity().getAssets(), "montserrat_semibold.ttf");
        final Typeface regular = Typeface.createFromAsset(getActivity().getAssets(), "montserrat_regular.ttf");

        final TextView esterno = (TextView)v.findViewById(R.id.tirocinio_esterno);
        final TextView interno = (TextView)v.findViewById(R.id.tirocinio_interno);

        getChildFragmentManager().beginTransaction().replace(R.id.container_iter, new IterInternoFrag()).commit();
        interno.setTextColor(Color.BLACK);
        interno.setTypeface(bold);
        
        interno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.container_iter, new IterInternoFrag()).commit();
                interno.setTextColor(Color.BLACK);
                esterno.setTextColor(Color.GRAY);
                interno.setTypeface(bold);
                esterno.setTypeface(regular);
            }
        });

        esterno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildFragmentManager().beginTransaction().replace(R.id.container_iter, new IterEsternoFrag()).commit();
                interno.setTextColor(Color.GRAY);
                esterno.setTextColor(Color.BLACK);
                interno.setTypeface(regular);
                esterno.setTypeface(bold);
            }
        });

        return v;


    }
}
