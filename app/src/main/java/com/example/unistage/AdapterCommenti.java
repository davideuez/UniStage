package com.example.unistage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterCommenti extends RecyclerView.Adapter<AdapterCommenti.ViewHolder1> {
    private ArrayList<String> lista;

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        public TextView commento;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            commento = itemView.findViewById(R.id.comment_layout);
        }
    }

    public AdapterCommenti(ArrayList<String> lista) {
        this.lista = lista;
    }

    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_comment, null);
        ViewHolder1 viewHolder = new ViewHolder1(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder1 holder, int position) {
        String current_item = lista.get(position);
        holder.commento.setText(current_item);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}