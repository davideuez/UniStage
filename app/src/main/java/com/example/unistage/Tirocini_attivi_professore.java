package com.example.unistage;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Tirocini_attivi_professore extends AppCompatActivity {

    public static Utente u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirocini_attivi_professore);
        final BottomNavigationView btv = findViewById(R.id.bottombar_professore);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_professore, new T_incorso_professore()).commit();
        btv.setOnNavigationItemSelectedListener(btnm);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener btnm = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.Incorso:
                    fragment = new T_incorso_professore();
                    System.out.println("Iter Tirocinio");
                    break;

                case R.id.ricerca:
                    fragment = new FragmentRicercaStudente();
                    System.out.println("Ricerca");
                    break;

                case R.id.notifiche:
                    fragment = new FragmentRicercaStudente();
                    System.out.println("Notifiche");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.card_container, fragment).commit();
            return true;
        }
    };
}
