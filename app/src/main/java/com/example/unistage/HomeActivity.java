package com.example.unistage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView mrv;
    private RecyclerView.Adapter ma;
    private RecyclerView.LayoutManager mlm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final BottomNavigationView btv = findViewById(R.id.bottombar);
        getSupportFragmentManager().beginTransaction().replace(R.id.Container, new HomeFrag()).commit();
        btv.setOnNavigationItemSelectedListener(btnm);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener btnm = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            switch (item.getItemId()){
                case R.id.iter_tirocinio:
                    fragment = new TiroFrag();
                    System.out.println("Tiro");
                    break;

                case R.id.ricerca:
                    fragment = new SearchFrag();
                    System.out.println("cerca");
                    break;

                case R.id.salvati:
                    fragment = new HomeFrag();
                    System.out.println("home");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.Container, fragment).commit();
            return true;
        }
    };
}