package com.example.unistage;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.unistage.AdapterTirociniStudente.*;

public class AdapterTirociniStudente extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<ModuloPropostaTirocinio> lista_privata_adapter;
    private OnItemClickedListener mListener;

    public interface  OnItemClickedListener{
        void onItemClick(int position);
        void onSaveClick(int position);
        void onDetailClick(int position);
    }

    public void setOnItemClickListener(OnItemClickedListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView vh_title;
        public TextView vh_data;
        public TextView vh_azienda;
        public Button salva;
        public Button dettagli;


        public ViewHolder(@NonNull View itemView, final OnItemClickedListener listener) {
            super(itemView);
            vh_azienda = itemView.findViewById(R.id.card_azienda_id);
            vh_data = itemView.findViewById(R.id.card_data_id);
            vh_title = itemView.findViewById(R.id.card_title_id);
            dettagli = itemView.findViewById(R.id.card_dettaglibutton_id);


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

            dettagli.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        Log.d("Vedi dettagli", "Cliccato vedi dettagli alla posizione: "+position);
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDetailClick(position);
                        }

                    }
                }
            });
        }


    }

    public AdapterTirociniStudente(ArrayList<ModuloPropostaTirocinio> adaptercardlist){
        this.lista_privata_adapter = adaptercardlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.carte_della_h, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModuloPropostaTirocinio current_item = lista_privata_adapter.get(position);

        holder.vh_title.setText(current_item.getTitolo());
        holder.vh_azienda.setText(current_item.getLuogo());
        holder.vh_data.setText(String.valueOf(current_item.getDataInizio()));

    }

    @Override
    public int getItemCount() {
        return lista_privata_adapter.size();
    }


}

