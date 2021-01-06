package com.example.unistage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterTirociniProfessore extends RecyclerView.Adapter<AdapterTirociniProfessore.ViewHolder2> {
    private ArrayList<ModuloPropostaTirocinio> lista_privata_adapter;
    private ClickedListener listener;

    public interface ClickedListener{
        void ClickDettagli(int position);
    }
    public void SetTheClick (ClickedListener listener){
        this.listener=listener;
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder{
        public TextView vh_title;
        public TextView vh_data;
        public TextView vh_azienda;
        public Button salva;
        public Button dettagli;


        public ViewHolder2(@NonNull View itemView, final ClickedListener listener) {
            super(itemView);
            vh_azienda = itemView.findViewById(R.id.testo_del_presso);
            vh_data = itemView.findViewById(R.id.durata_itemprofessore);
            vh_title = itemView.findViewById(R.id.durata_itemprofessore);
            salva = itemView.findViewById(R.id.bottome_item_professore);

            salva.setOnClickListener(new View.OnClickListener() {
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

    public AdapterTirociniProfessore(ArrayList<ModuloPropostaTirocinio> adaptercardlist){
        this.lista_privata_adapter = adaptercardlist;
    }

    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_professore, null);
        ViewHolder2 viewHolder = new ViewHolder2(itemLayoutView,listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        final ModuloPropostaTirocinio current_item = lista_privata_adapter.get(position);
        holder.vh_title.setText(current_item.getLuogo());
        holder.vh_azienda.setText(current_item.getTitolo());
        holder.vh_data.setText(current_item.getDurata());

    }

    @Override
    public int getItemCount() {
        return lista_privata_adapter.size();
    }


}
