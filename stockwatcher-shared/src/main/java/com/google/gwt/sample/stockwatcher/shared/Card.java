package com.google.gwt.sample.stockwatcher.shared;

import java.io.Serializable;

public class Card implements Serializable{
	protected String name;
	protected String ID;
	protected String tipoGioco;
	protected String condizione;

	public void setID(String ID) {
		this.ID = ID;
	}

	public void setTipoGioco(String tipoGioco) {
		this.tipoGioco = tipoGioco;
	}

	public void setCondizione(String condizione) {
		this.condizione = condizione;
	}

	public String getID() {
		return ID;
	}

	public String getTipoGioco() {
		return tipoGioco;
	}

	public String getCondizione() {
		return ID;
	}


}

