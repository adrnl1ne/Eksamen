package com.exam.model.entities.biler;

import com.exam.repository.BilRepo;
import com.exam.repository.LejeaftaleRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    private LocalDate Slutdato;


    public double calculatePrice() {
        int abnmtLængde = abonnement.getAbonnementLængde();
        double prisPrMåned = abonnement.getPriceprmonth();
        double farveprisPrMåned = abonnement.getXtraColorprice();
        double sum = (prisPrMåned + farveprisPrMåned) * abnmtLængde + abonnement.getUdbetaling();
        return sum;
    }

    //Jakob
    /*public void setSlutdato(LocalDate Slutdato) {
        this.Slutdato = Slutdato;
    }*/

    //Jakob
    /*public LocalDate getSlutdato() {
        return Slutdato;
    }

    public LocalDate getSlutdatoDefault() {
        Slutdato = getStartDate().plusMonths(abonnement.getAbonnementLængde());
        return Slutdato;
    }


    public void setSlutdato(BilRepo bilRepo, LejeaftaleRepo lejeaftaleRepo) {
        bilRepo.viewBil(getBilen().getStelnummer());
        BilTilstand tilstand = getBilen().getTilstand();
        int Tilstands_ID = tilstand.getInt();
        if (Tilstands_ID == 3 && lejeaftaleRepo.isLejeperiodeOverstået(this)) {
            System.out.println("Slutdato er: " + getSlutdatoDefault());
            getSlutdatoDefault();
        } else {
            setSlutdato(LocalDate.now());
            System.out.println("Slutdato er: " + getSlutdato());

        }

    }*/

    public LejeAftale(int Lejeaftale_ID) {
        this.LejeAftale_ID = Lejeaftale_ID;
    }

    public LejeAftale(Bil bilen, Kunde kunden) {
        this.Bilen = bilen;
        this.Kunden = kunden;
        KontaktInfo kundensKontaktInfo = kunden.getNyesteinfo();
        this.Kontakt = kundensKontaktInfo;
        kundensKontaktInfo.setKundensAftale(this);
    }

    public String displayStartDate() {
        DateTimeFormatter formatedStartDate = DateTimeFormatter.ofPattern("dd_MM-yyyy");
        return StartDate.format(formatedStartDate);
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
