package com.example.unistage;

import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class Notifiche {
    String titolo;
    String descrizione;
    String data;

    public Notifiche(String titolo, String descrizione, String data) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
    }


    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
