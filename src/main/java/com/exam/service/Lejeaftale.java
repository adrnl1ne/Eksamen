package com.exam.service;

import com.exam.model.kunder.Kontaktinfo;
import com.exam.model.kunder.Kunde;
import com.exam.repository.Bil;

import java.util.List;

public class Lejeaftale {
    private List<Bil>Bilen; // Der kun en bil for en Lejaftale, så dette skal ikke være en liste bare en bil
    private List<Skadesrapport>Rapport; // Det blive kun udført en skadesrapport på en Lejeaftale, så det skal ikke være en liste
    private List<Abonnement>Abonnement; // Der er kun et Abonnement for en Lejeaftale, så det er ikke en liste
    private List<Levering>Levering; // Der er kun en Levering for en Lejeaftale, så det er ikke en liste
    private List<Kunde>Kunde; // En lejeaftale holder kun øje med en kunde, og ikke flere kunder, derfor er dette ikke en liste
    private List<Kontaktinfo>Kontakt; // En Lejeaftale har kun en kontaktinfo, så dette er heler ikke en liste
    // Det mangler et felt med datatypen Date, dette er start datoen for en lejeaftale, hvilket er fastsat af kunden da de laver aftalen
    private String Numberplate;
}
