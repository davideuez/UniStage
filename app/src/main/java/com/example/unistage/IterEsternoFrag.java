package com.example.unistage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.fragment.app.Fragment;

public class IterEsternoFrag extends Fragment {

    CheckBox c1, c2, c3, c4, c5, c6, c7, c8, c9;
    SharedPreferences myPrefs;
    SharedPreferences.Editor myPrefsPrefsEditor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_iteresterno, container, false);

        c1 = v.findViewById(R.id.checkbox1);
        c2 = v.findViewById(R.id.checkbox2);
        c3 = v.findViewById(R.id.checkbox3);
        c4 = v.findViewById(R.id.checkbox4);
        c5 = v.findViewById(R.id.checkbox5);
        c6 = v.findViewById(R.id.checkbox6);
        c7 = v.findViewById(R.id.checkbox7);
        c8 = v.findViewById(R.id.checkbox8);
        c9 = v.findViewById(R.id.checkbox9);

        final String MY_SHARED_PREF = "Iter_esterno";

        myPrefs = getActivity().getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE);
        myPrefsPrefsEditor = myPrefs.edit();

        updateStatus();

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (c1.isChecked()){
                    myPrefsPrefsEditor.putBoolean("c1", true);
                    myPrefsPrefsEditor.commit();
                } else{
                    myPrefsPrefsEditor.putBoolean("c1", false);
                    myPrefsPrefsEditor.commit();
                }


            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (c2.isChecked()){
                    myPrefsPrefsEditor.putBoolean("c2", true);
                    myPrefsPrefsEditor.commit();
                } else {
                    myPrefsPrefsEditor.putBoolean("c2", false);
                    myPrefsPrefsEditor.commit();
                }


            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (c3.isChecked()){
                    myPrefsPrefsEditor.putBoolean("c3", true);
                    myPrefsPrefsEditor.commit();
                } else{
                    myPrefsPrefsEditor.putBoolean("c3", false);
                    myPrefsPrefsEditor.commit();
                }

            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (c4.isChecked()){
                    myPrefsPrefsEditor.putBoolean("c4", true);
                    myPrefsPrefsEditor.commit();
                } else{
                    myPrefsPrefsEditor.putBoolean("c4", false);
                    myPrefsPrefsEditor.commit();
                }

            }
        });

        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (c5.isChecked()){
                    myPrefsPrefsEditor.putBoolean("c5", true);
                    myPrefsPrefsEditor.commit();
                } else{
                    myPrefsPrefsEditor.putBoolean("c5", false);
                    myPrefsPrefsEditor.commit();
                }

            }
        });

        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (c6.isChecked()){
                    myPrefsPrefsEditor.putBoolean("c6", true);
                    myPrefsPrefsEditor.commit();
                } else{
                    myPrefsPrefsEditor.putBoolean("c6", false);
                    myPrefsPrefsEditor.commit();
                }

            }
        });

        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (c7.isChecked()){
                    myPrefsPrefsEditor.putBoolean("c7", true);
                    myPrefsPrefsEditor.commit();
                } else{
                    myPrefsPrefsEditor.putBoolean("c7", false);
                    myPrefsPrefsEditor.commit();
                }

            }
        });

        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (c8.isChecked()){
                    myPrefsPrefsEditor.putBoolean("c8", true);
                    myPrefsPrefsEditor.commit();
                } else{
                    myPrefsPrefsEditor.putBoolean("c8", false);
                    myPrefsPrefsEditor.commit();
                }

            }
        });

        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (c9.isChecked()){
                    myPrefsPrefsEditor.putBoolean("c9", true);
                    myPrefsPrefsEditor.commit();
                } else{
                    myPrefsPrefsEditor.putBoolean("c9", false);
                    myPrefsPrefsEditor.commit();
                }

            }
        });

        return v;
    }

    public void updateStatus(){

        if(myPrefs.getBoolean("c1", false) == true){
            c1.setChecked(true);
        }
        if(myPrefs.getBoolean("c2", false) == true){
            c2.setChecked(true);
        }
        if(myPrefs.getBoolean("c3", false) == true){
            c3.setChecked(true);
        }
        if(myPrefs.getBoolean("c4", false) == true){
            c4.setChecked(true);
        }
        if(myPrefs.getBoolean("c5", false) == true){
            c5.setChecked(true);
        }
        if(myPrefs.getBoolean("c6", false) == true){
            c6.setChecked(true);
        }
        if(myPrefs.getBoolean("c7", false) == true){
            c7.setChecked(true);
        }
        if(myPrefs.getBoolean("c8", false) == true){
            c8.setChecked(true);
        }
        if(myPrefs.getBoolean("c9", false) == true){
            c9.setChecked(true);
        }


    }

}
