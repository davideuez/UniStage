package com.example.unistage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static android.widget.LinearLayout.VERTICAL;


public class FragmentTirociniCandidati extends Fragment {

    String emailStud;
    int matricolaStud;
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tirocini_candidati, container, false);

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.card_candidature_tirocini);

        final AdapterCandidature listAdapterCandidature = new AdapterCandidature(LoginActivity.listaTirociniCandidati);
        rv.setAdapter(listAdapterCandidature);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);

        listAdapterCandidature.notifyDataSetChanged();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(), VERTICAL);
        rv.addItemDecoration(dividerItemDecoration);

        listAdapterCandidature.SetTheClick(new AdapterCandidature.OnItemClickedListener() {
            @Override
            public void onDeclinaClick(int position) {

                System.out.println("DECLINA n. " + position);

                ModuloPropostaTirocinio x = LoginActivity.listaTirociniCandidati.get(position);
                String[] separa = x.getStudente().split(" ");

                for(int i=0; i<LoginActivity.listaUtenti.size(); i++){

                    if(LoginActivity.listaUtenti.get(i).getCognome().equals(separa[1])){

                        emailStud = LoginActivity.listaUtenti.get(i).getEmail();
                        matricolaStud = LoginActivity.listaUtenti.get(i).getMatricola();

                        dbref.child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_candidati").child(String.valueOf(matricolaStud)).setValue(null);
                        for(int w=0; w < LoginActivity.listaTirociniCandidati.size(); w++){

                            if(LoginActivity.listaTirociniCandidati.get(w).getTitolo().equals(x.getTitolo())){
                                LoginActivity.listaTirociniCandidati.remove(w);
                            }

                        }

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { emailStud });
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Esito candidatura tirocinio " + x.getTitolo());
                        intent.putExtra(Intent.EXTRA_TEXT, "Siamo spiacenti, ma la sua candidatura per il tirocinio: " + x.getTitolo() + " è stata DECLINATA. \n\nSiamo dispiaciuti per l'esito ma la invitiamo a restare fiducioso e a candidarsi ad altri tirocini proposti. \n\nGrazie dell'attenzione \nArrivederci");
                        startActivity(Intent.createChooser(intent, ""));

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_professore, new FragmentTirociniCandidati()).commit();



                    }

                }

            }

            @Override
            public void onAccettaClick(int position) {
                System.out.println("ACCETTA n. " + position);

                ModuloPropostaTirocinio x = LoginActivity.listaTirociniCandidati.get(position);
                String[] separa = x.getStudente().split(" ");

                for(int i=0; i<LoginActivity.listaUtenti.size(); i++){

                    if(LoginActivity.listaUtenti.get(i).getCognome().equals(separa[1]+"")){

                        emailStud = LoginActivity.listaUtenti.get(i).getEmail();
                        matricolaStud = LoginActivity.listaUtenti.get(i).getMatricola();

                        // inizializzo tirocinio attivo per lo studente
                        dbref.child("Utenti").child("Studenti").child(String.valueOf(matricolaStud)).child("tirocinio_avviato").setValue(true);
                        dbref.child("Utenti").child("Studenti").child(String.valueOf(matricolaStud)).child("tirocinio_in_corso").child(x.getTitolo()).setValue(x);

                        // inizializzo tirocinio avviato lato professore
                        dbref.child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_avviati").child(x.getTitolo()).setValue(x);
                        
                        // elimino proposta tirocinio
                        dbref.child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_Proposti").child(x.getTitolo()).setValue(null);
                        for(int j=0; j < LoginActivity.listaTirociniPropostiSingle.size(); j++){

                            if(LoginActivity.listaTirociniPropostiSingle.get(j).getTitolo().equals(x.getTitolo())){
                                LoginActivity.listaTirociniPropostiSingle.remove(j);
                            }

                        }

                        // elimino tirocinio proposto
                        dbref.child("Tirocini_Proposti_Professori").child(x.getTitolo()).setValue(null);
                        for(int k=0; k < LoginActivity.listaTirociniProposti.size(); k++){

                            if(LoginActivity.listaTirociniProposti.get(k).getTitolo().equals(x.getTitolo())){
                                LoginActivity.listaTirociniProposti.remove(k);
                            }

                        }

                        //elimino candidatura

                        dbref.child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_candidati").child(String.valueOf(matricolaStud)).setValue(null);
                        for(int w=0; w < LoginActivity.listaTirociniCandidati.size(); w++){

                            if(LoginActivity.listaTirociniCandidati.get(w).getTitolo().equals(x.getTitolo())){
                                LoginActivity.listaTirociniCandidati.remove(w);
                            }

                        }

                        ArrayList<Task> y = new ArrayList<Task>();
                        LoginActivity.listaTask.add(y);

                        System.out.println("Grandezza array candidature: " + LoginActivity.listaTirociniCandidati.size());

                        //apre intent email
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { emailStud });
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Esito candidatura tirocinio " + x.getTitolo());
                        intent.putExtra(Intent.EXTRA_TEXT, "Siamo lieti di avvisarla che la sua candidatura per il tirocinio: " + x.getTitolo() + " è stata ACCETTATA. \n\nDa questo momento può effettuare il login sull'app UniStage per visualizzare i dettagli ed i task del tirocinio.  \n\nGrazie dell'attenzione. Buon lavoro. \nArrivederci");
                        startActivity(Intent.createChooser(intent, ""));

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_professore, new T_incorso_professore()).commit();

                    }

                }

            }
        });


        return v;
    }
}
