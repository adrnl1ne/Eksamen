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
    private static CallableStatement cstmt = null;
    private static ResultSet rs = null;
    private final Connection DCM = com.exam.utilities.DCM.getConn();

    //Jakob
    public void deleteBil(Bil bil) {
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
    public void createBil(Bil bil) {

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
    public Bil viewBil(String Stelnummer) {
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
                    BilModel bilModel = new BilmodelRepo().viewBilmodel(Model_ID);
                    bil.setModel(bilModel);
                    return bil; // Der mangler stadig at sætte alle de skadesrapporter, som denne bil har
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ikke muligt at se bil med stelnummeret: " + Stelnummer);
            throw new RuntimeException(e);
        }
        return null;
    }

    //Jakob
    public List<Bil> viewAlleBiler() {
        List<Bil> alleBiler = new ArrayList<>();

        try {
            String QUERY = "SELECT * FROM bil";
            PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String stelnummer = resultSet.getString("Stelnummer");
                alleBiler.add(viewBil(stelnummer));
            }
        } catch (SQLException e) {
            System.err.println("Fejl, Kan ikke se alle biler");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return alleBiler;
    }

    public List<Bil> viewUdlejetBiler() {
        List<Bil> udlejedeBiler = new ArrayList<>();
        try {
            cstmt = DCM.prepareCall("{call viewudlejet(2)}");
            cstmt.execute();
            rs = cstmt.getResultSet();
            String stelnummer = rs.getString("Stelnummer");
            udlejedeBiler.add(viewBil(stelnummer));

        } catch (SQLException e) {
            System.err.println("Fejl, kan ikke hente biler");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return udlejedeBiler;

    }


    public void updateBil(Bil bil) {
        try {
            String Stelnummer = bil.getStelnummer();
            BilTilstand tilstand = bil.getTilstand();
            int Tilstands_ID = tilstand.getInt();

            double Km_Kørt = bil.getKm_kørte();
            String QUERY = "UPDATE bil SET Tilstands_ID =?, Km_Kørt =? WHERE Stelnummer=?";
            PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);
            preparedStatement.setInt(1, Tilstands_ID);
            preparedStatement.setDouble(2, Km_Kørt);
            preparedStatement.setString(3, Stelnummer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Kan ikke opdatere " + bil);
            throw new RuntimeException(e);
        }
    }


}


