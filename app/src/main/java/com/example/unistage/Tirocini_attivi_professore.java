package com.example.unistage;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Tirocini_attivi_professore extends AppCompatActivity {

    ConstraintLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirocini_attivi_professore);

        rl = (ConstraintLayout)findViewById(R.id.bg_prof);

        String nome_prof = LoginActivity.u_loggato.getNome();
        String cognome_prof = LoginActivity.u_loggato.getCognome();
        String nome_completo = nome_prof.substring(0, 1).toUpperCase() + nome_prof.substring(1).toLowerCase() + " " + cognome_prof.substring(0, 1).toUpperCase() + cognome_prof.substring(1).toLowerCase();

        TextView nome = findViewById(R.id.nome_prof);
        nome.setText(nome_completo);

        ImageView aggTirocinio = findViewById(R.id.aggiungi);
        aggTirocinio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(Tirocini_attivi_professore.this, AggiungiTirocinio.class);
                startActivity(k);
            }
        });

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
                    rl.setBackgroundResource(R.drawable.bg_in_corso);
                    System.out.println("Tirocini in corso");
                    break;

                case R.id.Proposti:
                    fragment = new FragmentTirociniProposti();
                    rl.setBackgroundResource(R.drawable.bg_proposti);
                    System.out.println("Tirocini proposti");
                    break;

                case R.id.Inattesa:
                    fragment = new FragmentTirociniCandidati();
                    rl.setBackgroundResource(R.drawable.bg_candidature);
                    System.out.println("Tirocini in attesa");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_professore, fragment).commit();
            return true;
        }
    };
}
