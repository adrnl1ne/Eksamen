package com.exam.model.biler;

import com.exam.repository.EnergiType;
import com.exam.service.AbonnementsPriser;

public class BilModel {

    private AbonnementsPriser AboPris = new AbonnementsPriser();
    private String brand;
    private String model;
    private double c02PerKm;
    private double steelPrice;
    private double km;
    private boolean isGearManuel = false;
    private boolean isGearAutomatic = false;
    private EnergiType energiType;




}



