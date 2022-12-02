package com.exam.model.entities.biler;

import java.util.List;

public class Kunde {
    private List<LejeAftale> lejeaftaler;

    private KontaktInfo Nyestelinfo;

    private String cprnumber;
    private int RegNum;
    private int KontoNum;

    public Kunde(LejeAftale lejeaftaler, KontaktInfo kontaktinfo, String CPR) {
        Nyestelinfo = kontaktinfo;
        cprnumber = CPR;
    }

    public Kunde (String CPR) {
        this.cprnumber = CPR;
    }

    // Getters, Setters and toString()


    public List<LejeAftale> getLejeaftaler() {
        return lejeaftaler;
    }

    public void setLejeaftaler(List<LejeAftale> lejeaftaler) {
        this.lejeaftaler = lejeaftaler;
    }

    public KontaktInfo getNyestelinfo() {
        return Nyestelinfo;
    }

    public void setNyesteInfo(KontaktInfo nyestelinfo) {
        Nyestelinfo = nyestelinfo;
    }

    public String getCprnumber() {
        return cprnumber;
    }

    public void setCprnumber(String cprnumber) {
        this.cprnumber = cprnumber;
    }

    public int getRegNum() {
        return RegNum;
    }

    public void setRegNum(int regNum) {
        RegNum = regNum;
    }

    public int getKontoNum() {
        return KontoNum;
    }

    public void setKontoNum(int kontoNum) {
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
