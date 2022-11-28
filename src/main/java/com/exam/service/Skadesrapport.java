package com.exam.service;

import com.exam.repository.SkadeType;

import java.util.List;

public class Skadesrapport {
    private List<SkadeType>Type; // Der kan være mange eller ingen skader i en skadesrapport, men det er en Skade en rapport holder øje med ikke en Skadetype, en Skade holder øje med Skadetype, så det er en liste men ikke med Skadetyper, men med Skader
    private List<Skadesrapport>Skaderapport; // En skadesrapport har ikke Skadesrapporter, faktisk har en rapport slet ikke styr på andre skadesrapporter, så dette felt burde slet ikke være der
    // Der mangler et objekt for den Lejeaftalem som en Skadesrapport er blevet lavet på
    // Der mangler et objekt for den Bil en skadesrapport er blevet udført på
    // Der mangler et Date attribut, for den dag hvor bilen blev afleveret til eftersyn
    // Der mangler en double attribut, for den distance bilen som rapporten er blevet udført på nu har efter et eftersyn og denne nye længde gemmes i Bil Tabellen for den bil rapporten er udført på
    private double Price; // En skadesrapport har ikke en pris attribut, da alle de Skader, som den har holder styr på hver deres pris, mens en rapport har en metode til at udregne prisen for skadesrapporten fra alle de skader, som den holder på

}
