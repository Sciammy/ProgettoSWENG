package com.google.gwt.sample.stockwatcher.client;

import java.io.Serializable;

public class Card implements Serializable{
	protected String name;
	protected int ID;
	protected String tipoGioco;
	protected String condizione;
	
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
    
    public int getCondizione() {
        return ID;
    }
	
	
}

