package com.example.unistage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.ViewHolder1> {
    private ArrayList<Task> lista;
    private OnItemClickedListener mListener;

    Task current_item;
    private DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();

    public interface OnItemClickedListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(AdapterTask.OnItemClickedListener listener){
        mListener = listener;
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {

        public TextView titolo;
        public TextView descrizione_task;
        public TextView data_assegnazione;
        public TextView data_scadenza;
        public ImageView check;

        public ViewHolder1(@NonNull View itemView, final OnItemClickedListener listener) {

            super(itemView);
            titolo = itemView.findViewById(R.id.titolo_task);
            descrizione_task = itemView.findViewById(R.id.descrizione_task);
            data_assegnazione = itemView.findViewById(R.id.card_data_inizio_task);
            data_scadenza = itemView.findViewById(R.id.card_data_fine_task);
            check = itemView.findViewById(R.id.check);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public AdapterTask(ArrayList<Task> lista) {
        this.lista = lista;
    }

    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_task, null);
        ViewHolder1 viewHolder = new ViewHolder1(itemLayoutView, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, final int position) {
        current_item = lista.get(position);
        holder.titolo.setText(current_item.getTitolo());
        holder.descrizione_task.setText(current_item.getDescrizione());
        holder.data_assegnazione.setText(current_item.getAssegnataIl());
        holder.data_scadenza.setText(current_item.getDataScadenza());

        if(current_item.getCompletata() == 0)
            holder.check.setImageResource(R.drawable.listgray);

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Oggetto cliccato: " + current_item.toString());

                if(LoginActivity.u_loggato.ruolo.equals("professore")){
                    if(current_item.getCompletata() == 0){
                        LoginActivity.listaTask.get(GestisciTask.posizione).get(position).setCompletata(1);
                        dbref.child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_avviati").child(GestisciTirocinio.x.getTitolo()).child("listaTask").child(current_item.getTitolo()).child("completata").setValue(1);
                        dbref.child("Utenti").child("Studenti").child(String.valueOf(GestisciTask.matricola)).child("tirocinio_in_corso").child(GestisciTirocinio.x.getTitolo()).child("listaTask").child(current_item.getTitolo()).child("completata").setValue(1);
                        notifyItemChanged(position);

                    } else {
                        LoginActivity.listaTask.get(GestisciTask.posizione).get(position).setCompletata(0);
                        dbref.child("Utenti").child("Professori").child(LoginActivity.u_loggato.getCognome()).child("Tirocini_avviati").child(GestisciTirocinio.x.getTitolo()).child("listaTask").child(current_item.getTitolo()).child("completata").setValue(0);
                        dbref.child("Utenti").child("Studenti").child(String.valueOf(GestisciTask.matricola)).child("tirocinio_in_corso").child(GestisciTirocinio.x.getTitolo()).child("listaTask").child(current_item.getTitolo()).child("completata").setValue(0);
                        notifyItemChanged(position);
                    }
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}