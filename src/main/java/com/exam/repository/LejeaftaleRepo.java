package com.exam.repository;

import com.exam.model.entities.biler.*;
import com.exam.utilities.RentingOutNoneReadyCarException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LejeaftaleRepo {

  private final Connection DCM = com.exam.utilities.DCM.getConn();


  // Marcus
  private int findNyesteLejeAftale_ID() {
    try {
      String newestLejeaftaleQUERY = "SELECT MAX(Lejeaftale_ID) FROM lejeaftale";
      PreparedStatement preparedStatement1 = DCM.prepareStatement(newestLejeaftaleQUERY);
      ResultSet resultSet = preparedStatement1.executeQuery();
      if (resultSet.next()) {
        return resultSet.getInt("Lejeaftale_ID");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at finde, altså Selecte, det højeste ID, altså ID'et til den nyeste Lejeaftale der er i tabellen.");
      throw new RuntimeException();
    }
    return -1;
  }

  // Marcus
  public void createLejeaftale(LejeAftale lejeAftale) throws RentingOutNoneReadyCarException {
    int lejeaftalens_ID;

    Bil potentielBilTilUdlejning = lejeAftale.getBilen();
    BilTilstand lejeBilensTilstand = potentielBilTilUdlejning.getTilstand();

    if (lejeBilensTilstand != BilTilstand.KLAR){
      System.err.println("En LejeAftale forsøgte at udleje en bil med Tilstanden: " + lejeBilensTilstand + ", LejeAftalen der blev modtaget af metoden, createLejeAftale, var: " + lejeAftale);
      throw new RentingOutNoneReadyCarException(potentielBilTilUdlejning.toString());
    }

    potentielBilTilUdlejning.setTilstand(BilTilstand.UDLEJET);
    new BilRepo().updateBilModel(potentielBilTilUdlejning);
    Abonnement abonnement = lejeAftale.getAbonnement();
    Levering levering = lejeAftale.getLeveringen();
    KontaktInfo kontakt = lejeAftale.getKontakt();
    String CPR = lejeAftale.getKunden().getCprnumber();



    String stelnummer = potentielBilTilUdlejning.getStelnummer();
    LocalDate startDato = lejeAftale.getStartDate();
    if (startDato == null) {
      startDato = LocalDate.now();
    }
    String nummerplade = lejeAftale.getNumberplate();

    try {
      String QUERY = "INSERT INTO lejeaftale (CPR, Stelnummer, StartDato, Nummerplade) VALUES (?, ?, ?, ?)";
      PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
      preparedStatement.setString(1, CPR);
      preparedStatement.setString(2, stelnummer);
      preparedStatement.setDate(3, java.sql.Date.valueOf(startDato));
      preparedStatement.setString(4, nummerplade);
      preparedStatement.executeUpdate();

      lejeaftalens_ID = this.findNyesteLejeAftale_ID();
      lejeAftale.setLejeAftale_ID(lejeaftalens_ID);



    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at create, altså inserte i tabellen, LejeAftalen: " + lejeAftale);
      throw new RuntimeException(e);
    }


    if (lejeaftalens_ID > 0) {
      this.createAbonnement(abonnement);
      this.createLevering(levering);
      this.createKontaktinfo(kontakt);
      new KundeRepo().createKunde(lejeAftale.getKunden());
    } else {
      this.deleteLejeaftale(lejeAftale);
    }
  }

  // Marcus
  private void createKontaktinfo(KontaktInfo kontakt) {
    int lejeaftalens_ID = kontakt.getKundensAftale().getLejeAftale_ID();
    String CPR = kontakt.getKundensAftale().getKunden().getCprnumber();
    String fornavn = kontakt.getFirstName();
    String efternavn = kontakt.getLastName();
    String adresse = kontakt.getAddress();
    int postNr = kontakt.getPostnr();
    String email = kontakt.getEmail();
    int mobil = kontakt.getMobilNumber();
    String by = kontakt.getCity();

    try {

      String kontaktQUERY = "INSERT INTO kontaktinfo (Lejeaftale_ID, CPR, Fornavn, Efternavn, Adresse, Postnr, Mail, Mobil, By) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement preparedStatement4 = DCM.prepareStatement(kontaktQUERY);
      preparedStatement4.setInt(1, lejeaftalens_ID);
      preparedStatement4.setString(2, CPR);
      preparedStatement4.setString(3, fornavn);
      preparedStatement4.setString(4, efternavn);
      preparedStatement4.setString(5, adresse);
      preparedStatement4.setInt(6, postNr);
      preparedStatement4.setString(7, email);
      preparedStatement4.setInt(8, mobil);
      preparedStatement4.setString(9, by);
      preparedStatement4.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at create, altså Inserte i tabellen, Kontaktinformationen: " + kontakt);
      throw new RuntimeException(e);
    }
  }

  // Marcus
  private void createLevering(Levering levering) {
    int lejeaftalens_ID = levering.getLejeaftalen().getLejeAftale_ID();
    int leveringsType_ID = levering.getType().getId();
    String leveringsAdresse = levering.getLeveringsadresse();
    String afleveringsAdresse = levering.getAfleveringsadress();
    double kørtDistanceFørUdlejning = levering.getKørselDistanceInden();
    double transporttillæg = levering.getTransportTillæg();

    try {
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
      System.err.println("Det var ikke muligt at create, altså Inserte i tabellen, Leveringen: " + levering);
      throw new RuntimeException();
    }
  }

  // Marcus
  private void createAbonnement(Abonnement abonnement) {
    int lejeaftalens_ID = abonnement.getLejeaftalen().getLejeAftale_ID();
    boolean isUnlimited = abonnement.isUnlimited();
    int kmPrMd = abonnement.getKmprMd();
    int abonnementLængde = abonnement.getAbonnementLængde();
    double overAflPris = abonnement.getAfleveringPrice();
    double prisPrMd = abonnement.getPriceprmonth();
    double udbetaling = abonnement.getUdbetaling();
    double farvePrisPrMd = abonnement.getXtraColorprice();
    double prisPrKmOver = abonnement.getPriceForOverDrive();

    try {

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
      PreparedStatement preparedStatement = DCM.prepareStatement(lejeaftaleQUERY);
      preparedStatement.setInt(1, Lejeaftale_ID);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        int leje_ID = resultSet.getInt("Lejeaftale_ID");
        lejeAftalen = new LejeAftale(leje_ID);

        List<SkadesRapport> alleSkadesRapporter = new SkadesRapportRepo().viewAlleSkadesRapporter();
        for (SkadesRapport rapport : alleSkadesRapporter) {
          int rapportensLejeAftale_ID = rapport.getLejeaftalen().getLejeAftale_ID();
          if (rapportensLejeAftale_ID == leje_ID) {
            lejeAftalen.setRapport(rapport);
          }
        }

        String CPR = resultSet.getString("CPR");
        kunden = new KundeRepo().viewKunde(CPR);
        lejeAftalen.setKunden(kunden);

        String stelnummer = resultSet.getString("Stelnummer");
        Bil udlejetBil = new BilRepo().ViewBil(stelnummer);
        lejeAftalen.setBilen(udlejetBil);

        LocalDate startDato = resultSet.getDate("StartDato").toLocalDate();
        lejeAftalen.setStartDate(startDato);
        String nummerplade = resultSet.getString("Nummerplade");
        lejeAftalen.setNumberplate(nummerplade);

        // Creating the Abonnement object from the table
        abmnt = this.viewAbonnement(lejeAftalen);
        lejeAftalen.setAbonnement(abmnt);

        // Creating the Levering objekt from the table
        levering = this.viewLevering(lejeAftalen);
        lejeAftalen.setLeveringen(levering);

        // Creating the KontaktInfo object from the table
        kontaktInfo = this.viewKontaktInfo(lejeAftalen);
        lejeAftalen.setKontakt(kontaktInfo);



        return lejeAftalen;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view, alstå få Lejeaftalen fra tabellen, ved brug af Lejeaftale_ID: " + Lejeaftale_ID);
      throw new RuntimeException();
    }
    return null;
  }

  // Marcus
  private KontaktInfo viewKontaktInfo(LejeAftale lejeAftalen) {
    int lejeAftale_ID = lejeAftalen.getLejeAftale_ID();
    Kunde kunden = lejeAftalen.getKunden();
    KontaktInfo kontaktInfo = new KontaktInfo(lejeAftalen);
    kontaktInfo.setKunden(kunden);

    try {
      String kontaktQUERY = "SELECT * FROM kontaktinfo WHERE kontaktinfo.Lejeaftale_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(kontaktQUERY);
      preparedStatement.setInt(1, lejeAftale_ID);

      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        String fornavn = resultSet.getString("Fornavn");
        kontaktInfo.setFirstName(fornavn);

        String efternavn = resultSet.getString("Efternavn");
        kontaktInfo.setLastName(efternavn);

        String adresse = resultSet.getString("Adresse");
        kontaktInfo.setAddress(adresse);

        int postnr = resultSet.getInt("Postnr");
        kontaktInfo.setPostnr(postnr);

        String mail = resultSet.getString("Mail");
        kontaktInfo.setEmail(mail);

        int mobil = resultSet.getInt("Mobil");
        kontaktInfo.setMobilNumber(mobil);

        String by = resultSet.getString("By");
        kontaktInfo.setCity(by);

        int counter = resultSet.getInt("Counter");
        kontaktInfo.setCounter(counter);

        return kontaktInfo;
      }
      return null;

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view, altså Select, KontaktInformatione: " + kontaktInfo);
      throw new RuntimeException();
    }
  }

  // Marcus
  private Levering viewLevering(LejeAftale lejeAftalen) {

    int lejeAftale_ID = lejeAftalen.getLejeAftale_ID();
    Levering levering = new Levering(lejeAftalen);

    try {
      String leveringQUERY = "SELECT * FROM levering WHERE levering.Lejeaftale_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(leveringQUERY);
      preparedStatement.setInt(1, lejeAftale_ID);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        int leveringsType_ID = resultSet.getInt("LeveringsType_ID");
        LeveringsType leveringsType = LeveringsType.getType(leveringsType_ID);
        levering.setType(leveringsType);

        String leveringsAdresse = resultSet.getString("Leveringsadresse");
        levering.setLeveringsadresse(leveringsAdresse);

        String afleveringsAdresse = resultSet.getString("Afleveringsadresse");
        levering.setAfleveringsadress(afleveringsAdresse);

        double kmKørt = resultSet.getDouble("Km_Kørt");
        levering.setKørselDistanceInden(kmKørt);

        double transporttillæg = resultSet.getDouble("Transporttillæg");
        levering.setTransportTillæg(transporttillæg);

        return levering;
      }
      return null;

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view, altså Select, Leveringen: " + levering);
      throw new RuntimeException();
    }
  }

  // Marcus
  private Abonnement viewAbonnement(LejeAftale lejeAftale) {
    int lejeAftale_ID = lejeAftale.getLejeAftale_ID();
    Abonnement lejeAftalesAbo = new Abonnement(lejeAftale);

    try {
      String abonnementQUERY = "SELECT * FROM abnmt WHERE abnmt.Lejeaftale_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(abonnementQUERY);
      preparedStatement.setInt(1, lejeAftale_ID);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        boolean isUnlimited = resultSet.getBoolean("isUnlimited");
        lejeAftalesAbo.setUnlimited(isUnlimited);

        int kmPrMd = resultSet.getInt("KmPrMd");
        lejeAftalesAbo.setKmprMd(kmPrMd);

        int aboLængde = resultSet.getInt("AbnmtLængde");
        lejeAftalesAbo.setAbonnementLængde(aboLængde);

        double overAflPris = resultSet.getDouble("OverAflPris");
        lejeAftalesAbo.setAfleveringPrice(overAflPris);

        double prisPrMd =  resultSet.getDouble("PrisPrMåned");
        lejeAftalesAbo.setPriceprmonth(prisPrMd);

        double udbetaling = resultSet.getDouble("Udbetaling");
        lejeAftalesAbo.setUdbetaling(udbetaling);

        double farvePrisPrMd = resultSet.getDouble("FarvePrisPrMåned");
        lejeAftalesAbo.setXtraColorprice(farvePrisPrMd);

        double prisPrKmOver = resultSet.getDouble("PrisPrKmOver");
        lejeAftalesAbo.setPriceForOverDrive(prisPrKmOver);

        return lejeAftalesAbo;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt view, altså Select, Abonnementet: " + lejeAftalesAbo);
      throw new RuntimeException();
    }
    return null;
    }



  // Marcus
  public List<LejeAftale> viewAlleLejeaftaler() {
    List<LejeAftale> lejeaftaler = new ArrayList<>();
    try {
      String alleLejeaftalerQUERY = "SELECT Lejeaftale_ID FROM lejeaftale";
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



  // Marcus
  public void updateLejeaftale(LejeAftale lejeAftale) {

    // Finder de værdier i en lejeAftale, som skal gemmes i LejeAftale Tabellen
    int lejeAftale_ID = lejeAftale.getLejeAftale_ID();
    String CPR_Number = lejeAftale.getKunden().getCprnumber();
    String stelnummer = lejeAftale.getBilen().getStelnummer();
    LocalDate startDato = lejeAftale.getStartDate();
    String nummerplade = lejeAftale.getNumberplate();

    // updater værdierne der lige er blevet fundet
    try {
      String lejeAftaleQUERY = "UPDATE lejeaftale SET CPR = ?, Stelnummer = ?, StartDato = ?, Nummerplade = ? WHERE Lejeaftale_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(lejeAftaleQUERY);
      preparedStatement.setString(1, CPR_Number);
      preparedStatement.setString(2, stelnummer);
      preparedStatement.setDate(3, java.sql.Date.valueOf(startDato));
      preparedStatement.setString(4, nummerplade);
      preparedStatement.setInt(5, lejeAftale_ID);
      preparedStatement.executeUpdate();

      // Updater de individuelle objekter inde i en lejeAftale
      this.updateAbonnement(lejeAftale.getAbonnement());
      this.updateLevering(lejeAftale.getLeveringen());
      this.updateKontaktInfo(lejeAftale.getKontakt());

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

    // Finder alle de værdier der er i en lejeaftales KontaktInfo, som så skal updates
    String CPR = kontakt.getKunden().getCprnumber();
    String fornavn = kontakt.getFirstName();
    String efternavn = kontakt.getLastName();
    String adresse = kontakt.getAddress();
    int postnr = kontakt.getPostnr();
    String mail = kontakt.getEmail();
    int mobil = kontakt.getMobilNumber();
    String by = kontakt.getCity();

    // Updater de fundne værdier med dem i tabellen
    try { // Mangler at indføre By
      String kontaktQUERY = "UPDATE kontaktinfo SET CPR = ?, Fornavn = ?, Efternavn = ?, Adresse = ?, Postnr = ?, Mail = ?, Mobil = ?, 'By' = ? WHERE Lejeaftale_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(kontaktQUERY);
      preparedStatement.setString(1, CPR);
      preparedStatement.setString(2, fornavn);
      preparedStatement.setString(3, efternavn);
      preparedStatement.setString(4, adresse);
      preparedStatement.setInt(5, postnr);
      preparedStatement.setString(6, mail);
      preparedStatement.setInt(7, mobil);
      preparedStatement.setString(8, by);
      preparedStatement.setInt(9, lejeAftale_ID);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at Update KontaktInformationen: " + kontakt);
      throw new RuntimeException();
    }

  }
}

