package com.exam.service;

import com.exam.repository.Bil;
import com.exam.repository.Skade;

import java.util.Date;

public class SkadesRapport {
    private int Skadesrapport_ID;
    private Bil Bilen;
    private Skade Skader;
    private LejeAftale Lejeaftalen;
    private Date Afleveringsdate;
    private double Kørselsdistance;
    private LejeAftale lejeaftalen = new LejeAftale();

    private Bil bil = new Bil();
}

