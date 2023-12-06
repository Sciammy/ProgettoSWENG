package com.google.gwt.sample.stockwatcher.client;

import java.io.Serializable;
import java.util.Arrays;



public class Pokemon_card extends Card{
    private String illustrator;
    private String image;
    //private String name;
    private String rarity;
    private Variants variants;
    private String[] types;

    public Pokemon_card(String illustrator, String image, String name, String rarity, Variants variants, String[] types) {
        this.illustrator = illustrator;
        this.image = image;
        this.name = name;
        this.rarity = rarity;
        this.variants = variants;
        this.types = types;
    }

    // Default constructor
    public Pokemon_card() {
    }

    public String getIllustrator() {
        return illustrator;
    }

    public void setIllustrator(String illustrator) {
        this.illustrator = illustrator;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "Gico: " + tipoGioco
        		+ "PokemonCard{" +
                "illustrator='" + illustrator + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", variants=" + variants +
                ", types=" + Arrays.toString(types) +
                '}';
    }

	public String getTypesString() {
		// TODO Auto-generated method stub
		if (types == null) {
			return "---";
		}
		else {
			String tipi = "";
			for (String tipo : types) {
				tipi = tipi + tipo + " ";
			}
			
			return tipi.trim();
		}
	}

 
}