package com.example.unistage;

public class ModuloPropostaTirocinio {
    String titolo;
    String luogo;
    String docente;
    int CFU;
    int durata;
    String descrizione;

    public ModuloPropostaTirocinio(){

    }

    @Override
    public String toString() {
        return "ModuloPropostaTirocinio{" +
                "titolo='" + titolo + '\'' +
                ", luogo='" + luogo + '\'' +
                ", docente='" + docente + '\'' +
                ", CFU=" + CFU +
                ", durata=" + durata +
                ", descrizione='" + descrizione + '\'' +
                '}';
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

}
