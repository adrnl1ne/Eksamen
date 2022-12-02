package com.exam.model.entities.biler;

import java.time.LocalDate;

public class LejeAftale {
    private int LejeAftale_ID;
    private Bil Bilen;
    private SkadesRapport Rapport;
    private Abonnement abonnement;
    private Levering Leveringen;
    private Kunde Kunden;
    private KontaktInfo Kontakt;
    private LocalDate StartDate;
    private String Numberplate;

    public LejeAftale(int Lejeaftale_ID) {
        this.LejeAftale_ID = Lejeaftale_ID;
    }

    // getters and setters og toString




    public int getLejeAftale_ID() {
        return LejeAftale_ID;
    }

    public void setLejeAftale_ID(int lejeAftale_ID) {
        LejeAftale_ID = lejeAftale_ID;
    }

    public Bil getBilen() {
        return Bilen;
    }

    public void setBilen(Bil bilen) {
        Bilen = bilen;
    }

    public SkadesRapport getRapport() {
        return Rapport;
    }

    public void setRapport(SkadesRapport rapport) {
        Rapport = rapport;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public Levering getLeveringen() {
        return Leveringen;
    }

    public void setLeveringen(Levering leveringen) {
        Leveringen = leveringen;
    }

    public Kunde getKunden() {
        return Kunden;
    }

    public void setKunden(Kunde kunden) {
        Kunden = kunden;
    }

    public KontaktInfo getKontakt() {
        return Kontakt;
    }

    public void setKontakt(KontaktInfo kontakt) {
        Kontakt = kontakt;
    }

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate startDate) {
        StartDate = startDate;
    }

    public String getNumberplate() {
        return Numberplate;
    }

    public void setNumberplate(String numberplate) {
        Numberplate = numberplate;
    }

    @Override
    public String toString() {
        return "LejeAftale{" +
            "LejeAftale_ID=" + LejeAftale_ID +
            ", Bilen=" + Bilen +
            ", Rapport=" + Rapport +
            ", abonnement=" + abonnement +
            ", Leveringen=" + Leveringen +
            ", Kunden=" + Kunden +
            ", Kontakt=" + Kontakt +
            ", StartDate=" + StartDate +
            ", Numberplate='" + Numberplate + '\'' +
            '}';
    }
}
