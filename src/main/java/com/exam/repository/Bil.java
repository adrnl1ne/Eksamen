package com.exam.repository;

import com.exam.model.biler.BilTilstand;
import com.exam.model.biler.Udstyr;
import com.exam.service.Skadesrapport;

import java.util.List;

public class Bil {
    private List<Udstyr>Udstyrliste; // Det er en bilmodel, som har udstyr for sig og en bil har kun det udstyr som den bilmodel bilen er har, så det skal ikke være her
    private List<BilTilstand>Tilstand; // En bil kan un være i en tilstand, så det skal bare være en Enum der enten er KLAR, UDLEJET, CHECKUP eller SKADET
    private List<Energitype>Energi; // En bilmodel er det som har en Energitype og ud efter hvilken model en bil er, så har den Energitype, så det skal ikke være her
    private List<Skadesrapport>Skaderapporter;

    private String FrameNumber; // Hvis du mener stelnummer, så er det korrekt
    private double Kmkørte; // Det antal kilometer en bil har kørt over sit liv, dette bliver løbende opdateret, efter en bil har været til checkup
}
