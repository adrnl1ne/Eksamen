package com.exam.model.kunder;

import com.exam.service.Lejeaftale;

import java.util.List;

public class Kunde {
    private List<Lejeaftale>Lejeaftaler;
     private List<Kontaktinfo>NyestelInfo; // Dette skal ikke være en liste, det er bare den kontaktinformation for en kunde, som har den nyeste, hvilket kan ses fra Kontaktinfo tabellens Counter
    // Der mangler en int for kundens CPR nummer
    private int RegNum;
    private int KontoNum;

    public Kunde(int regNum, int kontoNum) { // Der skal overføres kundens lejeaftaler, selvom der ikke er nogen, deres CPR og nyeste kontaktinfo
        RegNum = regNum;
        KontoNum = kontoNum;
    }




    @Override
    public String toString() {
        return "Kunde{" +
                "RegNum=" + RegNum +
                ", KontoNum=" + KontoNum +
                '}';
    }


}
