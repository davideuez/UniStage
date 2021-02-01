package com.example.unistage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCandidature extends RecyclerView.Adapter<AdapterCandidature.ViewHolder> {
    private ArrayList<ModuloPropostaTirocinio> lista_tirocini;
    private OnItemClickedListener listener;

    public interface  OnItemClickedListener{
        void onAccettaClick(int position);
        void onDeclinaClick(int position);
    }

    public void SetTheClick (AdapterCandidature.OnItemClickedListener listener){
        this.listener=listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView vh_nomeStudente, vh_matricola, vh_tirocinio;
        public Button accettaCandidatura, rifiutaCandidatura;


        public ViewHolder(@NonNull View itemView, final OnItemClickedListener listener) {
            super(itemView);
            vh_nomeStudente = itemView.findViewById(R.id.nome_candidato);
            vh_matricola = itemView.findViewById(R.id.matricola_candidato);
            vh_tirocinio = itemView.findViewById(R.id.nome_tirocinio);
            accettaCandidatura = itemView.findViewById(R.id.accetta);
            rifiutaCandidatura = itemView.findViewById(R.id.declina);

            accettaCandidatura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onAccettaClick(position);
                        }
                    }
                }
            });

            rifiutaCandidatura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeclinaClick(position);
                        }
                    }
                }
            });

        }

    }

    public AdapterCandidature(ArrayList<ModuloPropostaTirocinio> adaptercardlist){
        this.lista_tirocini = adaptercardlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_candidatura, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModuloPropostaTirocinio current_item = lista_tirocini.get(position);
        holder.vh_nomeStudente.setText(current_item.getStudente());
        holder.vh_matricola.setText("123456");
        holder.vh_tirocinio.setText(current_item.getTitolo());

    }

    @Override
    public int getItemCount() {
        return lista_tirocini.size();
    }


}
