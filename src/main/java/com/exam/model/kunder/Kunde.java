package com.exam.model.kunder;

import com.exam.service.LejeAftale;

public class Kunde {
    private LejeAftale lejeaftaler;

    {
        new LejeAftale();
    }

    private KontaktInfo Nyestelinfo;

    {
        new KontaktInfo();
    }

    private int cprnumber;
    private int RegNum;
    private int KontoNum;

    public Kunde(LejeAftale lejeaftaler, KontaktInfo kontaktinfo, int CPR) {
        this.lejeaftaler = lejeaftaler;
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
