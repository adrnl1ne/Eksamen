package com.exam.repository;

import com.exam.service.Skadesrapport;

import java.util.List;

public class Skade {

    private List<SkadeType>Type; // Der er kun en type af skader, som en skade kan være, så dette burde ikke lave en lsite
     private double Price; // Denne pris bliver originalt bestemt ud fra skadetypen, men efter det er lagt ind i tabellen for skader, så er prisen for skaden fastsat
     private List<Skadesrapport>Skadesrapporten; // Der er kun en Skadesrapport for en skade, mens flere skader kan have den samme Skadesrapport, så her skal der bare være en Skadesrapport og ikke en liste
}
