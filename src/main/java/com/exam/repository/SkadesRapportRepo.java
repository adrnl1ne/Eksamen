package com.exam.repository;


import com.exam.model.entities.biler.Skade;
import com.exam.model.entities.biler.SkadesRapport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SkadesRapportRepo {

  private final Connection DCM = com.exam.utilities.DCM.getConn();

  // Marcus
  public void createSkadesRapport(SkadesRapport skadesRapport) {
    try {
      // Finder de værdier som skal Insertes i tabellen for Skadesrapporter
      int Lejeaftale_ID = skadesRapport.getLejeaftalen().getLejeAftale_ID();
      String Stelnummer = skadesRapport.getBilen().getStelnummer();
      Date afleveringsdate = skadesRapport.getAfleveringsdate();
      double kørselsdistance = skadesRapport.getKørselsdistance();


      // Inserter de fundne værdier for skadesrapporten

      String InsertQUERY = "INSERT INTO skadesrapport (Lejeaftale_ID, Stelnummer, Afleveringsdato, Kørselsdistance) VALUES (?, ?, ?, ?)";
      PreparedStatement preparedStatement1 = DCM.prepareStatement(InsertQUERY);
      preparedStatement1.setInt(1, Lejeaftale_ID);
      preparedStatement1.setString(2, Stelnummer);
      preparedStatement1.setDate(3, (java.sql.Date) afleveringsdate);
      preparedStatement1.setDouble(4, kørselsdistance);
      preparedStatement1.executeUpdate();

      // Finder den nyeste SkadesRapport, som lige er blevet lagt ind i tabellen, og finder dens ID
      String SelectQUERY = "SELECT MAX(Skaderapport_ID) FROM skadesrapport";
      PreparedStatement preparedStatement2 = DCM.prepareStatement(SelectQUERY);
      ResultSet resultSet = preparedStatement2.executeQuery();
      if (resultSet.next()) {
        int SkadesRapport_ID = resultSet.getInt("Skaderapport_ID");
        skadesRapport.setSkadesrapport_ID(SkadesRapport_ID);
        List<Skade> skader = skadesRapport.getSkader();
        // Finder hver skade i en liste af skader og Inserter dem ind i tabellen for skader
        for (Skade skade : skader) {
          this.createSkade(skade);
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at create, altså Inserte, SkadesRapporten: " + skadesRapport);
      throw new RuntimeException();
    }
  }

  private void createSkade(Skade skaden) {
    // Finder de værdier en Skade har, når den for første gang skal sættes/insertes ind i tabellen
    try {
      int SkadesRapport_ID = skaden.getSkadesrapporten().getSkadesrapport_ID();
      int Skadetype_ID = skaden.getType().getId();
      double pris = skaden.getPrice();

      // Inserter de fundne værdier
      String InsertQUERY = "INSERT INTO skade (Skaderapport_ID, Skadetype_ID, Pris) VALUES (?, ?, ?)";
      PreparedStatement preparedStatement = DCM.prepareStatement(InsertQUERY);
      preparedStatement.setInt(1, SkadesRapport_ID);
      preparedStatement.setInt(2, Skadetype_ID);
      preparedStatement.setDouble(3, pris);
      preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at create Skaden: " + skaden);
      throw new RuntimeException();
    }
  }

  // Marcus
  public SkadesRapport viewSkadesRapport(int Skaderapport_ID) {

    return null;
  }

  // Marcus
  public List<SkadesRapport> viewAlleSkadesRapporter() {
    List<SkadesRapport> skadesRapporter = new ArrayList<>();

    return skadesRapporter;
  }

  // Marcus
  public void updateSkadesRapport(SkadesRapport skadesRapport) {

  }

  // Marcus
  public void deleteSkadesRapport(SkadesRapport skadesRapport) {

  }

}
