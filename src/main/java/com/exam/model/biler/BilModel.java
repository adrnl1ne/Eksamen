package com.exam.model.biler;

import com.exam.service.Abonnementspriser;

import java.util.List;

public class BilModel {

    private List<Abonnementspriser>AboPris; // dette er ikke en liste af priser det er bare et objekt der har forskellige priser for en model af biler
    private String Design; // Du har oversat mærke til design, men det er et brand af biler, som Citröen
    private String Style; // Model er ikke en style men bare en car model, som at en Citröen har en model der hedder C1
    private double C02km; // Dette er det CO2 som biler af en specifik model udskyder for hver kilometer bilen kører
    private double SteelPrice; // Det er prisen for det stål bil af denne model vil være værd
    private double km; // Dette er Km-pr- (ladning eller liter) ud efter om det er en energi type ELEKTRISK eller BENZIN/DIESEL
    // Der mangler en boolean for om bil modelen har et manuelt gear eller automatisk
    // Der mangler den type energi (ENUM) som en bilmodel kan være



}



