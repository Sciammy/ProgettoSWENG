package com.google.gwt.sample.stockwatcher.shared;

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

    public static class Variants implements Serializable{
        private boolean firstEdition;
        private boolean holo;
        private boolean normal;
        private boolean reverse;
        private boolean wPromo;

        public Variants(boolean firstEdition, boolean holo, boolean normal, boolean reverse, boolean wPromo) {
            this.firstEdition = firstEdition;
            this.holo = holo;
            this.normal = normal;
            this.reverse = reverse;
            this.wPromo = wPromo;
        }

        // Default constructor
        public Variants() {
        }

        public boolean isFirstEdition() {
            return firstEdition;
        }

        public void setFirstEdition(boolean firstEdition) {
            this.firstEdition = firstEdition;
        }

        public boolean isHolo() {
            return holo;
        }

        public void setHolo(boolean holo) {
            this.holo = holo;
        }

        public boolean isNormal() {
            return normal;
        }

        public void setNormal(boolean normal) {
            this.normal = normal;
        }

        public boolean isReverse() {
            return reverse;
        }

        public void setReverse(boolean reverse) {
            this.reverse = reverse;
        }

        public boolean iswPromo() {
            return wPromo;
        }

        public void setwPromo(boolean wPromo) {
            this.wPromo = wPromo;
        }

        @Override
        public String toString() {
            return "Variants{" +
                    "firstEdition=" + firstEdition +
                    ", holo=" + holo +
                    ", normal=" + normal +
                    ", reverse=" + reverse +
                    ", wPromo=" + wPromo +
                    '}';
        }
    }
}