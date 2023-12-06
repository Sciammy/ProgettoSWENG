package com.google.gwt.sample.stockwatcher.client;

import java.util.ArrayList;
import java.util.List;

public class Scambio {

    private String proponente;
    private String ricevente;
    private List<Card> offerta;
    private List<Card> richiesta;


    public Scambio(String proponente, String ricevente) {
        this.proponente = proponente;
        this.ricevente = ricevente;
        this.offerta = new ArrayList<>();
        this.richiesta = new ArrayList<>();
    }

    // Getter for proponente
    public String getProponente() {
        return proponente;
    }

    // Setter for proponente
    public void setProponente(String proponente) {
        this.proponente = proponente;
    }

    // Getter for ricevente
    public String getRicevente() {
        return ricevente;
    }

    // Setter for ricevente
    public void setRicevente(String ricevente) {
        this.ricevente = ricevente;
    }

    // Getter for offerta
    public List<Card> getOfferta() {
        return offerta;
    }


    public void setOfferta(List<Card> offerta) {
        this.offerta = offerta;
    }

    // Add a Card to the offerta list
    public void addCardToOfferta(Card card) {
        if (offerta == null) {
            offerta = new ArrayList<>();
        }
        offerta.add(card);
    }

    // Remove a Card from the offerta list
    public void removeCardFromOfferta(Card card) {
        if (offerta != null) {
            offerta.remove(card);
        }
    }

    public String getTitoloOfferta() {
        String titolo = "";
        for(Card carta : offerta) {
            titolo = titolo + carta.getName() + "("+ carta.getCondizione() + "); ";
        }
        titolo = titolo.trim();
        return titolo;
    }



    public String getTitoloRichiesta() {
        String titolo = "";
        for(Card carta : richiesta) {
            titolo = titolo + carta.getName() + "("+ carta.getCondizione() + "); ";
        }
        titolo = titolo.trim();
        return titolo;
    }


    // Add a Card to the richiesta list
    public void addCardToRichiesta(Card card) {
        if (richiesta == null) {
            richiesta = new ArrayList<>();
        }
        richiesta.add(card);
    }

    // Remove a Card from the richiesta list
    public void removeCardFromRichiesta(Card card) {
        if (richiesta != null) {
            richiesta.remove(card);
        }
    }




    // Getter for richiesta
    public List<Card> getRichiesta() {
        return richiesta;
    }

    // Setter for richiesta
    public void setRichiesta(List<Card> richiesta) {
        this.richiesta = richiesta;
    }

    // toString method to represent the object as a String
    @Override
    public String toString() {
        return "Scambio{" +
                "proponente='" + proponente + '\'' +
                ", ricevente='" + ricevente + '\'' +
                ", offerta=" + offerta +
                ", richiesta=" + richiesta +
                '}';
    }
}