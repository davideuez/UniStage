package com.example.unistage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.ViewHolder1> {
    private ArrayList<Task> lista;
    private OnItemClickedListener mListener;

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
        public EditText commento_task;


    public ViewHolder1(@NonNull View itemView, final OnItemClickedListener listener) {
        super(itemView);
        titolo = itemView.findViewById(R.id.titolo_task);
        descrizione_task = itemView.findViewById(R.id.descrizione_task);
        data_assegnazione = itemView.findViewById(R.id.card_data_inizio_task);
        data_scadenza = itemView.findViewById(R.id.card_data_fine_task);
        commento_task = itemView.findViewById(R.id.commento_task);

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
        Task current_item = lista.get(position);
        holder.titolo.setText(current_item.getTitolo());
        holder.descrizione_task.setText(current_item.getDescrizione());
        holder.data_assegnazione.setText(current_item.getAssegnataIl());
        holder.data_scadenza.setText(current_item.getDataScadenza());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}