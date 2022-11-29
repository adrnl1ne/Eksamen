package com.exam.model.entities.biler;

public class Abonnement {
    private LejeAftale Lejeaftalen;
    private boolean isUnlimited;
    private int kmprMd;
    private int AbonnementLængde;
    private double AfleveringPrice;
    private double Priceprmonth;
    private double Udbetaling;
    private double XtraColorprice;
    private double PriceForOverDrive;

    public Abonnement(LejeAftale lejeaftalen) {
        this.Lejeaftalen = lejeaftalen;
    }

    // getters, setters and tostring.
    public LejeAftale getLejeaftalen() {
        return Lejeaftalen;
    }

    public void setLejeaftalen(LejeAftale lejeaftalen) {
        Lejeaftalen = lejeaftalen;
    }

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public void setUnlimited(boolean unlimited) {
        isUnlimited = unlimited;
    }

    public int getKmprMd() {
        return kmprMd;
    }

    public void setKmprMd(int kmprMd) {
        this.kmprMd = kmprMd;
    }

    public int getAbonnementLængde() {
        return AbonnementLængde;
    }

    public void setAbonnementLængde(int abonnementLængde) {
        AbonnementLængde = abonnementLængde;
    }

    public double getAfleveringPrice() {
        return AfleveringPrice;
    }

    public void setAfleveringPrice(double afleveringPrice) {
        AfleveringPrice = afleveringPrice;
    }

    public double getPriceprmonth() {
        return Priceprmonth;
    }

    public void setPriceprmonth(double priceprmonth) {
        Priceprmonth = priceprmonth;
    }

    public double getUdbetaling() {
        return Udbetaling;
    }

    public void setUdbetaling(double udbetaling) {
        Udbetaling = udbetaling;
    }

    public double getXtraColorprice() {
        return XtraColorprice;
    }

    public void setXtraColorprice(double xtraColorprice) {
        XtraColorprice = xtraColorprice;
    }

    public double getPriceForOverDrive() {
        return PriceForOverDrive;
    }

    public void setPriceForOverDrive(double priceForOverDrive) {
        PriceForOverDrive = priceForOverDrive;
    }


    @Override
    public String toString() {
        return "Abonnement{" +
                "Lejeaftalen=" + Lejeaftalen +
                ", isUnlimited=" + isUnlimited +
                ", kmprMd=" + kmprMd +
                ", AbonnementLængde=" + AbonnementLængde +
                ", AfleveringPrice=" + AfleveringPrice +
                ", Priceprmonth=" + Priceprmonth +
                ", Udbetaling=" + Udbetaling +
                ", XtraColorprice=" + XtraColorprice +
                ", PriceForOverDrive=" + PriceForOverDrive +
                '}';

    }
}
