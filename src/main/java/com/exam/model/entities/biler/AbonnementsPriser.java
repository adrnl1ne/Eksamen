package com.exam.model.entities.biler;

public class AbonnementsPriser {
    private BilModel bilModel;
    private boolean isUnlimited;
    private double ThreeMonthsPris;
    private double SiXMonthsPris;
    private double TwelveMonthsPrice;
    private double TwentyFourMonthsPrice;
    private double ThirtysixMonthsPrice;
    private double PriceForColorChoice;
    private double StartUdbetaling;


    // getters and setters and to string


    public BilModel getBilModel() {
        return bilModel;
    }

    public void setBilModel(BilModel bilModel) {
        this.bilModel = bilModel;
    }

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public void setUnlimited(boolean unlimited) {
        isUnlimited = unlimited;
    }

    public double getThreeMonthsPris() {
        return ThreeMonthsPris;
    }

    public void setThreeMonthsPris(double threeMonthsPris) {
        ThreeMonthsPris = threeMonthsPris;
    }

    public double getSiXMonthsPris() {
        return SiXMonthsPris;
    }

    public void setSiXMonthsPris(double siXMonthsPris) {
        SiXMonthsPris = siXMonthsPris;
    }

    public double getTwelveMonthsPrice() {
        return TwelveMonthsPrice;
    }

    public void setTwelveMonthsPrice(double twelveMonthsPrice) {
        TwelveMonthsPrice = twelveMonthsPrice;
    }

    public double getTwentyFourMonthsPrice() {
        return TwentyFourMonthsPrice;
    }

    public void setTwentyFourMonthsPrice(double twentyFourMonthsPrice) {
        TwentyFourMonthsPrice = twentyFourMonthsPrice;
    }

    public double getThirtysixMonthsPrice() {
        return ThirtysixMonthsPrice;
    }

    public void setThirtysixMonthsPrice(double thirtysixMonthsPrice) {
        ThirtysixMonthsPrice = thirtysixMonthsPrice;
    }

    public double getPriceForColorChoice() {
        return PriceForColorChoice;
    }

    public void setPriceForColorChoice(double priceForColorChoice) {
        PriceForColorChoice = priceForColorChoice;
    }

    public double getStartUdbetaling() {
        return StartUdbetaling;
    }

    public void setStartUdbetaling(double startUdbetaling) {
        StartUdbetaling = startUdbetaling;
    }

    @Override
    public String toString() {
        return "AbonnementsPriser{" +
                "bilModel=" + bilModel +
                ", isUnlimited=" + isUnlimited +
                ", ThreeMonthsPris=" + ThreeMonthsPris +
                ", SiXMonthsPris=" + SiXMonthsPris +
                ", TwelveMonthsPrice=" + TwelveMonthsPrice +
                ", TwentyFourMonthsPrice=" + TwentyFourMonthsPrice +
                ", ThirtysixMonthsPrice=" + ThirtysixMonthsPrice +
                ", PriceForColorChoice=" + PriceForColorChoice +
                ", StartUdbetaling=" + StartUdbetaling +
                '}';
    }
}
