package com.google.gwt.sample.stockwatcher.client;


public class Magic_card extends Card{
    private String 	artist;
    //private String name;
    private String text;
    private String types;
    private String rarity;
    private String hasFoil;
    private String isAlternative;
    private String isFullArt;
    private String isPromo;
    private String isReprint;

    // Constructor
    public Magic_card(String artist, String name, String text, String types, String rarity,
                      String hasFoil, String isAlternative, String isFullArt,
                      String isPromo, String isReprint) {
        this.artist = artist;
        this.name = name;
        this.text = text;
        this.types = types;
        this.rarity = rarity;
        this.hasFoil = hasFoil;
        this.isAlternative = isAlternative;
        this.isFullArt = isFullArt;
        this.isPromo = isPromo;
        this.isReprint = isReprint;
    }

    // Getters
    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getTypes() {
        return types;
    }

    public String getRarity() {
        return rarity;
    }

    public String getHasFoil() {
        return hasFoil;
    }

    public String getIsAlternative() {
        return isAlternative;
    }

    public String getIsFullArt() {
        return isFullArt;
    }

    public String getIsPromo() {
        return isPromo;
    }

    public String getIsReprint() {
        return isReprint;
    }

    // Setters
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setHasFoil(String hasFoil) {
        this.hasFoil = hasFoil;
    }

    public void setIsAlternative(String isAlternative) {
        this.isAlternative = isAlternative;
    }

    public void setIsFullArt(String isFullArt) {
        this.isFullArt = isFullArt;
    }

    public void setIsPromo(String isPromo) {
        this.isPromo = isPromo;
    }

    public void setIsReprint(String isReprint) {
        this.isReprint = isReprint;
    }
    
    
    @Override
    public String toString() {
        return "ID: " + ID + "Gico: " + tipoGioco
        		+ "YourClass{" +
                "artist='" + artist + '\'' +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", types='" + types + '\'' +
                ", rarity='" + rarity + '\'' +
                ", hasFoil='" + hasFoil + '\'' +
                ", isAlternative='" + isAlternative + '\'' +
                ", isFullArt='" + isFullArt + '\'' +
                ", isPromo='" + isPromo + '\'' +
                ", isReprint='" + isReprint + '\'' +
                '}';
    }
}