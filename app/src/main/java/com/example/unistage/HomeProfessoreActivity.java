package com.example.unistage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeProfessoreActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_professore);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.bottombar_professore);
        getSupportFragmentManager().beginTransaction().replace(R.id.Container_Prof, new InattesaFrag()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.Inattesa:
                    fragment = new InattesaFrag();
                    System.out.println("Tiro");
                    break;

                case R.id.Incorso:
                    fragment = new InattesaFrag();
                    System.out.println("cerca");
                    break;

                case R.id.Notifiche:
                    fragment = new InattesaFrag();
                    System.out.println("home");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.Container_Prof, fragment).commit();
            return true;
        }
    };

}