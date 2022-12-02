package com.exam.repository;

import com.exam.model.entities.biler.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KundeRepo {

  private final Connection DCM = com.exam.utilities.DCM.getConn();

  // Marcus
  public void createKunde(Kunde kunde) {
    String CPR = kunde.getCprnumber();
    int regNum = kunde.getRegNum();
    int kontoNum = kunde.getKontoNum();
    boolean erNyKunde = true;

    try {
      String findKundeQUERY = "SELECT * FROM kunde WHERE CPR = ?";
      PreparedStatement prepStatement = DCM.prepareStatement(findKundeQUERY);
      prepStatement.setString(1, CPR);
      ResultSet resultSet = prepStatement.executeQuery();
      if (resultSet.next()) {
        erNyKunde = false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view, altså Selecte Kunden: " + kunde);
      throw new RuntimeException();
    }

    if (erNyKunde) {
      try {
        String QUERY = "INSERT INTO kunde (CPR, RegNum, KontoNum) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
        preparedStatement.setString(1, CPR);
        preparedStatement.setInt(2, regNum);
        preparedStatement.setInt(3, kontoNum);
        preparedStatement.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Not possible to create, to Insert, a Kunde: " + kunde);
        throw new RuntimeException(e);
      }
    } else {
      this.updateKunde(kunde);
    }

  }

  // Marcus
  private KontaktInfo findNyesteKontaktFor (Kunde kunde) {
    // Finder kundens primær nøgle, CPR
    String CPR = kunde.getCprnumber();

    try {
      String KontaktInfoQUERY = "SELECT * FROM kontaktinfo WHERE CPR = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(KontaktInfoQUERY);
      preparedStatement.setString(1, CPR);
      ResultSet resultSet = preparedStatement.executeQuery();
      List<KontaktInfo> kontaktinformationer = new ArrayList<>();
      while (resultSet.next()) {
        KontaktInfo kontaktInfo = new KontaktInfo(kunde);
        int lejeAftale_ID = resultSet.getInt("Lejeaftale_ID");
        kontaktInfo.setKundensAftale(new LejeAftale(lejeAftale_ID));
        String fornavn = resultSet.getString("Fornavn");
        kontaktInfo.setFirstName(fornavn);
        String efternavn = resultSet.getString("Efternavn");
        kontaktInfo.setLastName(efternavn);
        String adresse = resultSet.getString("Adresse");
        kontaktInfo.setAddress(adresse);
        int postnr = resultSet.getInt("Postnr");
        kontaktInfo.setPostnr(postnr);
        String by = resultSet.getString("By");
        kontaktInfo.setCity(by);
        String email = resultSet.getString("Mail");
        kontaktInfo.setEmail(email);
        int mobil = resultSet.getInt("Mobil");
        kontaktInfo.setMobilNumber(mobil);
        int counter = resultSet.getInt("Counter");
        kontaktInfo.setCounter(counter);
        kontaktinformationer.add(kontaktInfo);
      }

      KontaktInfo newestKontaktInfo = null;
      int highestCounter = 0;
      for (KontaktInfo kInfo : kontaktinformationer) {
        int theCounter = kInfo.getCounter();
        if (theCounter > highestCounter) {
          newestKontaktInfo = kInfo;
        }
      }

      if (newestKontaktInfo != null) {
        int lejeAftalens_ID = newestKontaktInfo.getKundensAftale().getLejeAftale_ID();
        LejeAftale kontaktLejeAftale = new LejeaftaleRepo().viewLejeaftale(lejeAftalens_ID);
        newestKontaktInfo.setKundensAftale(kontaktLejeAftale);
      }

      return newestKontaktInfo;

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view nyeste kontaktinfo for Kunden: " + kunde);
      throw new RuntimeException();
    }
  }

  // Marcus
  public Kunde viewKunde(String CPR) {
    try {
      String KundeQUERY = "SELECT * FROM kunde WHERE CPR = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(KundeQUERY);
      preparedStatement.setString(1, CPR);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        String CPRNum = resultSet.getString("CPR");
        int regNum = resultSet.getInt("RegNum");
        int kontoNum = resultSet.getInt("KontoNum");
        Kunde kunde = new Kunde(CPRNum);
        kunde.setRegNum(regNum);
        kunde.setKontoNum(kontoNum);

        // Finder den nyeste kontaktinfo for denne kunde med det CPR som blev givet og giver det til vores kunde
        KontaktInfo nyesteKontaktInfo = this.findNyesteKontaktFor(kunde);
        kunde.setNyesteInfo(nyesteKontaktInfo);


        // Tilføjer alle de Lejeaftaler en kunde har
        List<LejeAftale> lejeAftaler = new LejeaftaleRepo().viewAlleLejeaftaler();
        for (LejeAftale lejeAftale : lejeAftaler) {
          String lejeAftalesKundesCPR = lejeAftale.getKunden().getCprnumber();
          if (lejeAftalesKundesCPR.equals(kunde.getCprnumber())) {
            lejeAftaler.add(lejeAftale);
          }
        }
        kunde.setLejeaftaler(lejeAftaler);
        return kunde;
      }
      return null;

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Not possible to view a Kunde med CPR:" + CPR);
      throw new RuntimeException(e);
    }
  }

  // Marcus
  public List<Kunde> viewAllKunder() {
    List<Kunde> alleKunder = new ArrayList<>();

    try {
      String QUERY = "SELECT CPR FROM kunde";
      PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        String CPR = resultSet.getString(1);
        Kunde kunde = viewKunde(CPR);
        alleKunder.add(kunde);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Var ikke muligt at viewAllKunder");
      throw new RuntimeException(e);
    }

    return alleKunder;
  }

  // Marcus
  public void updateKunde(Kunde kunde) {
    try {
      String CPR = kunde.getCprnumber();
      int regNum = kunde.getRegNum();
      int kontoNum = kunde.getKontoNum();
      String QUERY = "UPDATE kunde SET RegNum = ?, KontoNum = ? WHERE CPR = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
      preparedStatement.setInt(1, regNum);
      preparedStatement.setInt(2, kontoNum);
      preparedStatement.setString(3, CPR);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at opdatere kunden: " + kunde);
      throw new RuntimeException();
    }
  }

  public void deleteKunde(Kunde kunde) {
    try {
      String CPR = kunde.getCprnumber();
      String QUERY = "DELETE FROM kunde WHERE CPR = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
      preparedStatement.setString(1, CPR);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at delete kunden: " + kunde);
      throw new RuntimeException(e);
    }
  }
}