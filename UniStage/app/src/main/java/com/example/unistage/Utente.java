package com.example.unistage;

import java.util.ArrayList;

public class Utente {
    public int userid;
    public String nome;
    public String cognome;
    public String email;
    public String  ruolo;
    public String password;
    ArrayList<ModuloPropostaTirocinio> tirocini_salvati;
    boolean tirocinio_avviato;

    public Utente(){
        tirocini_salvati= new ArrayList<>();
        userid=0;
        nome = "";
        email = "";
        cognome = "";
        ruolo="";
        tirocinio_avviato=false;
    }

    public Utente(int uid, String n, String cn, String em, String r){
        nome=n;
        cognome=cn;
        email=em;
        ruolo=r;
        userid=uid;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "userid=" + userid +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", tirocini_salvati=" + tirocini_salvati +
                ", tirocinio_avviato=" + tirocinio_avviato +
                '}';
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<ModuloPropostaTirocinio> getTirocini_salvati() {
        return tirocini_salvati;
    }

    public void setTirocini_salvati(ArrayList<ModuloPropostaTirocinio> tirocini_salvati) {
        this.tirocini_salvati = tirocini_salvati;
    }

    public boolean isTirocinio_avviato() {
        return tirocinio_avviato;
    }

    public void setTirocinio_avviato(boolean tirocinio_avviato) {
        this.tirocinio_avviato = tirocinio_avviato;
    }
}
