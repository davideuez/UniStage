package com.example.unistage;

import java.util.ArrayList;

public class ModuloPropostaTirocinio {

    String titolo;
    String docente;
    int CFU;
    int durata;
    String dataInizio;
    String dataFine;
    String descrizione;
    int tipologia;
    String luogo;
    String studente;
    String listaObiettivi;
    ArrayList<Task> listaTask;

    public ModuloPropostaTirocinio(){

        titolo = "prova";
        docente = "prova";
        CFU=6;
        durata=24;
        dataInizio="12/10/14";
        dataFine="12/10/24";
        descrizione = "deadedad dadefa faewfefa efafdad afeadfwfa ";
        tipologia = 0;
        luogo = "Monclassico";
        studente = "Davide Uez";
        listaTask = new ArrayList<>();
        listaObiettivi = " obb1 ";

    }

    @Override
    public String toString() {
        return "ModuloPropostaTirocinio{" +
                "titolo='" + titolo + '\'' +
                ", docente='" + docente + '\'' +
                ", CFU=" + CFU +
                ", durata=" + durata +
                ", dataInizio='" + dataInizio + '\'' +
                ", dataFine='" + dataFine + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", tipologia=" + tipologia +
                ", luogo='" + luogo + '\'' +
                ", studente='" + studente + '\'' +
                ", listaObiettivi='" + listaObiettivi + '\'' +
                ", listaTask=" + listaTask +
                '}';
    }

    public ModuloPropostaTirocinio(String titolo, String docente, int CFU, int durata, String dataInizio, String dataFine, String descrizione, int tipologia, String luogo, String studente, String listaObiettivi, ArrayList<Task> listaTask) {
        this.titolo = titolo;
        this.docente = docente;
        this.CFU = CFU;
        this.durata = durata;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.descrizione = descrizione;
        this.tipologia = tipologia;
        this.luogo = luogo;
        this.studente = studente;
        this.listaObiettivi = listaObiettivi;
        this.listaTask = listaTask;
    }

    public ModuloPropostaTirocinio(String titolo, String luogo, String docente, int CFU, int durata, String descrizione) {
        this.titolo = titolo;
        this.luogo = luogo;
        this.docente = docente;
        this.CFU = CFU;
        this.durata = durata;
        this.descrizione = descrizione;
    }

    public ModuloPropostaTirocinio(String titolo, String luogo, int CFU, int durata, String descrizione) {
        this.titolo = titolo;
        this.luogo = luogo;
        this.CFU = CFU;
        this.durata = durata;
        this.descrizione = descrizione;
    }

    public ModuloPropostaTirocinio(String titolo, String luogo, int durata) {
        this.titolo = titolo;
        this.luogo = luogo;
        this.durata = durata;
    }


    public String getListaObiettivi() {
        return listaObiettivi;
    }

    public void setListaObiettivi(String listaObiettivi) {
        this.listaObiettivi = listaObiettivi;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public int getCFU() {
        return CFU;
    }

    public void setCFU(int CFU) {
        this.CFU = CFU;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getDataFine() {
        return dataFine;
    }

    public void setDataFine(String dataFine) {
        this.dataFine = dataFine;
    }

    public String getStudente() {
        return studente;
    }

    public void setStudente(String studente) {
        this.studente = studente;
    }

    public int getTipologia() {
        return tipologia;
    }

    public void setTipologia(int tipologia) {
        this.tipologia = tipologia;
    }

}
