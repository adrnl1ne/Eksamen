package com.exam.service;

import java.util.List;

public class Abonnement {
    private List<Lejeaftale>Lejeaftalen; // Der er en lejeaftale for et abonnement, så dette burde ikke være en liste, men bare den Lejeaftale, som Abonnementet er lavet til
    // Mangler boolean for om abonnementet er Unlimited, isUnlimited
    private int kmprMd;
    private int AbonnementLængde;
    private double OverHalfPrice; // Det er ikke halfprice, bare: Over Afleverings Prisen, det er prisen for at aflevere en bilen forsent ud efter det Abonnement, som Lejeaftalen er lavet ud efter
    private double Priceprmonth;
    private double Udbetaling;
    private double Colorprice; // Prisen, hver måned ekstra, for at have selv valgt farven på bilen der er udlejet
    private double PriceForOverDrive; // Prisen, per kilometer over, for at køre mere end hvad der er aftalt da Lejeaftalen med dette abonnement blev lavet
}
