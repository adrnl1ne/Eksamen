package com.exam.model.entities.biler;

import com.exam.model.entities.biler.KontaktInfo;
import com.exam.model.entities.biler.LejeAftale;

import java.util.List;

public class Kunde {
    private List<LejeAftale> lejeaftaler;

    {
    }

    private KontaktInfo Nyestelinfo;

    {
        new KontaktInfo();
    }

    private int cprnumber;
    private int RegNum;
    private int KontoNum;

    public Kunde(LejeAftale lejeaftaler, KontaktInfo kontaktinfo, int CPR) {
        Nyestelinfo = kontaktinfo;
        cprnumber = CPR;
    }




    @Override
    public String toString() {
        return "Kunde{" +
                "RegNum=" + RegNum +
                ", KontoNum=" + KontoNum +
                '}';
    }


}
