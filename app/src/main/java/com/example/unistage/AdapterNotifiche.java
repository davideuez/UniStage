package com.example.unistage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterNotifiche extends RecyclerView.Adapter<AdapterNotifiche.ViewHolder3> {

    private ArrayList<Notifiche> lista;

    public static class ViewHolder3 extends RecyclerView.ViewHolder {

        public TextView titolo_notifica;
        public TextView descrizione_notifica;
        public TextView data_notifica;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            titolo_notifica = itemView.findViewById(R.id.titolo_notifica_id);
            descrizione_notifica = itemView.findViewById(R.id.testo_notifica_id);
            data_notifica = itemView.findViewById(R.id.data_notifica_id);
        }

    }

    public AdapterNotifiche(ArrayList<Notifiche> lista) {
        this.lista = lista;
    }

    public ViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notifiche, null);
        ViewHolder3 viewHolder = new ViewHolder3(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNotifiche.ViewHolder3 holder, int position) {

        Notifiche current_item = lista.get(position);
        holder.data_notifica.setText(current_item.getData().toString());
        holder.descrizione_notifica.setText(current_item.getDescrizione());
        holder.titolo_notifica.setText(current_item.getTitolo());

    }


    @Override
    public int getItemCount() {
        return lista.size();
    }
}
