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

import java.util.ArrayList;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.ViewHolder1> {
    private ArrayList<Task> lista;
    private OnItemClickedListener mListener;

    Task current_item;

    public interface OnItemClickedListener{
        void onItemClick(int position);
        void onSaveClick(int position);
        void onDetailClick(int position);
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
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
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

                if(current_item.getCompletata() == 0){
                    System.out.println("Completata");
                } else {
                    System.out.println("Non completata");
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}