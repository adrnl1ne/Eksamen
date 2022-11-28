package com.exam.service;

import java.util.List;

public class Levering {
    private List<Leveringstype>Type; // En levering kan kun have en leveringstype, så dette skal ikke være en liste
    private List<Lejeaftale>Lejeaftalen; // Der er kun en Lejeaftale for hver Levering, så dette skal ikke en liste
    private String Leveringsadresse;
    // Der mangler en String Afleveringsadresse, for den adresse, som den udlejede bil for en Lejeaftale, skal afleveres af kunden efter at lejemålet er ovre
    private double KørselDistanceInden;
    private double TransportTillæg;

}
