package com.example.unistage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TirocinioInCorsoAdapter extends RecyclerView.Adapter<TirocinioInCorsoAdapter.ViewHolder2> {
    private ArrayList<ModuloPropostaTirocinio> lista_privata_adapter;
    private ClickedListener listener;

    public interface ClickedListener{
        void ClickDettagli(int position);
    }
    public void SetTheClick (ClickedListener listener){
        this.listener=listener;
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder{
        public TextView nomestudente;
        public TextView tipologia;
        public TextView presso;
        public TextView task;
        public TextView data;
        public Button gestione;


        public ViewHolder2(@NonNull View itemView, final ClickedListener listener) {
            super(itemView);
            nomestudente = itemView.findViewById(R.id.nome_studente);
            tipologia = itemView.findViewById(R.id.tipologia);
            presso = itemView.findViewById(R.id.nome_azienda);
            task = itemView.findViewById(R.id.task);
            data = itemView.findViewById(R.id.dataFine);
            gestione= itemView.findViewById(R.id.card_gestiscitirocinio_button);

            gestione.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.ClickDettagli(position);
                        }
                    }
                }
            });

        }

    }

    public TirocinioInCorsoAdapter(ArrayList<ModuloPropostaTirocinio> adaptercardlist){
        this.lista_privata_adapter = adaptercardlist;
    }

    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_professore_attivo, null);
        ViewHolder2 viewHolder = new ViewHolder2(itemLayoutView,listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        final ModuloPropostaTirocinio current_item = lista_privata_adapter.get(position);
        holder.nomestudente.setText(current_item.getLuogo());
        holder.tipologia.setText(current_item.getTitolo());
        holder.presso.setText(current_item.getDurata());
        holder.task.setText(current_item.getDurata());
        holder.data.setText(current_item.getDurata());

    }

    @Override
    public int getItemCount() {
        return lista_privata_adapter.size();
    }


}
