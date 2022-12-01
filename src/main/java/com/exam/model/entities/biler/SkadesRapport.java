package com.exam.model.entities.biler;

import java.util.Date;
import java.util.List;

public class SkadesRapport {
    private int Skadesrapport_ID;
    private Bil Bilen;
    private List<Skade> Skader;
    private LejeAftale Lejeaftalen;
    private Date Afleveringsdate;
    private double Kørselsdistance;
    private LejeAftale lejeaftalen;

    public SkadesRapport(int Skadesrapport_ID) {
        this.Skadesrapport_ID = Skadesrapport_ID;
    }

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

    public List<Skade> getSkader() {
        return Skader;
    }

    public void setSkader(List<Skade> skader) {
        this.Skader = skader;
    }

    public void setSkader(Skade skade) {
        Skader.add(skade);
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

