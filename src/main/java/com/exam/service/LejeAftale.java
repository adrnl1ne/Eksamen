package com.exam.service;

import com.exam.model.kunder.KontaktInfo;
import com.exam.repository.Bil;

public class LejeAftale {
    private int LejeAftale_ID;
    private Bil Bilen;
    private SkadesRapport Rapport;
    private Abonnement abonnement;
    private Levering Leveringen;
    private KontaktInfo Kontakt;
    private String StartDate;
    private String Numberplate;
}
