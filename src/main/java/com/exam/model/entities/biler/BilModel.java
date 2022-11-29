package com.exam.model.entities.biler;

public class BilModel {

    private AbonnementsPriser Abopris;
    private String brand;
    private String model;
    private int model_ID;
    private double c02PerKm;
    private double steelPrice;
    private double kmprx;
    private boolean isGearManuel;
    private EnergiType energiType;

    //getters and setters and toStrings


    public AbonnementsPriser getAbopris() {
        return Abopris;
    }

    public void setAbopris(AbonnementsPriser abopris) {
        Abopris = abopris;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getModel_ID() {
        return model_ID;
    }

    public void setModel_ID(int model_ID) {
        this.model_ID = model_ID;
    }

    public double getC02PerKm() {
        return c02PerKm;
    }

    public void setC02PerKm(double c02PerKm) {
        this.c02PerKm = c02PerKm;
    }

    public double getSteelPrice() {
        return steelPrice;
    }

    public void setSteelPrice(double steelPrice) {
        this.steelPrice = steelPrice;
    }

    public double getKmprx() {
        return kmprx;
    }

    public void setKmprx(double kmprx) {
        this.kmprx = kmprx;
    }

    public boolean isGearManuel() {
        return isGearManuel;
    }

    public void setGearManuel(boolean gearManuel) {
        isGearManuel = gearManuel;
    }

    public EnergiType getEnergiType() {
        return energiType;
    }

    public void setEnergiType(EnergiType energiType) {
        this.energiType = energiType;
    }

    @Override
    public String toString() {
        return "BilModel{" +
                "Abopris=" + Abopris +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", model_ID=" + model_ID +
                ", c02PerKm=" + c02PerKm +
                ", steelPrice=" + steelPrice +
                ", kmprx=" + kmprx +
                ", isGearManuel=" + isGearManuel +
                ", energiType=" + energiType +
                '}';
    }
}



