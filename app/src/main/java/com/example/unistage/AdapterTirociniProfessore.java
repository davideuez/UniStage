package com.example.unistage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterTirociniProfessore extends RecyclerView.Adapter<AdapterTirociniProfessore.ViewHolder> {
    private ArrayList<ModuloPropostaTirocinio> lista_tirocini;
    private OnItemClickedListener listener;

    public interface  OnItemClickedListener{
        void onItemClick(int position);
    }

    public void SetTheClick (OnItemClickedListener listener){
        this.listener=listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView vh_nomeStudente, vh_tipologia, vh_azienda, vh_dataFine, vh_task;
        public Button gestisciTirocinio;


        public ViewHolder(@NonNull View itemView, final OnItemClickedListener listener) {
            super(itemView);
            vh_nomeStudente = itemView.findViewById(R.id.nome_studente);
            vh_tipologia = itemView.findViewById(R.id.tipologia);
            vh_azienda = itemView.findViewById(R.id.nome_azienda);
            vh_task = itemView.findViewById(R.id.task);
            vh_dataFine = itemView.findViewById(R.id.dataFine);
            gestisciTirocinio = itemView.findViewById(R.id.card_gestiscitirocinio_button);

            gestisciTirocinio.setOnClickListener(new View.OnClickListener() {
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

    public AdapterTirociniProfessore(ArrayList<ModuloPropostaTirocinio> adaptercardlist){
        this.lista_tirocini = adaptercardlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_professore_attivo, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView,listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModuloPropostaTirocinio current_item = lista_tirocini.get(position);
        holder.vh_nomeStudente.setText(current_item.getStudente());
        holder.vh_azienda.setText(current_item.getLuogo());
        holder.vh_dataFine.setText(current_item.getDataFine());

        if(current_item.getTipologia() == 0){
            holder.vh_tipologia.setText("INTERNO");
        } else{
            holder.vh_tipologia.setText("ESTERNO");
        }

        try{


        } catch(Exception e) {

        }

        if(!LoginActivity.listaTask.get(position).isEmpty()){
            int x = LoginActivity.listaTask.get(position).size();
            String prossimaTask = LoginActivity.listaTask.get(position).get(x-1).getTitolo();
            holder.vh_task.setText(prossimaTask);
        } else {
            holder.vh_task.setText("Non ci sono task da completare");
        }

    }

    @Override
    public int getItemCount() {
        return lista_tirocini.size();
    }


}
