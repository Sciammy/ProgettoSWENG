package com.google.gwt.sample.stockwatcher.server;

import java.io.Serializable;

public class Card implements Serializable{
	protected String name;
	protected int ID;
	protected String tipoGioco;
	protected String condizione;
	protected String proprietario;
	
	public void setID(int ID) {
	this.ID = ID;
	}
	
	
	public void setProprietario(String proprietario) {
	this.proprietario = proprietario;
	}
	
	public String getProprietario() {
	return proprietario;
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
    
    public String getCondizione() {
        return condizione;
    }
	
	
}
