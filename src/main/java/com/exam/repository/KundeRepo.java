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

  public void createKunde(Kunde kunde) {
    int CPR = kunde.getCprnumber();
    int regNum = kunde.getRegNum();
    int kontoNum = kunde.getKontoNum();
    try {
      String QUERY = "INSERT INTO kunde (CPR, RegNum, KontoNum) VALUES (?, ?, ?)";
      PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
      preparedStatement.setInt(1, CPR);
      preparedStatement.setInt(2, regNum);
      preparedStatement.setInt(3, kontoNum);
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Not possible to create a Kunde: " + kunde);
      throw new RuntimeException(e);
    }
  }

  public Kunde viewKunde(int CPR) {
    try {
      String KundeQUERY = "SELECT * FROM kunde WHERE CPR = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(KundeQUERY);
      preparedStatement.setInt(1, CPR);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        int CPRNum = resultSet.getInt("CPR");
        int regNum = resultSet.getInt("RegNum");
        int kontoNum = resultSet.getInt("KontoNum");
        Kunde kunde = new Kunde(CPRNum);
        kunde.setRegNum(regNum);
        kunde.setKontoNum(kontoNum);
        // Finder den nyeste kontaktinfo for denne kunde med det CPR som blev givet
        try {
          String KontaktInfoQUERY = "SELECT * FROM kontaktinfo WHERE CPR = ?";
          PreparedStatement preparedStatement2 = DCM.prepareStatement(KontaktInfoQUERY);
          preparedStatement2.setInt(1, CPRNum);
          ResultSet resultSet2 = preparedStatement2.executeQuery();
          List<KontaktInfo> kontaktinformationer = new ArrayList<>();
          while (resultSet2.next()) {
            KontaktInfo kontaktInfo = new KontaktInfo(kunde);
            String fornavn = resultSet2.getString("Fornavn");
            kontaktInfo.setFirstName(fornavn);
            String efternavn = resultSet2.getString("Efternavn");
            kontaktInfo.setLastName(efternavn);
            String adresse = resultSet2.getString("Adresse");
            kontaktInfo.setAddress(adresse);
            int postnr = resultSet2.getInt("Postnr");
            kontaktInfo.setPostnr(postnr);
            // String by = resultSet2.getString("By"); Mangler at indføre By i tabellen Kontaktinfo
            // kontaktInfo.setCity(by);
            String email = resultSet2.getString("Mail");
            kontaktInfo.setEmail(email);
            int mobil = resultSet2.getInt("Mobil");
            if (mobil != 0) {
              kontaktInfo.setMobilnumber(mobil);
            }
            int counter = resultSet2.getInt("Counter");
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
            kunde.setNyestelinfo(newestKontaktInfo);
          }
          return kunde;

        } catch (SQLException e) {
          e.printStackTrace();
          System.out.println("Det var ikke muligt at view nyeste kontaktinfo for kunden med CPR: " + CPR);
          throw new RuntimeException();
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Not possible to view a Kunde med CPR:" + CPR);
      throw new RuntimeException(e);
    }
    return null;
  }

  public List<Kunde> viewAllKunder() {
    List<Kunde> alleKunder = new ArrayList<>();

    try {
      String QUERY = "SELECT * FROM kunde";
      PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int CPR = resultSet.getInt("CPR");
        Kunde kunde = viewKunde(CPR);
        alleKunder.add(kunde);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Var ikke muligt at viewAllKunder");
      throw new RuntimeException(e);
    }

    return alleKunder;
  }

  public void updateKunde(Kunde kunde) {
    try {
      int CPR = kunde.getCprnumber();
      int regNum = kunde.getRegNum();
      int kontoNum = kunde.getKontoNum();
      String QUERY = "UPDATE kunde SET RegNum = ?, KontoNum = ? WHERE CPR = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
      preparedStatement.setInt(1, regNum);
      preparedStatement.setInt(2, kontoNum);
      preparedStatement.setInt(3, CPR);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Det var ikke muligt at opdatere kunden: " + kunde);
      throw new RuntimeException();
    }
  }

  public void deleteKunde(Kunde kunde) {
    try {
      int CPR = kunde.getCprnumber();
      String QUERY = "DELETE FROM kunde WHERE CPR = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
      preparedStatement.setInt(1, CPR);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("Det var ikke muligt at delete kunden: " + kunde);
      throw new RuntimeException(e);
    }
  }
}