package com.exam.repository;

import com.exam.model.entities.biler.BilModel;
import com.exam.model.entities.biler.EnergiType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BilmodelRepo {
    //Jakob
    private final Connection DCM = com.exam.utilities.DCM.getConn();

    //Jakob
    public BilModel ViewBilmodel(int Model_ID) {
        try {
            String Model_ID_QUERY = "SELECT * FROM BilModel WHERE Model_ID=?";
            PreparedStatement preparedStatement = DCM.prepareStatement(Model_ID_QUERY);
            preparedStatement.setInt(1, Model_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int model_ID = resultSet.getInt("Model_ID");
                String Mærke = resultSet.getString("Mærket");
                int Energitype_ID = resultSet.getInt("Energitype_ID");
                String Model = resultSet.getString("Model");
                boolean isGearManuel = resultSet.getBoolean("isGearManuel");
                double CO2_Udslip = resultSet.getDouble("CO2_Udslip");
                double Stålpris = resultSet.getDouble("Stålpris");
                double KmPrX = resultSet.getDouble("KmPrX");

                String EnergiType_QUERY = "SELECT Energi FROM energitype WHERE Energitype_ID=?";
                PreparedStatement preparedStatement1 = DCM.prepareStatement(EnergiType_QUERY);
                preparedStatement1.setInt(1, Energitype_ID);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                if (resultSet1.next()) {
                    EnergiType type = EnergiType.getEnum(resultSet1.getInt(Energitype_ID));
                    BilModel bilModel = new BilModel(model_ID);
                    bilModel.setMærke(Mærke);
                    bilModel.setEnergitype(type);
                    bilModel.setModel(Model);
                    bilModel.setGearManuel(isGearManuel);
                    bilModel.setStålpris(Stålpris);
                    bilModel.setCO2_Udslip(CO2_Udslip);
                    bilModel.setKmPrX(KmPrX);
                    return bilModel;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Jakob
    public List<BilModel> ViewAlleBilModeller() {
        List<BilModel> alleBilModeller = new ArrayList<>();

        try {
            String QUERY = "SELECT * FROM bilmodel";
            PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Model_ID = resultSet.getInt("Model_ID");
                BilModel bilModel = ViewBilmodel(Model_ID);
                alleBilModeller.add(bilModel);
            }

        } catch (SQLException e) {
            System.out.println("Fejl, Kan ikke se alle bilmodeller");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return alleBilModeller;
    }


    public void updateBilModel(BilModel bilModel) {
        try {
            int Model_ID = bilModel.getModel_ID();
            EnergiType Energitype = bilModel.getEnergitype();
            int Energitype_ID = Energitype.getInt();
            String BilModel = bilModel.getMærke();
            boolean isGearManual = bilModel.isGearManuel();
            double CO2_Udslip = bilModel.getCO2_Udslip();
            double Stålpris = bilModel.getStålpris();
            double KmPrX = bilModel.getKmPrX();
            String QUERY = "UPDATE bilmodel SET Energitype_ID = ?, Model = ?, isGearManual = ?," +
                    " CO2_Udslip = ?, Stålpris = ?, KmPrX = ? WHERE Model_ID = ?";
            PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
            preparedStatement.setInt(1, Energitype_ID);
            preparedStatement.setString(2, BilModel);
            preparedStatement.setBoolean(3, isGearManual);
            preparedStatement.setDouble(4, CO2_Udslip);
            preparedStatement.setDouble(5, Stålpris);
            preparedStatement.setDouble(6, KmPrX);
            preparedStatement.setInt(7, Model_ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Kan ikke opdatere" + bilModel);
            throw new RuntimeException(e);
        }
    }

}
