package com.google.gwt.sample.stockwatcher.client;

import java.io.Serializable;

public class Variants implements Serializable{
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