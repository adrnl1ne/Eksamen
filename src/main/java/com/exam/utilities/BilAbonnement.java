package com.exam.utilities;

import com.exam.model.entities.biler.KontaktInfo;
import com.exam.model.entities.biler.Kunde;
import com.exam.model.entities.biler.LejeAftale;
import com.exam.repository.BilRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BilAbonnement {

  private static BilRepo bilRepo = new BilRepo();



  private BilAbonnement() {

  }


  public static LejeAftale simulateLejeAftale() {
    // skab en tilfældig kunde med en kontaktinfo
    Kunde skabtKunde = simulateKundeInfo();

    // Find en tilfældig bil der har tilstand KLAR

    // skab en Lejeaftale ud fra tilfældige valg

    return null;
  }

  private static Kunde simulateKundeInfo() {

    return null;
  }

  private static Kunde getKunde1() {
    Kunde kunde1 = new Kunde("061200-1115");
    kunde1.setRegNum(1111);
    kunde1.setKontoNum(1234567890);
    KontaktInfo kontaktInfo1 = new KontaktInfo(kunde1);
    kunde1.setNyesteInfo(kontaktInfo1);
    kontaktInfo1.setFirstName("Hans");
    kontaktInfo1.setLastName("Hansen");
    kontaktInfo1.setEmail("hans@email.dk");

    int randumNum = getRandomNum(20, 1);
    if (randumNum <= 18) {
      kontaktInfo1.setAddress("Verstergade 29, 1. tv");
      kontaktInfo1.setPostnr(1456);
      kontaktInfo1.setCity("København K");
      kontaktInfo1.setMobilNumber(15352515);
    } else {
      kontaktInfo1.setAddress("Verster Voldgade 7B, 2. th");
      kontaktInfo1.setPostnr(1456);
      kontaktInfo1.setCity("København V");
      kontaktInfo1.setMobilNumber(25354535);
    }
    return kunde1;
  }

  private static Kunde getKunde2() {
    Kunde kunde2 = new Kunde("061290-6666");
    kunde2.setRegNum(2222);
    kunde2.setKontoNum(2087654321);
    KontaktInfo kontaktInfo2 = new KontaktInfo(kunde2);
    kunde2.setNyesteInfo(kontaktInfo2);
    kontaktInfo2.setFirstName("Marie");
    kontaktInfo2.setLastName("Petersen");
    kontaktInfo2.setEmail("marie@email.dk");

    int randumNum = getRandomNum(20, 1);
    if (randumNum <= 18) {
      kontaktInfo2.setAddress("Verstergade 29, 1. tv");
      kontaktInfo2.setPostnr(1456);
      kontaktInfo2.setCity("København K");
      kontaktInfo2.setMobilNumber(15352515);
    } else {

    }
    return kunde2;
  }

  private static List<Kunde> getKunder () {
    List<Kunde> kundeList = new ArrayList<>();

    Kunde kunde1 = getKunde1();
    kundeList.add(kunde1);

    Kunde kunde2 = getKunde2();
    kundeList.add(kunde2);

    Kunde kunde3 = new Kunde("061200-1115");
    kunde3.setRegNum(1111);
    kunde3.setKontoNum(1234567890);
    KontaktInfo kontaktInfo3 = new KontaktInfo(kunde3);
    kunde1.setNyesteInfo(kontaktInfo3);
    kontaktInfo3.setFirstName("Hans");
    kontaktInfo3.setLastName("Hansen");
    kontaktInfo3.setEmail("hans@email.dk");
    kontaktInfo3.setAddress("Verstergade 29, 1. tv");
    kontaktInfo3.setPostnr(1456);
    kontaktInfo3.setCity("København K");
    kontaktInfo3.setMobilNumber(15352515);
    kundeList.add(kunde3);

    Kunde kunde4 = new Kunde("061200-1115");
    kunde4.setRegNum(1111);
    kunde4.setKontoNum(1234567890);
    KontaktInfo kontaktInfo4 = new KontaktInfo(kunde4);
    kunde1.setNyesteInfo(kontaktInfo4);
    kontaktInfo4.setFirstName("Hans");
    kontaktInfo4.setLastName("Hansen");
    kontaktInfo4.setEmail("hans@email.dk");
    kontaktInfo4.setAddress("Verstergade 29, 1. tv");
    kontaktInfo4.setPostnr(1456);
    kontaktInfo4.setCity("København K");
    kontaktInfo4.setMobilNumber(15352515);
    kundeList.add(kunde4);

    Kunde kunde5 = new Kunde("061200-1115");
    kunde5.setRegNum(1111);
    kunde5.setKontoNum(1234567890);
    KontaktInfo kontaktInfo5 = new KontaktInfo(kunde5);
    kunde1.setNyesteInfo(kontaktInfo5);
    kontaktInfo5.setFirstName("Hans");
    kontaktInfo5.setLastName("Hansen");
    kontaktInfo5.setEmail("hans@email.dk");
    kontaktInfo5.setAddress("Verstergade 29, 1. tv");
    kontaktInfo5.setPostnr(1456);
    kontaktInfo5.setCity("København K");
    kontaktInfo5.setMobilNumber(15352515);
    kundeList.add(kunde5);

    Kunde kunde6 = new Kunde("061200-1115");
    kunde6.setRegNum(1111);
    kunde6.setKontoNum(1234567890);
    KontaktInfo kontaktInfo6 = new KontaktInfo(kunde6);
    kunde1.setNyesteInfo(kontaktInfo6);
    kontaktInfo6.setFirstName("Hans");
    kontaktInfo6.setLastName("Hansen");
    kontaktInfo6.setEmail("hans@email.dk");
    kontaktInfo6.setAddress("Verstergade 29, 1. tv");
    kontaktInfo6.setPostnr(1456);
    kontaktInfo6.setCity("København K");
    kontaktInfo6.setMobilNumber(15352515);
    kundeList.add(kunde6);

    Kunde kunde7 = new Kunde("061200-1115");
    kunde7.setRegNum(1111);
    kunde7.setKontoNum(1234567890);
    KontaktInfo kontaktInfo7 = new KontaktInfo(kunde7);
    kunde1.setNyesteInfo(kontaktInfo7);
    kontaktInfo7.setFirstName("Hans");
    kontaktInfo7.setLastName("Hansen");
    kontaktInfo7.setEmail("hans@email.dk");
    kontaktInfo7.setAddress("Verstergade 29, 1. tv");
    kontaktInfo7.setPostnr(1456);
    kontaktInfo7.setCity("København K");
    kontaktInfo7.setMobilNumber(15352515);
    kundeList.add(kunde7);

    Kunde kunde8 = new Kunde("061200-1115");
    kunde8.setRegNum(1111);
    kunde8.setKontoNum(1234567890);
    KontaktInfo kontaktInfo8 = new KontaktInfo(kunde8);
    kunde1.setNyesteInfo(kontaktInfo8);
    kontaktInfo8.setFirstName("Hans");
    kontaktInfo8.setLastName("Hansen");
    kontaktInfo8.setEmail("hans@email.dk");
    kontaktInfo8.setAddress("Verstergade 29, 1. tv");
    kontaktInfo8.setPostnr(1456);
    kontaktInfo8.setCity("København K");
    kontaktInfo8.setMobilNumber(15352515);
    kundeList.add(kunde8);

    Kunde kunde9 = new Kunde("061200-1115");
    kunde9.setRegNum(1111);
    kunde9.setKontoNum(1234567890);
    KontaktInfo kontaktInfo9 = new KontaktInfo(kunde9);
    kunde1.setNyesteInfo(kontaktInfo9);
    kontaktInfo9.setFirstName("Hans");
    kontaktInfo9.setLastName("Hansen");
    kontaktInfo9.setEmail("hans@email.dk");
    kontaktInfo9.setAddress("Verstergade 29, 1. tv");
    kontaktInfo9.setPostnr(1456);
    kontaktInfo9.setCity("København K");
    kontaktInfo9.setMobilNumber(15352515);
    kundeList.add(kunde9);

    Kunde kunde10 = new Kunde("061200-1115");
    kunde10.setRegNum(1111);
    kunde10.setKontoNum(1234567890);
    KontaktInfo kontaktInfo10 = new KontaktInfo(kunde10);
    kunde1.setNyesteInfo(kontaktInfo10);
    kontaktInfo10.setFirstName("Hans");
    kontaktInfo10.setLastName("Hansen");
    kontaktInfo10.setEmail("hans@email.dk");
    kontaktInfo10.setAddress("Verstergade 29, 1. tv");
    kontaktInfo10.setPostnr(1456);
    kontaktInfo10.setCity("København K");
    kontaktInfo10.setMobilNumber(15352515);
    kundeList.add(kunde10);

    return kundeList;
  }


  // Metode der finder et random tal fra 0 til men ikke med et givet max tal
  private static int getRandomNum (int maxNum) {
    Random random = new Random();
    return random.nextInt(maxNum);
  }

  // Metode der finder et random tal fra et minimum tal (minNum burde sættes som 1) til et givet maximum tal plus det minimum tal (maxNum)
  public static int getRandomNum(int maxNum, int minNum) {
    Random random = new Random();
    return random.nextInt(maxNum) + minNum;
  }

}
