package com.exam.repository;

import com.exam.model.entities.biler.AbonnementsPriser;
import com.exam.model.entities.biler.BilModel;
import com.exam.model.entities.biler.BilTilstand;
import com.exam.model.entities.biler.EnergiType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BilModelRepository {
  private final Connection DCM = com.exam.utilities.DCM.getConn();


  // Marcus
  private String viewBilmærke(int Bilmærke_ID) {
    try {
      String selectQUERY = "SELECT Mærket FROM mærke WHERE Bilmærke_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(selectQUERY);
      preparedStatement.setInt(1, Bilmærke_ID);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getString(1);
      }
      throw new SQLException();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at skabe et Bilmærke, altså view, et Bilmærke med ID'et: " + Bilmærke_ID);
      throw new RuntimeException();
    }
  }

  // Marcus
  private String viewUdstyr (int Udstyr_ID) {
    try {
      String SelectQUERY = "SELECT Udstyr FROM udstyr WHERE Udstyr_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(SelectQUERY);
      preparedStatement.setInt(1, Udstyr_ID);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getString(1);
      }
      throw new SQLException();
    } catch (SQLException e){
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view et udstyr med ID'et: " + Udstyr_ID);
      throw new RuntimeException();
    }
  }


  private List<String> viewAltUdstyrForEnBilModel(int BilModel_ID) {
    List<String> udstyrEnBilModelHar = new ArrayList<>();
    try {
      String selectQUERY = "SELECT Udstyr_ID FROM modelharudstyr WHERE Model_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(selectQUERY);
      preparedStatement.setInt(1, BilModel_ID);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int etUdstyrsID = resultSet.getInt(1);
        String etUdstyr = this.viewUdstyr(etUdstyrsID);
        udstyrEnBilModelHar.add(etUdstyr);
      }
      return udstyrEnBilModelHar;
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Der var ikke muligt at view alt det udstyr der er for en BilModel med ID'et: " + BilModel_ID);
      throw new RuntimeException();
    }
  }

  // Marcus
  public BilModel viewBilmodel(int Model_ID) {
    try {
      String SelectQUERY = "SELECT * FROM bilmodel WHERE Model_ID=?";
      PreparedStatement preparedStatement = DCM.prepareStatement(SelectQUERY);
      preparedStatement.setInt(1, Model_ID);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        int Bilmærke_ID = resultSet.getInt("Bilmærke_ID");
        String bilmærke = this.viewBilmærke(Bilmærke_ID);

        int Energitype_ID = resultSet.getInt("Energitype_ID");
        EnergiType energiType = EnergiType.getEnum(Energitype_ID);

        String model = resultSet.getString("Model");
        boolean isGearManuel = resultSet.getBoolean("isGearManuel");
        double CO2_Udslip = resultSet.getDouble("CO2_Udslip");
        double Stålpris = resultSet.getDouble("Stålpris");
        double KmPrX = resultSet.getDouble("KmPrX");
        List<String> udstyr = this.viewAltUdstyrForEnBilModel(Model_ID);

        BilModel bilModel = new BilModel(Model_ID);
        bilModel.setMærke(bilmærke);
        bilModel.setEnergitype(energiType);
        bilModel.setModel(model);
        bilModel.setGearManuel(isGearManuel);
        bilModel.setStålpris(Stålpris);
        bilModel.setCO2_Udslip(CO2_Udslip);
        bilModel.setKmPrX(KmPrX);
        bilModel.setUdstyr(udstyr);
        AbonnementsPriser abnmtpris = viewAbonnementsPriser(bilModel);
        bilModel.setAbopris(abnmtpris);
        return bilModel;
      }
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Kan ikke ikke se, altså view, en BilModel med model_ID'et: " + Model_ID);
      throw new RuntimeException(e);
    }
  }

  private AbonnementsPriser viewAbonnementsPriser(BilModel bilModel) {
    int Model_ID = bilModel.getModel_ID();
    try {
      String selectQUERY = "SELECT * FROM abnmtpris WHERE Model_ID = ?";
      PreparedStatement preparedStatement = DCM.prepareStatement(selectQUERY);
      preparedStatement.setInt(1, Model_ID);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        boolean isUnlimited = resultSet.getBoolean("isUnlimited");
        double md3Pris = resultSet.getDouble("Md3Pris");
        double md6Pris = resultSet.getDouble("Md6Pris");
        double md12Pris = resultSet.getDouble("Md12Pris");
        double md24Pris = resultSet.getDouble("Md24Pris");
        double md36Pris = resultSet.getDouble("Md36Pris");
        double farveValgPris = resultSet.getDouble("FarvePris");
        double udbetaling = resultSet.getDouble("Udbetaling");

        AbonnementsPriser abnmtpris = new AbonnementsPriser();
        abnmtpris.setBilModel(bilModel);
        abnmtpris.setUnlimited(isUnlimited);
        abnmtpris.setThreeMonthsPris(md3Pris);
        abnmtpris.setSiXMonthsPris(md6Pris);
        abnmtpris.setTwelveMonthsPrice(md12Pris);
        abnmtpris.setTwentyFourMonthsPrice(md24Pris);
        abnmtpris.setThirtysixMonthsPrice(md36Pris);
        abnmtpris.setPriceForColorChoice(farveValgPris);
        abnmtpris.setStartUdbetaling(udbetaling);
        return abnmtpris;
      }
      throw new SQLException();
    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Det var ikke muligt at view en AbonnementsPriser med ID'et: " + Model_ID);
      throw new RuntimeException();
    }
  }

  // Marcus
  public List<BilModel> viewAlleBilModeller() {
    List<BilModel> alleBilModeller = new ArrayList<>();

    try {
      String QUERY = "SELECT * FROM bilmodel";
      PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int Model_ID = resultSet.getInt("Model_ID");
        alleBilModeller.add(viewBilmodel(Model_ID));
      }

    } catch (SQLException e) {
      System.err.println("Fejl, Kan ikke se alle bilmodeller");
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return alleBilModeller;
  }

}
