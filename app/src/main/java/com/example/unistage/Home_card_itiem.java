package com.example.unistage;

public class Home_card_itiem {

    private String titolo;
    private String azienda;
    private String data_iscrizioni;

    public Home_card_itiem(){

    }

    @Override
    public String toString() {
        return "Home_card_itiem{" +
                "titolo='" + titolo + '\'' +
                ", azienda='" + azienda + '\'' +
                ", data_iscrizioni='" + data_iscrizioni + '\'' +
                '}';
    }

    public Home_card_itiem(String titolo, String data_iscrizioni, String azienda){

        this.titolo=titolo;
        this.azienda = azienda;
        this.data_iscrizioni=data_iscrizioni;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAzienda(String azienda) {
        this.azienda = azienda;
    }

    public void setData_iscrizioni(String data_iscrizioni) {
        this.data_iscrizioni = data_iscrizioni;
    }

    public String getTitolo(){
        return titolo;
    }

    public String getAzienda(){
        return azienda;
    }

    public String getData_iscrizioni(){
        return data_iscrizioni;
    }
}
