package com.exam.model.kunder;

import com.exam.service.Lejeaftale;

import java.util.List;

public class Kunde {
    private List<Lejeaftale>Lejeaftaler;
     private List<Kontaktinfo>NyestelInfo;
    private int RegNum;

    public Kunde(int regNum, int kontoNum) {
        RegNum = regNum;
        KontoNum = kontoNum;
    }

    private int KontoNum;


    @Override
    public String toString() {
        return "Kunde{" +
                "RegNum=" + RegNum +
                ", KontoNum=" + KontoNum +
                '}';
    }


}
