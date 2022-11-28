package com.exam.service;

import com.exam.repository.Bil;

import java.util.List;

public class Abonnementspriser {
    private List<Bil>Bilen; // Dette skal ikke være en liste af Biler, men bare et objekt for en BilModel (En klasse der mangler)
    private double months; // Dette skal være prisen for at leje denne bilmodel for 3 måneder
    // Mangler et felt for prisen for at leje en bilmodel i 6 måneder
    // Mangler et felt for prisen for at leje en bilmodel i 12 måneder
    // Mangler et felt for prisen for at leje en bilmodel i 24 måneder
    // Mangler et felt for prisen for at leje en bilmodel i 36 måneder
    private double chosenColor; // Dette er prisen for selv at vælge farven på en bilmodel
    private double Udbetaling; // Dette er den pris en bilmodel kræver at der betales i starten af en Lejeaftale, dette er forskelligt fra bilmodel og nogle af dem har ingen altså 0 kr. i udbetaling

}
