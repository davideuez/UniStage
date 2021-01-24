package com.example.unistage;

public class Task {

    String titolo;
    String assegnataIl;
    int completata;
    String descrizione;
    String dataScadenza;

    public Task() {

        titolo = "Task n";
        assegnataIl = "10/10/20";
        completata = 0;
        descrizione = "Lorem ipsum dolor sit Lorem ipsum dolor sit Lorem ipsum dolor sit Lorem ipsum dolor sit";
        dataScadenza = "12/12/20";

    }

    public Task(String titolo, String assegnataIl, int completata, String descrizione, String dataScadenza) {
        this.titolo = titolo;
        this.assegnataIl = assegnataIl;
        this.completata = completata;
        this.descrizione = descrizione;
        this.dataScadenza = dataScadenza;
    }

    @Override
    public String toString() {
        return "Task{" +
                "titolo='" + titolo + '\'' +
                ", assegnataIl='" + assegnataIl + '\'' +
                ", completata=" + completata +
                ", descrizione='" + descrizione + '\'' +
                ", dataScadenza='" + dataScadenza + '\'' +
                '}';
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAssegnataIl() {
        return assegnataIl;
    }

    public void setAssegnataIl(String assegnataIl) {
        this.assegnataIl = assegnataIl;
    }

    public int getCompletata() {
        return completata;
    }

    public void setCompletata(int completata) {
        this.completata = completata;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

}
