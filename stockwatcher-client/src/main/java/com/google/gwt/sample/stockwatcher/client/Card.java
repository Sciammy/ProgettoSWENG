package com.google.gwt.sample.stockwatcher.client;

import java.io.Serializable;

public class Card implements Serializable{
	protected String name;
	protected int ID;
	protected String tipoGioco;
	protected String condizione;
	protected String proprietario;
	protected String acquirente;
	protected String testoPersonale;

	
	public void setTestoPersonale(String testoPersonale) {
	this.testoPersonale = testoPersonale;
	}
	
	public String getTestoPersonale() {
	return testoPersonale;
	}
	

	
	public void setAcquirente(String acquirente) {
	this.acquirente = acquirente;
	}
	
	public String getAcquirente() {
	return acquirente;
	}
	
	
	
	public void setID(int ID) {
	this.ID = ID;
	}
	
	public void setTipoGioco(String tipoGioco) {
	this.tipoGioco = tipoGioco;
	}
	
	public void setCondizione(String condizione) {
	this.condizione = condizione;
	}
	
    public int getID() {
        return ID;
    }
    
    public String getTipoGioco() {
        return tipoGioco;
    }
    
    
    public String getName() {
        return name;
    }
    
    public String getCondizione() {
        return condizione;
    }

	
	
	public void setProprietario(String proprietario) {
	this.proprietario = proprietario;
	}
	
	public String getProprietario() {
	return proprietario;
	}
	
	
}

