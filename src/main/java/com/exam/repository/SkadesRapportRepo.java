package com.exam.repository;


import com.exam.model.entities.biler.*;

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

  // Marcus
  private void createSkade(Skade skaden) {

    // Finder de værdier en Skade har, når den for første gang skal sættes/insertes ind i tabellen
    int SkadesRapport_ID = skaden.getSkadesrapporten().getSkadesrapport_ID();
    int Skadetype_ID = skaden.getType().getId();
    double pris;

    // Finder ud af om en helt ny skade har fået indsat en pris, skadens pris er 0, fordi den ikke er sat, og så finder vi en pris inde i skadetype tabellen
    if (skaden.getPrice() == 0.0) {
      pris = this.findSkadePris(Skadetype_ID);
    } else {
      pris = skaden.getPrice();
    }

    // og hvis Skadetype_ID'et ikke er mellem de typer af skader, så vil prisen være mere end nul og skaden vil blive oprettet på tabellen
    if (pris > 0) {
      try {
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
  }

  // Marcus
  private double findSkadePris(int skadetype_id) {
    // vil finde den pris, som er blevet sat til en skadetype i tabellen
    try {
      String skadeTypeQUERY = "SELECT Pris FROM skadetype WHERE Skadetype_Id = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(skadeTypeQUERY);
      preparedStatement.setInt(1, skadetype_id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getDouble("Pris");
      }
      return -1;

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at finde prisen for en SkadeType med dette ID: " + skadetype_id);
      throw new RuntimeException();
    }
  }

  // Marcus
  public SkadesRapport viewSkadesRapport(int Skaderapport_ID) {

    try {
      String skadesRapportQUERY = "SELECT * FROM skadesrapport WHERE Skaderapport_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(skadesRapportQUERY);
      preparedStatement.setInt(1, Skaderapport_ID);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        SkadesRapport skadesRapport = new SkadesRapport(Skaderapport_ID);
        int lejeAftale_ID = resultSet.getInt("Lejeaftale_ID");
        LejeAftale lejeAftale = new LejeaftaleRepo().viewLejeaftale(lejeAftale_ID);
        skadesRapport.setLejeaftalen(lejeAftale);

        String stelnummer = resultSet.getString("Stelnummer");
        Bil bil = new BilRepo().ViewBil(stelnummer);
        skadesRapport.setBilen(bil);

        Date afleveringsdato = resultSet.getDate("Afleveringsdato");
        skadesRapport.setAfleveringsdate(afleveringsdato);

        double kørselsdistance = resultSet.getDouble("Kørselsdistance");
        skadesRapport.setKørselsdistance(kørselsdistance);

        List<Skade> rapportensSkader = this.viewAlleSkader(skadesRapport);
        skadesRapport.setSkader(rapportensSkader);

        return skadesRapport;
      }
      return null;

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view, altså Select, en SkadesRapport med ID'et: " + Skaderapport_ID);
      throw new RuntimeException();
    }
  }

  private List<Skade> viewAlleSkader(SkadesRapport skadesRapport) {
    List<Skade> skader = new ArrayList<>();
    int skadesRapport_ID = skadesRapport.getSkadesrapport_ID();
    try {
      String skadeQUERY = "SELECT Skade_ID FROM skade WHERE Skaderapport_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(skadeQUERY);
      preparedStatement.setInt(1, skadesRapport_ID);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int Skade_ID = resultSet.getInt(1);
        Skade skade = this.viewSkade(Skade_ID);
        if (skade != null) {
          skade.setSkadesrapporten(skadesRapport);
          skader.add(skade);
        }
      }
      return skader;

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view alle skader for SkadeRapporten: " + skadesRapport);
      throw new RuntimeException();
    }
  }

  // Marcus
  private Skade viewSkade(int Skade_ID) {
    try {
      String skadeQUERY = "SELECT * FROM skade WHERE Skade_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(skadeQUERY);
      preparedStatement.setInt(1, Skade_ID);
      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        Skade skade = new Skade(Skade_ID);

        int SkadesRapport_ID = resultSet.getInt("Skadesrapport_ID"); // Kan ikke bruge denne værdi, da hvis jeg laver en SkadesRapport med den, så vil den køre i selvsving

        int SkadeType_ID = resultSet.getInt("Skadetype_ID");
        SkadeType skadeType = SkadeType.getEnum(SkadeType_ID);
        skade.setType(skadeType);

        double pris = resultSet.getInt("Pris");
        skade.setPrice(pris);

        return skade;
      }
      return null;

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view en Skade med ID'et: " + Skade_ID);
      throw new RuntimeException();
    }
  }

  // Marcus
  public List<SkadesRapport> viewAlleSkadesRapporter() {
    List<SkadesRapport> skadesRapporter = new ArrayList<>();
    try {
      String alleRapporterQUERY = "SELECT Skaderapport_ID FROM skadesrapport";
      PreparedStatement preparedStatement = DCM.prepareStatement(alleRapporterQUERY);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int SkadesRapport_ID = resultSet.getInt(1);
        SkadesRapport rapport = this.viewSkadesRapport(SkadesRapport_ID);
        if (rapport != null) {
          skadesRapporter.add(rapport);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view, altså Select, alle SkadesRapporter.");
    }
    return skadesRapporter;
  }

  // Marcus
  public void updateSkadesRapport(SkadesRapport skadesRapport) {
    // Finder de værdier i en SkadesRapport, som skal gemmes i SkadesRapport Tabellen
    int skadesRapport_ID = skadesRapport.getSkadesrapport_ID();
    int lejeAftale_ID = skadesRapport.getLejeaftalen().getLejeAftale_ID();
    String stelnummer = skadesRapport.getBilen().getStelnummer();
    Date afleveringsDato = skadesRapport.getAfleveringsdate();
    double kørselsdistance = skadesRapport.getKørselsdistance();

    // updater værdierne der lige er blevet fundet
    try {
      String updateQUERY = "UPDATE skadesrapport Set Lejeaftale_ID = ?, Stelnummer = ?, Afleveringsdato = ?, Kørselsdistance = ? WHERE Skaderapport_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(updateQUERY);
      preparedStatement.setInt(1, lejeAftale_ID);
      preparedStatement.setString(2, stelnummer);
      preparedStatement.setDate(3, (java.sql.Date) afleveringsDato);
      preparedStatement.setDouble(4, kørselsdistance);
      preparedStatement.setInt(5, skadesRapport_ID);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at Update SkadesRapporten: " + skadesRapport);
      throw new RuntimeException();
    }

    // Finder alle de skader en SkadeRapport har og updater dem, samt creater dem som er nye
    List<Skade> skader = skadesRapport.getSkader();
    for (Skade skade : skader) {
      updateSkade(skade);
    }
  }

  // Marcus
  private void updateSkade(Skade skade) {
    // Finder de værdier en skade har til at blive updated, sat ind i tabellen i stedet for hvad den skades værdi har i øjeblikket
    int skade_ID = skade.getSkade_ID();
    double pris = skade.getPrice();

    // Hvis en skades ID er 0 så må den være ny den skade vil blive Created
    if (skade_ID <= 0) {
      this.updateSkade(skade);
    } // Ellers updates skaden bare, som dens nye pris er, ikke muligt at skifte en skades type eller skaderapport
    else {
      try {
        String updateQUERY = "UPDATE skade SET Pris = ? WHERE Skade_ID = ?";
        PreparedStatement preparedStatement = DCM.prepareStatement(updateQUERY);
        preparedStatement.setDouble(1, pris);
        preparedStatement.setInt(2, skade_ID);
        preparedStatement.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Det var ikke muligt at update Skaden: " + skade);
        throw new RuntimeException();
      }
    }
  }

  // Marcus
  public void deleteSkadesRapport(SkadesRapport skadesRapport) {
    try {
      String deleteQUERY = "DELETE FROM skadesrapport WHERE Skaderapport_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(deleteQUERY);
      preparedStatement.setInt(1, skadesRapport.getSkadesrapport_ID());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at fjerne, altså Delete, SkadesRapporten: " + skadesRapport);
      throw new RuntimeException();
    }
  }

  public void deleteSkade(Skade skade) {
    try {
      String deleteQUERY = "DELETE FROM skade WHERE Skade_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(deleteQUERY);
      preparedStatement.setInt(1, skade.getSkade_ID());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at fjerne, altså Delete, Skaden: " + skade);
      throw new RuntimeException();
    }
  }

}
