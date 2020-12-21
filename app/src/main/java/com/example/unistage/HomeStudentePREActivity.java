package com.example.unistage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeStudentePREActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homestudente_pre);
        final ImageView add = findViewById(R.id.aggiungi);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeStudentePREActivity.this, PropostaTirocinioActivity.class);
                startActivity(i);
            }
        });
        final BottomNavigationView btv = findViewById(R.id.bottombar_studentepre);
        getSupportFragmentManager().beginTransaction().replace(R.id.card_container, new HomeFrag()).commit();
        btv.setOnNavigationItemSelectedListener(btnm);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener btnm = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.iter_tirocinio:
                    fragment = new TiroFrag();
                    System.out.println("Iter Tirocinio");
                    break;

                case R.id.ricerca:
                    fragment = new SearchFrag();
                    System.out.println("Ricerca");
                    break;

                case R.id.salvati:
                    fragment = new HomeFrag();
                    System.out.println("Salvati");
                    break;

                case R.id.notifiche:
                    fragment = new HomeFrag();
                    System.out.println("Notifiche");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.card_container, fragment).commit();
            return true;
        }
    };
}
