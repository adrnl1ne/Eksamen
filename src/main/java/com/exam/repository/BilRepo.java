package com.exam.repository;


import com.exam.model.entities.biler.Bil;
import com.exam.model.entities.biler.BilModel;
import com.exam.model.entities.biler.BilTilstand;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class BilRepo {
    //Jakob
    private final Connection DCM = com.exam.utilities.DCM.getConn();
    //Jakob
    public void DeleteBil(Bil bil) {
        String stelnummer = bil.getStelnummer();
        String Delete_Query = "DELETE FROM bil WHERE Stelnummer=?";
        try {
            PreparedStatement preparedStatement = DCM.prepareStatement(Delete_Query);
            preparedStatement.setString(1, stelnummer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Car has been deleted, where stelnummer is: " + stelnummer);
    }

    //Jakob
    public void CreateBil(Bil bil) {

        String QUERY = "INSERT INTO bil (Stelnummer, Model_ID, Km_Kørt, Tilstands_ID) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
            preparedStatement.setString(1, bil.getStelnummer());
            preparedStatement.setInt(2, bil.getModel().getModel_ID());
            preparedStatement.setDouble(3, bil.getKm_kørte());
            preparedStatement.setInt(4, bil.getTilstand().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can't create car" + bil);
            e.printStackTrace();
        }
    }

    //Jakob
    public Bil ViewBil(String Stelnummer) {
        try {
            String Model_QUERY = "SELECT * FROM Bil WHERE Stelnummer=?";
            PreparedStatement preparedStatement = DCM.prepareStatement(Model_QUERY);
            preparedStatement.setString(1, Stelnummer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String stelnummer = resultSet.getString("Stelnummer");
                int Tilstands_ID = resultSet.getInt("Tilstands_ID");
                int Model_ID = resultSet.getInt("Model_ID");
                double KmKørt = resultSet.getDouble("Km_Kørt");

                String Tilstand_QUERY = "SELECT Biltilstand FROM biltilstand WHERE TilStands_ID = ?";
                PreparedStatement preparedStatement1 = DCM.prepareStatement(Tilstand_QUERY);
                preparedStatement1.setInt(1, Tilstands_ID);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                if (resultSet1.next()) {
                    BilTilstand tilstand = BilTilstand.getEnum(resultSet1.getInt(Tilstands_ID));
                    Bil bil = new Bil(stelnummer);
                    bil.setTilstand(tilstand);
                    bil.setModel_ID(Model_ID);
                    bil.setKm_kørte(KmKørt);
                    BilModel bilModel = new BilmodelRepo().ViewBilmodel(Model_ID);
                    bil.setModel(bilModel);
                    return bil;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Jakob
    public List<Bil> ViewAlleBiler() {
        List<Bil> alleBiler = new ArrayList<>();

        try {
            String QUERY = "SELECT * FROM bil";
            PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String stelnummer = resultSet.getString("Stelnummer");
                Bil bil = ViewBil(stelnummer);
                alleBiler.add(bil);
            }
        } catch (SQLException e) {
            System.out.println("Fejl, Kan ikke se alle biler");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return alleBiler;
    }

    public void updateBilModel(Bil bil) {
        try {
            String Stelnummer = bil.getStelnummer();
            BilTilstand tilstand = bil.getTilstand();
            int Tilstands_ID = tilstand.getInt();
            int Model_ID = bil.getModel_ID();
            double Km_Kørt = bil.getKm_kørte();
            String QUERY = "UPDATE bil SET Tilstands_ID =?, Model_ID =?, Km_Kørt =? WHERE Stelnummer=?";
            PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
            preparedStatement.setInt(1, Tilstands_ID);
            preparedStatement.setInt(2, Model_ID);
            preparedStatement.setDouble(3, Km_Kørt);
            preparedStatement.setString(4, Stelnummer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Kan ikke opdatere " + bil);
            throw new RuntimeException(e);
        }
    }

}


