package com.exam.model.entities.biler;


public class Skade {

    private int Skade_ID;
    private SkadesRapport Skadesrapporten;
    private SkadeType Type;
    private double Price;

    public Skade(int Skade_ID) {
        this.Skade_ID = Skade_ID;
    }

    //getters ,setters og toStrings

    public int getSkade_ID() {
        return Skade_ID;
    }

    public void setSkade_ID(int skade_ID) {
        Skade_ID = skade_ID;
    }

    public SkadesRapport getSkadesrapporten() {
        return Skadesrapporten;
    }

    public void setSkadesrapporten(SkadesRapport skadesrapporten) {
        Skadesrapporten = skadesrapporten;
    }

    public SkadeType getType() {
        return Type;
    }

    public void setType(SkadeType type) {
        Type = type;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "Skade{" +
                "Skade_ID=" + Skade_ID +
                ", Skadesrapporten=" + Skadesrapporten +
                ", Type=" + Type +
                ", Price=" + Price +
                '}';
    }
}
