package com.exam.repository;

import com.exam.model.biler.BilTilstand;
import com.exam.model.biler.Udstyr;
import com.exam.service.Skadesrapport;

import java.util.List;

public class Bil {
    private List<Udstyr>Udstyrliste;
    private List<BilTilstand>Tilstand;
    private List<Energitype>Energi;
    private List<Skadesrapport>Skaderapporter;

    private String FrameNumber;
    private double Kmkørte;
}
