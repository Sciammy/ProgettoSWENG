package com.google.gwt.sample.stockwatcher.server;

import java.io.Serializable;

public class Yugioh_card extends Card{
	    //private String name;
	    private String type;
	    private String desc;
	    private String race;
	    private String imageUrl;
	    private String smallImageUrl;

	    public Yugioh_card(String name, String type, String desc, String race, String imageUrl, String smallImageUrl) {
	        this.name = name;
	        this.type = type;
	        this.desc = desc;
	        this.race = race;
	        this.imageUrl = imageUrl;
	        this.smallImageUrl = smallImageUrl;
	    }

	    // Default constructor
	    public Yugioh_card() {
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }

	    public String getRace() {
	        return race;
	    }

	    public void setRace(String race) {
	        this.race = race;
	    }

	    public String getImageUrl() {
	        return imageUrl;
	    }

	    public void setImageUrl(String imageUrl) {
	        this.imageUrl = imageUrl;
	    }

	    public String getSmallImageUrl() {
	        return smallImageUrl;
	    }

	    public void setSmallImageUrl(String smallImageUrl) {
	        this.smallImageUrl = smallImageUrl;
	    }

	    @Override
	    public String toString() {
	        return "ID: " + ID + "Gico: " + tipoGioco
	        		+ "YugiohCard{" +
	                "name='" + name + '\'' +
	                ", type='" + type + '\'' +
	                ", desc='" + desc + '\'' +
	                ", race='" + race + '\'' +
	                ", imageUrl='" + imageUrl + '\'' +
	                ", smallImageUrl='" + smallImageUrl + '\'' +
	                '}';
	    }
	}
