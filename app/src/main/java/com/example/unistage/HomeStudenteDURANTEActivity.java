package com.example.unistage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeStudenteDURANTEActivity extends AppCompatActivity {
    String nc;
    int m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homestudente_durante);

        nc = LoginActivity.u_loggato.nome + " " + LoginActivity.u_loggato.cognome;
        final TextView nomeCognome = findViewById(R.id.nomecognome_durante);
        nomeCognome.setText(nc);

        m = LoginActivity.u_loggato.matricola;
        final TextView matricolaDurante = findViewById(R.id.matricola_durante);
        matricolaDurante.setText("Matricola: "+m);


        final BottomNavigationView btv = findViewById(R.id.bottombar_studentedurante);
        getSupportFragmentManager().beginTransaction().replace(R.id.card_container_durante, new TirocinioDuranteFrag()).commit();
        btv.setOnNavigationItemSelectedListener(btnm);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener btnm = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.home:
                    fragment = new TirocinioDuranteFrag();
                    System.out.println("Home");
                    break;

                case R.id.tasks:
                    fragment = new TaskFrag();
                    System.out.println("Tasks");
                    break;

                case R.id.iter_tirocinio_durante:
                    fragment = new IterFrag();
                    System.out.println("Iter Tirocinio");
                    break;

                case R.id.notifiche:
                    fragment = new SearchFrag();
                    System.out.println("Notifiche");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.card_container_durante, fragment).commit();
            return true;
        }
    };
}
