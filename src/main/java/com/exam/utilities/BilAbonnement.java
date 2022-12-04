package com.exam.utilities;

import com.exam.model.entities.biler.*;
import com.exam.repository.BilRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Marcus: Alt inde i denne klasse er lavet af mig
public class BilAbonnement {

  private static final BilRepo bilRepo = new BilRepo();
  private static final Connection DCM = com.exam.utilities.DCM.getConn();


  // Marcus
  private BilAbonnement() {

  }


  // Marcus
  public static LejeAftale simulateLejeAftale() throws NoCarReadyToRentOutException {
    // skab en tilfældig kunde med en kontaktinfo
    Kunde skabtKunde = simulateKundeInfo();

    // Find en tilfældig bil der har tilstand KLAR
    Bil enTilfældigKlarBil = simulateValgAfKlarBIL();

    // skab en Lejeaftale ud fra tilfældige valg
    LejeAftale enSimuleretLejeAftale = simulateLejeAftaleValg(enTilfældigKlarBil, skabtKunde);

    List<LejeAftale> kundensLejeAftaler = new ArrayList<>();
    kundensLejeAftaler.add(enSimuleretLejeAftale);
    skabtKunde.setLejeaftaler(kundensLejeAftaler);

    return enSimuleretLejeAftale;
  }




  private static LejeAftale simulateLejeAftaleValg(Bil enTilfældigKlarBil, Kunde skabtKunde) {
    LejeAftale simuleretLejeAftale = new LejeAftale(enTilfældigKlarBil, skabtKunde);

    LocalDate simulatedStartDato = simulateDatoValg();
    simuleretLejeAftale.setStartDate(simulatedStartDato);

    String simulatedNummerplade = simulateNumberplate();
    simuleretLejeAftale.setNumberplate(simulatedNummerplade);

    Levering simulatedLevering = simulateLevering(simuleretLejeAftale);
    simuleretLejeAftale.setLeveringen(simulatedLevering);

    Abonnement simulatedAbonnement = simulateAbonnement(simuleretLejeAftale);
    simuleretLejeAftale.setAbonnement(simulatedAbonnement);

    return simuleretLejeAftale;
  }

  private static Abonnement simulateAbonnement(LejeAftale simuleretLejeAftale) {
    Abonnement simuleretAbonnement = new Abonnement(simuleretLejeAftale);
    AbonnementsPriser udlejetBilModelsAbonnement = simuleretLejeAftale.getBilen().getModel().getAbopris();
    boolean isUnlimited = udlejetBilModelsAbonnement.isUnlimited();

    double prisPrMd;
    int abonnementslængde;
    int kmPrMd;
    if (isUnlimited) {
      double[] månederOgPris = pickRandomAbonnement(udlejetBilModelsAbonnement);
      prisPrMd = månederOgPris[0];
      abonnementslængde = (int) månederOgPris[1];
      kmPrMd = 1500;
    } else {
      prisPrMd = udlejetBilModelsAbonnement.getSiXMonthsPris();
      abonnementslængde = 6;
      kmPrMd = 2000;
    }

    double udbetaling = udlejetBilModelsAbonnement.getStartUdbetaling();
    double farvePris = simulateFarveValg(udlejetBilModelsAbonnement);
    double overAflPris = viewPrisliste("Overskredet Aflevering");
    double prisPrKmOver = viewPrisliste("Per Overkørt km");

    simuleretAbonnement.setUnlimited(isUnlimited);
    simuleretAbonnement.setPriceprmonth(prisPrMd);
    simuleretAbonnement.setAbonnementLængde(abonnementslængde);
    simuleretAbonnement.setKmprMd(kmPrMd);
    simuleretAbonnement.setUdbetaling(udbetaling);
    simuleretAbonnement.setXtraColorprice(farvePris);
    simuleretAbonnement.setAfleveringPrice(overAflPris);
    simuleretAbonnement.setPriceForOverDrive(prisPrKmOver);

    return simuleretAbonnement;
  }

  private static double simulateFarveValg(AbonnementsPriser udlejetBilsAbonnement) {
    double farvePrisPrMd = udlejetBilsAbonnement.getPriceForColorChoice();
    int randomNum = getRandomNum(4, 1);
    if (randomNum == 4) {
      return farvePrisPrMd;
    }
    return 0;
  }

  private static double[] pickRandomAbonnement(AbonnementsPriser udlejetBilModelsAbonnement) {
    List<double[]> prisOgMånedList = new ArrayList<>();

    double måneder;

    double md3Pris = udlejetBilModelsAbonnement.getThreeMonthsPris();
    if (md3Pris != 0){
      måneder = 3;
      double[] md3 = {md3Pris, måneder};
      prisOgMånedList.add(md3);
    }

    double md6Pris = udlejetBilModelsAbonnement.getSiXMonthsPris();
    if (md6Pris != 0){
      måneder = 6;
      double[] md6 = {md6Pris, måneder};
      prisOgMånedList.add(md6);
    }

    double md12Pris = udlejetBilModelsAbonnement.getTwelveMonthsPrice();
    if (md12Pris != 0){
      måneder = 12;
      double[] md12 = {md12Pris, måneder};
      prisOgMånedList.add(md12);
    }

    double md24Pris = udlejetBilModelsAbonnement.getTwentyFourMonthsPrice();
    if (md24Pris != 0){
      måneder = 24;
      double[] md24 = {md24Pris, måneder};
      prisOgMånedList.add(md24);
    }

    double md36Pris = udlejetBilModelsAbonnement.getThirtysixMonthsPrice();
    if (md36Pris != 0){
      måneder = 36;
      double[] md36 = {md36Pris, måneder};
      prisOgMånedList.add(md36);
    }

    int randomNum = getRandomNum(prisOgMånedList.size());
    return prisOgMånedList.get(randomNum);
  }

  private static Levering simulateLevering(LejeAftale simuleretLejeAftale) {
    Levering simulatedLevering = new Levering(simuleretLejeAftale);
    BilModel udlejetBilsModel = simuleretLejeAftale.getBilen().getModel();

    LeveringsType simulatedLeveringsType = decideLeveringsType(udlejetBilsModel);
    double transportTillæg = calculateTransportTillæg(simulatedLeveringsType);
    String leveringsAdresse = simulateLeveringsAdresse(simulatedLeveringsType);
    String afleveringsAdresse = leveringsAdresse;
    double kørselsdistanceFørUdlejning = simuleretLejeAftale.getBilen().getKm_kørte();

    simulatedLevering.setType(simulatedLeveringsType);
    simulatedLevering.setTransportTillæg(transportTillæg);
    simulatedLevering.setLeveringsadresse(leveringsAdresse);
    simulatedLevering.setAfleveringsadress(afleveringsAdresse);
    simulatedLevering.setKørselDistanceInden(kørselsdistanceFørUdlejning);

    return simulatedLevering;
  }

  private static double viewPrisliste(String fakturaTekst) {
    try {
      String prislisteSelectQUERY = "SELECT Pris FROM prisliste WHERE FakturaTekst = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(prislisteSelectQUERY);
      preparedStatement.setString(1, fakturaTekst);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getDouble("Pris");
      }
      throw new SQLException();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view, altså Selecte, fra prisliste tabellen, en Pris hvor FakturaTekst: " + fakturaTekst);
      throw new RuntimeException();
    }
  }

  private static double calculateTransportTillæg(LeveringsType simulatedLeveringsType) {
    if (simulatedLeveringsType == LeveringsType.STANDARD) {
      return 0;
    } else {
      return viewPrisliste("Transporttillæg");
    }
  }

  private static String simulateLeveringsAdresse(LeveringsType typeLevering) {
    if (typeLevering == LeveringsType.FDM) {
      return getRandomFDMAdresse();
    } else {
      return "Bilabonnement A/S - Rødrovre\nSlotsherrensvej 411C\n2619 Rødovre";
    }
  }

  private static String getRandomFDMAdresse() {
    int randomNum = getRandomNum(3, 1);
    switch (randomNum) {
      case 1:
        return "DS STORE VIRUM\nVirumgårdsvej 4-10\n2830 Virum";
      case 2:
        return "DS SALON ODENSE\nBrondovej 13-15\n5250 Odense SV";
      default:
        return "DS SALON SILKEBORG\nNørrevænget 9-23\n8600 Silkeborg";
    }
  }

  private static LeveringsType decideLeveringsType(BilModel udlejetBilsModel) {
    String bilensMærke = udlejetBilsModel.getMærke();
    switch (bilensMærke) {
      case "DS":
        return LeveringsType.FDM;
      default:
        return LeveringsType.STANDARD;
    }
  }

  private static String simulateNumberplate() {
    StringBuilder simuleretNummerplade = new StringBuilder();

    for (int i = 0; i < 2; i++) {
      simuleretNummerplade.append(pickRandomChar());
    }
    for (int i = 0; i < 5; i++) {
      simuleretNummerplade.append(getRandomNum(10));
    }
    return simuleretNummerplade.toString();
  }

  private static char pickRandomChar() {
    int randomNum = getRandomNum(28, 1);

    return switch (randomNum) {
      case 1 -> 'A';
      case 2 -> 'B';
      case 3 -> 'C';
      case 4 -> 'D';
      case 5 -> 'E';
      case 6 -> 'F';
      case 7 -> 'G';
      case 8 -> 'H';
      case 9 -> 'I';
      case 10 -> 'J';
      case 11 -> 'K';
      case 12 -> 'L';
      case 13 -> 'M';
      case 14 -> 'N';
      case 15 -> 'O';
      case 16 -> 'P';
      case 17 -> 'Q';
      case 18 -> 'R';
      case 19 -> 'S';
      case 20 -> 'T';
      case 21 -> 'U';
      case 22 -> 'V';
      case 23 -> 'X';
      case 24 -> 'Y';
      case 25 -> 'Z';
      case 26 -> 'Æ';
      case 27 -> 'Ø';
      default -> 'Å';
    };
  }

  private static LocalDate simulateDatoValg() {
    LocalDate simuleretDato = LocalDate.now();
    int dageTilStartDato = getRandomNum(7, 1);
    return simuleretDato.plusDays(dageTilStartDato);
  }

  private static Bil simulateValgAfKlarBIL() throws NoCarReadyToRentOutException {
    List<Bil> alleBiler = bilRepo.ViewAlleBiler();
    List<Bil> bilerDerErKLAR = new ArrayList<>();

    for (Bil bil : alleBiler) {
      BilTilstand bilensTilstand = bil.getTilstand();
      if (bilensTilstand == BilTilstand.KLAR) {
        bilerDerErKLAR.add(bil);
      }
    }

    int antalBilerDerErKLAR = bilerDerErKLAR.size();
    if (antalBilerDerErKLAR > 0) {
      int randomNum = getRandomNum(antalBilerDerErKLAR);
      return bilerDerErKLAR.get(randomNum);
    } else {
      System.err.println("Der blev forsøgt at simulere et valg af bil, ud fra en liste, hvor ingen af dem har BilTilstanden: KLAR, af alle Biler: " + alleBiler);
      throw new NoCarReadyToRentOutException("Antallet af alle Biler er: " + alleBiler.size() + ". Antallet af Biler der er KLAR: " + antalBilerDerErKLAR);
    }
  }

  private static Kunde simulateKundeInfo() {
    List<Kunde> potentieleKunder = getKunder();
    int randomNum = getRandomNum(potentieleKunder.size());
    return potentieleKunder.get(randomNum);
  }






  // MARCUS: alle disse get kunder metoder er lavet af mig
  private static List<Kunde> getKunder () {
    List<Kunde> kundeList = new ArrayList<>();

    Kunde kunde1 = getKunde1();
    kundeList.add(kunde1);

    Kunde kunde2 = getKunde2();
    kundeList.add(kunde2);

    Kunde kunde3 = getKunde3();
    kundeList.add(kunde3);

    Kunde kunde4 = getKunde4();
    kundeList.add(kunde4);

    Kunde kunde5 = getKunde5();
    kundeList.add(kunde5);

    Kunde kunde6 = getKunde6();
    kundeList.add(kunde6);

    Kunde kunde7 = getKunde7();
    kundeList.add(kunde7);

    Kunde kunde8 = getKunde8();
    kundeList.add(kunde8);

    Kunde kunde9 = getKunde9();
    kundeList.add(kunde9);

    Kunde kunde10 = getKunde10();
    kundeList.add(kunde10);

    return kundeList;
  }
  private static Kunde getKunde10() {
    Kunde kunde10 = new Kunde("311201-5119");
    kunde10.setRegNum("0000");
    kunde10.setKontoNum("4973620183");
    KontaktInfo kontaktInfo10 = new KontaktInfo(kunde10);
    kunde10.setNyesteInfo(kontaktInfo10);
    kontaktInfo10.setFirstName("Anders");
    kontaktInfo10.setAddress("Snaregade 8, 1.");
    kontaktInfo10.setPostnr(1205);
    kontaktInfo10.setCity("København K");

    int randomNum = getRandomNum(20, 1);
    if (randomNum < 15) {
      kontaktInfo10.setLastName("Sand");
      kontaktInfo10.setEmail("sand@email.dk");
      kontaktInfo10.setMobilNumber(98342716);
    } else {
      kontaktInfo10.setLastName("White");
      kontaktInfo10.setEmail("white@email.com");
      kontaktInfo10.setMobilNumber(35825616);
    }
    return kunde10;
  }
  private static Kunde getKunde9() {
    Kunde kunde9 = new Kunde("061200-1115");
    kunde9.setRegNum("9999");
    kunde9.setKontoNum("9836291204");
    KontaktInfo kontaktInfo9 = new KontaktInfo(kunde9);
    kunde9.setNyesteInfo(kontaktInfo9);
    kontaktInfo9.setFirstName("Sophie");
    kontaktInfo9.setLastName("Blomster");
    kontaktInfo9.setEmail("Soph99@email.dk");
    kontaktInfo9.setAddress("Læderstræde 32B");
    kontaktInfo9.setPostnr(1201);
    kontaktInfo9.setCity("København K");
    kontaktInfo9.setMobilNumber(53762938);
    return kunde9;
  }
  private static Kunde getKunde8() {
    Kunde kunde8 = new Kunde("200655-2356");
    kunde8.setRegNum("8888");
    kunde8.setKontoNum("6923405868");
    KontaktInfo kontaktInfo8 = new KontaktInfo(kunde8);
    kunde8.setNyesteInfo(kontaktInfo8);
    kontaktInfo8.setFirstName("Gertrud");
    kontaktInfo8.setLastName("Hildebrand");

    kontaktInfo8.setAddress("Naboløs 4");
    kontaktInfo8.setPostnr(1206);
    kontaktInfo8.setCity("København K");

    int randomNum = getRandomNum(5, 1);
    if (randomNum < 3) {
      kontaktInfo8.setEmail("brand@email.dk");
      kontaktInfo8.setMobilNumber(73452651);
    } else {
      kontaktInfo8.setEmail("hildebrand@email.com");
      kontaktInfo8.setMobilNumber(88453658);
    }
    return kunde8;
  }
  private static Kunde getKunde7() {
    Kunde kunde7 = new Kunde("050506-2415");
    kunde7.setRegNum("7777");
    kunde7.setKontoNum("8373920671");
    KontaktInfo kontaktInfo7 = new KontaktInfo(kunde7);
    kunde7.setNyesteInfo(kontaktInfo7);
    kontaktInfo7.setFirstName("Johan");
    kontaktInfo7.setLastName("Buller");
    kontaktInfo7.setMobilNumber(28595538);
    int randomNum = getRandomNum(3, 1);
    if (randomNum == 1) {
      kontaktInfo7.setEmail("bøllebuller@email.dk");
      kontaktInfo7.setAddress("Peter Fabers Gade 15, st. tv");
      kontaktInfo7.setPostnr(2200);
      kontaktInfo7.setCity("København N");
    } else if (randomNum == 2) {
      kontaktInfo7.setEmail("bul06@email.dk");
      kontaktInfo7.setAddress("Peter Fabers Gade 15, st. tv");
      kontaktInfo7.setPostnr(2200);
      kontaktInfo7.setCity("København N");
    } else {
      kontaktInfo7.setEmail("bul06@email.dk");
      kontaktInfo7.setAddress("Brolæggerstræde 5 4");
      kontaktInfo7.setPostnr(1211);
      kontaktInfo7.setCity("København K");
    }
    return kunde7;
  }
  private static Kunde getKunde6() {
    Kunde kunde6 = new Kunde("010101-0101");
    kunde6.setRegNum("6666");
    kunde6.setKontoNum("9483729657");
    KontaktInfo kontaktInfo6 = new KontaktInfo(kunde6);
    kunde6.setNyesteInfo(kontaktInfo6);
    kontaktInfo6.setFirstName("NumseManden");
    kontaktInfo6.setLastName("HanNumseKan");
    kontaktInfo6.setEmail("numsMigHer@email.anus");
    kontaktInfo6.setAddress("Afføringstræde 8, 4. th");
    kontaktInfo6.setPostnr(1666);
    kontaktInfo6.setCity("Tarm");
    kontaktInfo6.setMobilNumber(51535151);
    return kunde6;
  }
  private static Kunde getKunde5() {
    Kunde kunde5 = new Kunde("290402-6752");
    kunde5.setRegNum("5555");
    kunde5.setKontoNum("9856308931");
    KontaktInfo kontaktInfo5 = new KontaktInfo(kunde5);
    kunde5.setNyesteInfo(kontaktInfo5);
    kontaktInfo5.setFirstName("Anna");
    kontaktInfo5.setLastName("Rasmussen");

    kontaktInfo5.setAddress("Gedstedvej 25");
    kontaktInfo5.setPostnr(2770);
    kontaktInfo5.setCity("Kastrup");
    kontaktInfo5.setMobilNumber(29383746);
    int randomNum = getRandomNum(1, 6);
    if (randomNum < 4) {
      kontaktInfo5.setEmail("mussen@email.dk");
    } else {
      kontaktInfo5.setEmail("anna@email.com");
    }
    return kunde5;
  }
  private static Kunde getKunde4() {
    Kunde kunde4 = new Kunde("261000-1116");
    kunde4.setRegNum("4444");
    kunde4.setKontoNum("8735201907");
    KontaktInfo kontaktInfo4 = new KontaktInfo(kunde4);
    kunde4.setNyesteInfo(kontaktInfo4);
    kontaktInfo4.setFirstName("Louise");
    kontaktInfo4.setLastName("Gren");
    kontaktInfo4.setEmail("gren@email.dk");
    kontaktInfo4.setAddress("Olsbækdal 23");
    kontaktInfo4.setPostnr(2670);
    kontaktInfo4.setCity("Greve");
    kontaktInfo4.setMobilNumber(38532091);
    return kunde4;
  }
  private static Kunde getKunde3() {
    Kunde kunde3 = new Kunde("270378-5566");
    kunde3.setRegNum("3333");
    kunde3.setKontoNum("1234567890");
    KontaktInfo kontaktInfo3 = new KontaktInfo(kunde3);
    kunde3.setNyesteInfo(kontaktInfo3);
    kontaktInfo3.setFirstName("Grethe");
    kontaktInfo3.setLastName("Hansen");
    kontaktInfo3.setEmail("knallertGrethe@email.dk");
    kontaktInfo3.setAddress("Fuglegårdsvej 22B");
    kontaktInfo3.setPostnr(2820);
    kontaktInfo3.setCity("Gentofte");
    kontaktInfo3.setMobilNumber(75936769);
    return kunde3;
  }
  private static Kunde getKunde2() {
    Kunde kunde2 = new Kunde("061290-6666");
    kunde2.setRegNum("2222");
    kunde2.setKontoNum("2087654321");
    KontaktInfo kontaktInfo2 = new KontaktInfo(kunde2);
    kunde2.setNyesteInfo(kontaktInfo2);
    kontaktInfo2.setFirstName("Marie");
    kontaktInfo2.setLastName("Petersen");
    kontaktInfo2.setEmail("marie@email.dk");

    int randumNum = getRandomNum(20, 1);
    if (randumNum <= 18) {
      kontaktInfo2.setAddress("Frederiksvej 11B, 2.");
      kontaktInfo2.setPostnr(2000);
      kontaktInfo2.setCity("Frederiksberg");
      kontaktInfo2.setMobilNumber(13752915);
    } else {
      kontaktInfo2.setAddress("Nørregade 4, st.");
      kontaktInfo2.setPostnr(1165);
      kontaktInfo2.setCity("København K");
      kontaktInfo2.setMobilNumber(31355916);
    }
    return kunde2;
  }
  private static Kunde getKunde1() {
    Kunde kunde1 = new Kunde("061200-1115");
    kunde1.setRegNum("1111");
    kunde1.setKontoNum("1234567890");
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
