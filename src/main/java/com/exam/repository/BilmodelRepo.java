package com.exam.repository;

import com.exam.model.entities.biler.BilModel;
import com.exam.model.entities.biler.EnergiType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BilmodelRepo {
    private final Connection DCM = com.exam.Utilities.DCM.getConn();


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
                    bilModel.setEnergiType(type);
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


}
