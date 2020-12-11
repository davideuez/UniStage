package com.example.unistage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Walkthrough1Activity extends AppCompatActivity {
    public static ArrayList<ModuloPropostaTirocinio> moduloPropostaTirocinios = new ArrayList<>();
    public static ArrayList<Home_card_itiem> home_card_itiems = new ArrayList<>();
    public static ArrayList<Home_card_itiem> home_card_itiems_prefe = new ArrayList<>();

    public static void init_array(){
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Tirocini_Proposti_Professori");
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Home_card_itiem hci = snapshot.getValue(Home_card_itiem.class);
                home_card_itiems.add(hci);
                System.out.println(hci);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    void init(){
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("Tirocini_Proposti_Professori");
        dbref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ModuloPropostaTirocinio hci = snapshot.getValue(ModuloPropostaTirocinio.class);
                moduloPropostaTirocinios.add(hci);
                System.out.println(hci);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough1);
        init_array();
        init();

        String userid = "billy è un cazzo di negro";
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final Button iniziamo = findViewById(R.id.getstartedbutton_id_main);

        iniziamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Walkthrough1Activity.this, Walkthrough2Activity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

}