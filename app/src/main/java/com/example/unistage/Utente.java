package com.example.unistage;

import java.util.ArrayList;

public class Utente {

    public String nome;
    public String cognome;
    public String email;
    public String password;
    public String ruolo;
    public int matricola;
    ArrayList<ModuloPropostaTirocinio> tirocini_salvati;
    ArrayList<ModuloPropostaTirocinio> tirocini_avviati;
    ArrayList<ModuloPropostaTirocinio> tirocini_proposti;
    ModuloPropostaTirocinio tirocinio_in_corso;
    boolean tirocinio_avviato;

    public Utente() {


    }

    // Costruttore professore
    public Utente(String mail, String nomeE, String cognomeE, String passwordE){
        email = mail;
        nome = nomeE;
        cognome = cognomeE;
        password = passwordE;
        ruolo = "professore";
    }

    // Costruttore studente
    public Utente(String n, String cn, String em, String pass, int matr){
        nome = n;
        cognome = cn;
        email = em;
        password = pass;
        matricola = matr;
        tirocinio_avviato = false;
        ruolo = "studente";
    }

    // Costruttore login


    public Utente(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", matricola=" + matricola +
                ", tirocini_salvati=" + tirocini_salvati +
                ", tirocini_avviati=" + tirocini_avviati +
                ", tirocini_proposti=" + tirocini_proposti +
                ", tirocinio_in_corso=" + tirocinio_in_corso +
                ", tirocinio_avviato=" + tirocinio_avviato +
                '}';
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
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

    public ArrayList<ModuloPropostaTirocinio> getTirocini_avviati() {
        return tirocini_avviati;
    }

    public void setTirocini_avviati(ArrayList<ModuloPropostaTirocinio> tirocini_avviati) {
        this.tirocini_avviati = tirocini_avviati;
    }

    public ArrayList<ModuloPropostaTirocinio> getTirocini_proposti() {
        return tirocini_proposti;
    }

    public void setTirocini_proposti(ArrayList<ModuloPropostaTirocinio> tirocini_proposti) {
        this.tirocini_proposti = tirocini_proposti;
    }

    public ModuloPropostaTirocinio getTirocinio_in_corso() {
        return tirocinio_in_corso;
    }

    public void setTirocinio_in_corso(ModuloPropostaTirocinio tirocinio_in_corso) {
        this.tirocinio_in_corso = tirocinio_in_corso;
    }

    public boolean isTirocinio_avviato() {
        return tirocinio_avviato;
    }

    public void setTirocinio_avviato(boolean tirocinio_avviato) {
        this.tirocinio_avviato = tirocinio_avviato;
    }
}
