package com.exam.model.entities.biler;

import java.util.List;

public class Levering {
    private LejeAftale Lejeaftalen;
    private LeveringsType Type;
    private String Leveringsadresse;
    private String Afleveringsadress;
    private double KørselDistanceInden;
    private double TransportTillæg;

    //getters og setters


    public LejeAftale getLejeaftalen() {
        return Lejeaftalen;
    }

    public void setLejeaftalen(LejeAftale lejeaftalen) {
        Lejeaftalen = lejeaftalen;
    }

    public LeveringsType getType() {
        return Type;
    }

    public void setType(LeveringsType type) {
        Type = type;
    }

    public String getLeveringsadresse() {
        return Leveringsadresse;
    }

    public void setLeveringsadresse(String leveringsadresse) {
        Leveringsadresse = leveringsadresse;
    }

    public String getAfleveringsadress() {
        return Afleveringsadress;
    }

    public void setAfleveringsadress(String afleveringsadress) {
        Afleveringsadress = afleveringsadress;
    }

    public double getKørselDistanceInden() {
        return KørselDistanceInden;
    }

    public void setKørselDistanceInden(double kørselDistanceInden) {
        KørselDistanceInden = kørselDistanceInden;
    }

    public double getTransportTillæg() {
        return TransportTillæg;
    }

    public void setTransportTillæg(double transportTillæg) {
        TransportTillæg = transportTillæg;
    }

    @Override
    public String toString() {
        return "Levering{" +
                "Lejeaftalen=" + Lejeaftalen +
                ", Type=" + Type +
                ", Leveringsadresse='" + Leveringsadresse + '\'' +
                ", Afleveringsadress='" + Afleveringsadress + '\'' +
                ", KørselDistanceInden=" + KørselDistanceInden +
                ", TransportTillæg=" + TransportTillæg +
                '}';
    }
}
