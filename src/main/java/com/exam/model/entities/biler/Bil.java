package com.exam.model.entities.biler;

import java.util.List;

public class Bil {
    private String Stelnummer;
    private BilTilstand Tilstand;
    private BilModel Model;
    private List<SkadesRapport> Skadesrapporter;
    private double Km_kørte;


    public Bil(String stelnummer, BilModel model, double km_kørte, BilTilstand tilstand) {
        Stelnummer = stelnummer;
        Model = model;
        Km_kørte = km_kørte;
        Tilstand = tilstand;
    }

    public Bil(String stelnummer, BilModel model, double km_kørte ,BilTilstand tilstand,
               List<SkadesRapport> skadesrapporter) {
        Stelnummer = stelnummer;
        Model = model;
        Km_kørte = km_kørte;
        Tilstand = tilstand;
        Skadesrapporter = skadesrapporter;
    }

    public Bil(String stelnummer) {
        this.Stelnummer = stelnummer;
    }

    public String getStelnummer() {
        return Stelnummer;
    }

    public void setStelnummer(String stelnummer) {
        Stelnummer = stelnummer;
    }

    public BilTilstand getTilstand() {
        return Tilstand;
    }

    public void setTilstand(BilTilstand tilstand) {
        Tilstand = tilstand;
    }

    public BilModel getModel() {
        return Model;
    }

    public void setModel(BilModel model) {
        Model = model;
    }

    public List<SkadesRapport> getSkadesrapporter() {
        return Skadesrapporter;
    }

    public void setSkadesrapporter(List<SkadesRapport> skadesrapporter) {
        Skadesrapporter = skadesrapporter;
    }

    public double getKm_kørte() {
        return Km_kørte;
    }

    public void setKm_kørte(double km_kørte) {
        Km_kørte = km_kørte;
    }
}
