package com.exam.model.entities.biler;

import java.util.List;

public class Bil {
    private String Stelnummer;
    private BilTilstand Tilstand;
    private BilModel Model;
    private List<SkadesRapport> Skadesrapporter;
    private double Km_kørte;
}
