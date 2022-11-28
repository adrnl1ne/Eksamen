package com.exam.repository;

import com.exam.model.biler.BilModel;
import com.exam.model.biler.BilTilstand;
import com.exam.service.SkadesRapport;

import java.util.List;

public class Bil {
    private String Stelnummer;
    private BilTilstand Tilstand;
    private BilModel Model;
    private SkadesRapport Skadesrapporter;
    private double Km_kørte_inden;
}
