package com.example.unistage;

public class Utente {
    public int userid;
    public String nome;
    public String cognome;
    public String email;
    public String  ruolo;
    boolean tirocinio_avviato;

    public Utente(){
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
}
