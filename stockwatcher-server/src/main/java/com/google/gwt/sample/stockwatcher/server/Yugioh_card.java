package com.google.gwt.sample.stockwatcher.server;

import java.io.Serializable;

public class Yugioh_card extends Card{
	    //private String name;
	    private String type;
	    private String desc;
	    private String race;
	    private String image_url;
	    private String small_image_url;

	    public Yugioh_card(String name, String type, String desc, String race, String imageUrl, String smallImageUrl) {
	        this.name = name;
	        this.type = type;
	        this.desc = desc;
	        this.race = race;
	        this.image_url = imageUrl;
	        this.small_image_url = smallImageUrl;
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
	        return image_url;
	    }

	    public void setImageUrl(String imageUrl) {
	        this.image_url = imageUrl;
	    }

	    public String getSmallImageUrl() {
	        return small_image_url;
	    }

	    public void setSmallImageUrl(String smallImageUrl) {
	        this.small_image_url = smallImageUrl;
	    }

	    @Override
	    public String toString() {
	        return "ID: " + ID + "Gico: " + tipoGioco
	        		+ "YugiohCard{" +
	                "name='" + name + '\'' +
	                ", type='" + type + '\'' +
	                ", desc='" + desc + '\'' +
	                ", race='" + race + '\'' +
	                ", imageUrl='" + image_url + '\'' +
	                ", smallImageUrl='" + small_image_url + '\'' +
	                '}';
	    }
	}
