package com.exam.model.entities.biler;

import java.util.Date;

public class SkadesRapport {
    private int Skadesrapport_ID;
    private Bil Bilen;
    private Skade Skader;
    private LejeAftale Lejeaftalen;
    private Date Afleveringsdate;
    private double Kørselsdistance;
    private LejeAftale lejeaftalen = new LejeAftale();


    //Getters,Setters and toString

    public int getSkadesrapport_ID() {
        return Skadesrapport_ID;
    }

    public void setSkadesrapport_ID(int skadesrapport_ID) {
        Skadesrapport_ID = skadesrapport_ID;
    }

    public Bil getBilen() {
        return Bilen;
    }

    public void setBilen(Bil bilen) {
        Bilen = bilen;
    }

    public Skade getSkader() {
        return Skader;
    }

    public void setSkader(Skade skader) {
        Skader = skader;
    }

    public LejeAftale getLejeaftalen() {
        return Lejeaftalen;
    }

    public void setLejeaftalen(LejeAftale lejeaftalen) {
        Lejeaftalen = lejeaftalen;
    }


    public Date getAfleveringsdate() {
        return Afleveringsdate;
    }

    public void setAfleveringsdate(Date afleveringsdate) {
        Afleveringsdate = afleveringsdate;
    }

    public double getKørselsdistance() {
        return Kørselsdistance;
    }

    public void setKørselsdistance(double kørselsdistance) {
        Kørselsdistance = kørselsdistance;
    }

    @Override
    public String toString() {
        return "SkadesRapport{" +
                "Skadesrapport_ID=" + Skadesrapport_ID +
                ", Bilen=" + Bilen +
                ", Skader=" + Skader +
                ", Lejeaftalen=" + Lejeaftalen +
                ", Afleveringsdate=" + Afleveringsdate +
                ", Kørselsdistance=" + Kørselsdistance +
                ", lejeaftalen=" + lejeaftalen +
                '}';
    }
}

