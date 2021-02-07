package com.example.unistage;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeStudentePREActivity extends AppCompatActivity {

    String nomecognome;
    int mat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homestudente_pre);

        nomecognome = LoginActivity.u_loggato.nome.substring(0,1).toUpperCase() + LoginActivity.u_loggato.nome.substring(1).toLowerCase() + " " + LoginActivity.u_loggato.cognome.substring(0,1).toUpperCase() + LoginActivity.u_loggato.cognome.substring(1).toLowerCase();
        final TextView nome = findViewById(R.id.nomecognome);
        nome.setText(nomecognome);

        mat = LoginActivity.u_loggato.matricola;
        final TextView matricola = findViewById(R.id.matricola);
        matricola.setText("Mat. " + mat);


        final BottomNavigationView btv = findViewById(R.id.bottombar_studentepre);
        getSupportFragmentManager().beginTransaction().replace(R.id.card_container, new SearchFrag(LoginActivity.listaTirociniProposti)).commit();
        btv.setOnNavigationItemSelectedListener(btnm);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener btnm = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.iter_tirocinio:
                    fragment = new IterFrag();
                    System.out.println("Iter Tirocinio");
                    break;

                case R.id.tirocini:
                    fragment = new SearchFrag(LoginActivity.listaTirociniProposti);
                    System.out.println("Tirocini");
                    break;

                case R.id.salvati:
                    fragment = new FragmentSalvatiStudente();
                    System.out.println("Salvati");
                    break;

                case R.id.notifiche:
                    fragment = new FragmentNotifiche();
                    System.out.println("Notifiche");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.card_container, fragment).commit();
            return true;
        }
    };
}
