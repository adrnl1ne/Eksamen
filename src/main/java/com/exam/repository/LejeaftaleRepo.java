package com.exam.repository;

import com.exam.model.entities.biler.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LejeaftaleRepo {

  private final Connection DCM = com.exam.utilities.DCM.getConn();


  // Marcus
  public void createLejeaftale(LejeAftale lejeAftale) {
    Abonnement abonnement = lejeAftale.getAbonnement();
    Levering levering = lejeAftale.getLeveringen();
    KontaktInfo kontakt = lejeAftale.getKontakt();

    int lejeaftalens_ID;
    int CPR = lejeAftale.getKunden().getCprnumber();
    String stelnummer = lejeAftale.getBilen().getStelnummer();
    Date startDato = lejeAftale.getStartDate();
    String nummerplade = lejeAftale.getNumberplate();

    try {
      String QUERY = "INSERT INTO lejeaftale (CPR, Stelnummer, StartDato, Nummerplade) VALUES (?, ?, ?, ?)";
      PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
      preparedStatement.setInt(1, CPR);
      preparedStatement.setString(2, stelnummer);
      preparedStatement.setDate(3, (java.sql.Date) startDato);
      preparedStatement.setString(4, nummerplade);
      preparedStatement.executeUpdate();
      String newestLejeaftaleQUERY = "SELECT MAX(Lejeaftale_ID) FROM lejeaftale";
      PreparedStatement preparedStatement1 = DCM.prepareStatement(newestLejeaftaleQUERY);
      ResultSet resultSet = preparedStatement1.executeQuery();
      lejeaftalens_ID = resultSet.getInt("Lejeaftale_ID");

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at create, altså inserte i tabellen, LejeAftalen: " + lejeAftale);
      throw new RuntimeException(e);
    }


    if (lejeaftalens_ID > 0) {
      try {
        boolean isUnlimited = abonnement.isUnlimited();
        int kmPrMd = abonnement.getKmprMd();
        int abonnementLængde = abonnement.getAbonnementLængde();
        double overAflPris = abonnement.getAfleveringPrice();
        double prisPrMd = abonnement.getPriceprmonth();
        double udbetaling = abonnement.getUdbetaling();
        double farvePrisPrMd = abonnement.getXtraColorprice();
        double prisPrKmOver = abonnement.getPriceForOverDrive();
        String abonnementQUERY = "INSERT INTO abnmt (Lejeaftale_ID, isUnlimited, KmPrMd, AbnmtLængde, OverAflPris, PrisPrMåned, Udbetaling, FarvePrisPrMåned, PrisPrKmOver) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement2 = DCM.prepareStatement(abonnementQUERY);
        preparedStatement2.setInt(1, lejeaftalens_ID);
        preparedStatement2.setBoolean(2, isUnlimited);
        preparedStatement2.setInt(3, kmPrMd);
        preparedStatement2.setInt(4, abonnementLængde);
        preparedStatement2.setDouble(5, overAflPris);
        preparedStatement2.setDouble(6, prisPrMd);
        preparedStatement2.setDouble(7, udbetaling);
        preparedStatement2.setDouble(8, farvePrisPrMd);
        preparedStatement2.setDouble(9, prisPrKmOver);
        preparedStatement2.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Det var ikke muligt at create, altså inserte i tabellen, Abonnementet: " + abonnement);
        throw new RuntimeException();
      }

      try {
        int leveringsType_ID = levering.getType().getId();
        String leveringsAdresse = levering.getLeveringsadresse();
        String afleveringsAdresse = levering.getAfleveringsadress();
        double kørtDistanceFørUdlejning = levering.getKørselDistanceInden();
        double transporttillæg = levering.getTransportTillæg();

        String leveringQUERY = "INSERT INTO levering (Lejeaftale_ID, LeveringsType_ID, Leveringsadresse, Afleveringsadresse, Km_Kørt, TransportTillæg) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement3 = DCM.prepareStatement(leveringQUERY);
        preparedStatement3.setInt(1, lejeaftalens_ID);
        preparedStatement3.setInt(2, leveringsType_ID);
        preparedStatement3.setString(3, leveringsAdresse);
        preparedStatement3.setString(4, afleveringsAdresse);
        preparedStatement3.setDouble(5, kørtDistanceFørUdlejning);
        preparedStatement3.setDouble(6, transporttillæg);
        preparedStatement3.executeUpdate();

      } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Det var ikke muligt at create, altså inserte i tabellen, Leveringen: " + levering);
        throw new RuntimeException();
      }

      try {
        String fornavn = kontakt.getFirstName();
        String efternavn = kontakt.getLastName();
        String adresse = kontakt.getAddress();
        int postNr = kontakt.getPostnr();
        // String by = kontakt.getCity();
        String email = kontakt.getEmail();
        int mobil = kontakt.getMobilnumber();

        String kontaktQUERY = "INSERT INTO kontaktinfo (Lejeaftale_ID, CPR, Fornavn, Efternavn, Adresse, Postnr, Mail, Mobil) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement4 = DCM.prepareStatement(kontaktQUERY);
        preparedStatement4.setInt(1, lejeaftalens_ID);
        preparedStatement4.setInt(2, CPR);
        preparedStatement4.setString(3, fornavn);
        preparedStatement4.setString(4, efternavn);
        preparedStatement4.setString(5, adresse);
        preparedStatement4.setInt(6, postNr);
        // preparedStatement4.setString(X, by); mangler by i tabellen og i kontaktQUERY
        preparedStatement4.setString(7, email);
        preparedStatement4.setInt(8, mobil);
        preparedStatement4.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Det var ikke muligt at create, altså Inserte i tabellen, Kontaktinformationen: " + kontakt);
        throw new RuntimeException(e);
      }
    }

  }

  // Marcus
  public LejeAftale viewLejeaftale(int Lejeaftale_ID) {
    try {
      LejeAftale lejeAftalen;
      Abonnement abmnt;
      Levering levering;
      KontaktInfo kontaktInfo;
      Kunde kunden;
      // Creating the LejeAftale object from the table
      String lejeaftaleQUERY = "SELECT * FROM lejeaftale WHERE lejeaftale.Lejeaftale_ID = ?";
      PreparedStatement preparedStatement1 = DCM.prepareStatement(lejeaftaleQUERY);
      preparedStatement1.setInt(1, Lejeaftale_ID);
      ResultSet resultSet1 = preparedStatement1.executeQuery();
      if (resultSet1.next()) {
        int leje_ID = resultSet1.getInt("Lejeaftale_ID");
        int CPR = resultSet1.getInt("CPR");
        kunden = new KundeRepo().viewKunde(CPR);
        String stelnummer = resultSet1.getString("Stelnummer");
        Bil udlejetBil = new BilRepo().ViewBil(stelnummer);
        Date startDato = resultSet1.getDate("StartDato");
        String nummerplade = resultSet1.getString("Nummerplade");
        lejeAftalen = new LejeAftale(leje_ID);
        lejeAftalen.setKunden(kunden);
        lejeAftalen.setBilen(udlejetBil);
        lejeAftalen.setStartDate(startDato);
        lejeAftalen.setNumberplate(nummerplade);

        // Creating the Abonnement object from the table
        String abonnementQUERY = "SELECT * FROM abnmt WHERE abnmt.Lejeaftale_ID = ?";
        PreparedStatement preparedStatement2 = DCM.prepareStatement(abonnementQUERY);
        preparedStatement2.setInt(1, Lejeaftale_ID);
        ResultSet resultSet2 = preparedStatement2.executeQuery();
        if (resultSet2.next()) {
          abmnt = new Abonnement(lejeAftalen);
          boolean isUnlimited = resultSet2.getBoolean("isUnlimited");
          abmnt.setUnlimited(isUnlimited);
          int kmPrMd = resultSet2.getInt("KmPrMd");
          abmnt.setKmprMd(kmPrMd);
          int aboLængde = resultSet2.getInt("AbnmtLængde");
          abmnt.setAbonnementLængde(aboLængde);
          double overAflPris = resultSet2.getDouble("OverAflPris");
          abmnt.setAfleveringPrice(overAflPris);
          double prisPrMd =  resultSet2.getDouble("PrisPrMåned");
          abmnt.setPriceprmonth(prisPrMd);
          double udbetaling = resultSet2.getDouble("Udbetaling");
          abmnt.setUdbetaling(udbetaling);
          double farvePrisPrMd = resultSet2.getDouble("FarvePrisPrMåned");
          abmnt.setXtraColorprice(farvePrisPrMd);
          double prisPrKmOver = resultSet2.getDouble("PrisPrKmOver");
          abmnt.setPriceForOverDrive(prisPrKmOver);
          lejeAftalen.setAbonnement(abmnt);

          // Creating the Levering objekt from the table
          String leveringQUERY = "SELECT * FROM levering WHERE levering.Lejeaftale_ID = ?";
          PreparedStatement preparedStatement3 = DCM.prepareStatement(leveringQUERY);
          preparedStatement3.setInt(1, Lejeaftale_ID);
          ResultSet resultSet3 = preparedStatement3.executeQuery();
          if (resultSet3.next()) {
            levering = new Levering(lejeAftalen);
            LeveringsType leveringsType = LeveringsType.getType(resultSet3.getInt("LeveringsType_ID"));
            levering.setType(leveringsType);
            String leveringsAdresse = resultSet3.getString("Leveringsadresse");
            levering.setLeveringsadresse(leveringsAdresse);
            String afleveringsAdresse = resultSet3.getString("Afleveringsadresse");
            levering.setAfleveringsadress(afleveringsAdresse);
            double kmKørt = resultSet3.getDouble("Km_Kørt");
            levering.setKørselDistanceInden(kmKørt);
            double transporttillæg = resultSet3.getDouble("Transporttillæg");
            levering.setTransportTillæg(transporttillæg);
            lejeAftalen.setLeveringen(levering);

            // Creating the KontaktInfo object from the table
            String kontaktQUERY = "SELECT * FROM kontaktinfo WHERE kontaktinfo.Lejeaftale_ID = ?";
            PreparedStatement preparedStatement4 = DCM.prepareStatement(kontaktQUERY);
            preparedStatement4.setInt(1, Lejeaftale_ID);
            ResultSet resultSet4 = preparedStatement4.executeQuery();
            if (resultSet4.next()) {
              kontaktInfo = new KontaktInfo(lejeAftalen);
              kontaktInfo.setKunden(kunden);
              String fornavn = resultSet4.getString("Fornavn");
              kontaktInfo.setFirstName(fornavn);
              String efternavn = resultSet4.getString("Efternavn");
              kontaktInfo.setLastName(efternavn);
              String adresse = resultSet4.getString("Adresse");
              kontaktInfo.setAddress(adresse);
              int postnr = resultSet4.getInt("Postnr");
              kontaktInfo.setPostnr(postnr);
              String mail = resultSet4.getString("Mail");
              kontaktInfo.setEmail(mail);
              int mobil = resultSet4.getInt("Mobil");
              kontaktInfo.setMobil(mobil);
              int counter = resultSet4.getInt("Counter");
              kontaktInfo.setCounter(counter);
              lejeAftalen.setKontakt(kontaktInfo);

              return lejeAftalen;
            }
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view, alstå få Lejeaftalen fra tabellen, ved brug af Lejeaftale_ID: " + Lejeaftale_ID);
      throw new RuntimeException();
    }
    return null;
  }

  // Marcus
  public List<LejeAftale> viewAlleLejeaftaler() {
    List<LejeAftale> lejeaftaler = new ArrayList<>();
    try {
      String alleLejeaftalerQUERY = "SELECT * FROM lejeaftale";
      PreparedStatement preparedStatement = DCM.prepareStatement(alleLejeaftalerQUERY);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int lejeAftale_ID = resultSet.getInt("Lejeaftale_ID");
        LejeAftale lejeAftale = viewLejeaftale(lejeAftale_ID);
        lejeaftaler.add(lejeAftale);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view alle Lejeaftaler fra tabellen");
      throw new RuntimeException();
    }
    return lejeaftaler;
  }



  public void updateLejeaftale(LejeAftale lejeAftale) {
    // Updater de individuelle objekter inde i en lejeAftale
    this.updateAbonnement(lejeAftale.getAbonnement());
    this.updateLevering(lejeAftale.getLeveringen());
    this.updateKontaktInfo(lejeAftale.getKontakt());

    // Finder de værdier i en lejeAftale, som skal gemmes i LejeAftale Tabellen
    int lejeAftale_ID = lejeAftale.getLejeAftale_ID();
    int CPR_Number = lejeAftale.getKunden().getCprnumber();
    String stelnummer = lejeAftale.getBilen().getStelnummer();
    Date startDato = lejeAftale.getStartDate();
    String nummerplade = lejeAftale.getNumberplate();

    // updater værdierne der lige er blevet fundet
    try {
      String lejeAftaleQUERY = "UPDATE lejeaftale SET CPR = ?, Stelnummer = ?, StartDato = ?, Nummerplade = ? WHERE Lejeaftale_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(lejeAftaleQUERY);
      preparedStatement.setInt(1, CPR_Number);
      preparedStatement.setString(2, stelnummer);
      preparedStatement.setDate(3, (java.sql.Date) startDato);
      preparedStatement.setString(4, nummerplade);
      preparedStatement.setInt(5, lejeAftale_ID);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at update LejeAftalen: " + lejeAftale);
      throw new RuntimeException();
    }

  }

  // Marcus
  public void deleteLejeaftale(LejeAftale lejeAftale) {
    try {
      int LejeAftale_ID = lejeAftale.getLejeAftale_ID();
      String QUERY ="DELETE FROM lejeaftale WHERE Lejeaftale_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
      preparedStatement.setInt(1, LejeAftale_ID);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at delete Lejeaftalen: " + lejeAftale);
      throw new RuntimeException(e);
    }

  }

  // Marcus
  private void updateAbonnement(Abonnement abonnement) {
    // Finder id'et til hvor i tabellen dette Abonnement skal updates
    int lejeAftale_ID = abonnement.getLejeaftalen().getLejeAftale_ID();

    // Finder alle de værdier der er i en lejeaftales abonnement, som så skal updates
    boolean isUnlimited = abonnement.isUnlimited();
    int kmPrMd = abonnement.getKmprMd();
    int abonnementLængde = abonnement.getAbonnementLængde();
    double overAflPris = abonnement.getAfleveringPrice();
    double prisPrMd = abonnement.getPriceprmonth();
    double udbetaling = abonnement.getUdbetaling();
    double farvePrisPrMd = abonnement.getXtraColorprice();
    double prisPrKmOver = abonnement.getPriceForOverDrive();

    // Updater de fundne værdier med dem i tabellen
    try {
      String abonnementQUERY = "UPDATE abnmt SET isUnlimited = ?, KmPrMd = ?, AbnmtLængde = ?, OverAflPris = ?, PrisPrMåned = ?, Udbetaling = ?, FarvePrisPrMåned = ?, PrisPrKmOver = ? WHERE Lejeaftale_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(abonnementQUERY);
      preparedStatement.setBoolean(1, isUnlimited);
      preparedStatement.setInt(2, kmPrMd);
      preparedStatement.setInt(3, abonnementLængde);
      preparedStatement.setDouble(4, overAflPris);
      preparedStatement.setDouble(5, prisPrMd);
      preparedStatement.setDouble(6, udbetaling);
      preparedStatement.setDouble(7, farvePrisPrMd);
      preparedStatement.setDouble(8, prisPrKmOver);
      preparedStatement.setInt(9, lejeAftale_ID);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at update Abonnementet: " + abonnement);
      throw new RuntimeException();
    }
  }

  // Marcus
  private void updateLevering(Levering leveringen) {
    // Finder id'et til hvor i tabellen denne Levering skal updates
    int lejeAftale_ID = leveringen.getLejeaftalen().getLejeAftale_ID();

    // Finder alle de værdier der er i en lejeaftales levering, som så skal updates
    int LeveringsType_ID = leveringen.getType().getId();
    String leveringsAdresse = leveringen.getLeveringsadresse();
    String afleveringsAdresse = leveringen.getAfleveringsadress();
    double kmKørtFørUdlej = leveringen.getKørselDistanceInden();
    double transporttillæg = leveringen.getTransportTillæg();

    // Updater tabellen med de fundne værdier
    try {
      String leveringQUERY = "UPDATE levering SET LeveringsType_ID = ?, Leveringsadresse = ?, Afleveringsadresse = ?, Km_Kørt = ?, TransportTillæg = ? WHERE Lejeaftale_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(leveringQUERY);
      preparedStatement.setInt(1, LeveringsType_ID);
      preparedStatement.setString(2, leveringsAdresse);
      preparedStatement.setString(3, afleveringsAdresse);
      preparedStatement.setDouble(4, kmKørtFørUdlej);
      preparedStatement.setDouble(5, transporttillæg);
      preparedStatement.setInt(6, lejeAftale_ID);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at Update Leveringen: " + leveringen);
    }

  }

  // Marcus
  private void updateKontaktInfo(KontaktInfo kontakt) {
    // Finder id'et til hvor i tabellen denne Levering skal updates
    int lejeAftale_ID = kontakt.getKundensAftale().getLejeAftale_ID();

    // Finder alle de værdier der er i en lejeaftales levering, som så skal updates
    int CPR = kontakt.getKunden().getCprnumber();
    String fornavn = kontakt.getFirstName();
    String efternavn = kontakt.getLastName();
    String adresse = kontakt.getAddress();
    int postnr = kontakt.getPostnr();
    // String by = kontakt.getCity(); Mangler at have By som et felt inde i tabellen for kontaktinformationer
    String mail = kontakt.getEmail();
    int mobil = kontakt.getMobil();

    // Updater de fundne værdier med dem i tabellen
    try { // Mangler at indføre By
      String kontaktQUERY = "UPDATE kontaktinfo SET CPR = ?, Fornavn = ?, Efternavn = ?, Adresse = ?, Postnr = ?, Mail = ?, Mobil = ? WHERE Lejeaftale_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(kontaktQUERY);
      preparedStatement.setInt(1, CPR);
      preparedStatement.setString(2, fornavn);
      preparedStatement.setString(3, efternavn);
      preparedStatement.setString(4, adresse);
      preparedStatement.setInt(5, postnr);
      preparedStatement.setString(6, mail);
      preparedStatement.setInt(7, mobil);
      preparedStatement.setInt(8, lejeAftale_ID);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at Update KontaktInformationen: " + kontakt);
      throw new RuntimeException();
    }

  }
}
