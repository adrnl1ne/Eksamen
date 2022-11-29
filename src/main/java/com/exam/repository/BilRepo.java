package com.exam.repository;

import com.exam.Utilities.DCM;
import com.exam.model.entities.biler.Bil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class BilRepo {
    private final Connection DCM = com.exam.Utilities.DCM.getConn();

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
            System.out.println("Can't create car" + bil.toString());
            e.printStackTrace();
        }
    }

    public ArrayList<String> ViewBil(String stelnummer) {
        ArrayList<String> bil = new ArrayList<>();
        String SN_QUERY = " SELECT Stelnummer FROM bil";
        String sn = "";
        try {
            Statement statement = DCM.createStatement();
            ResultSet resultSet = statement.executeQuery(SN_QUERY);

            while (resultSet.next()) {
                sn = resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String VIEW_QUERY = "SELECT * FROM bil WHERE Stelnummer = " + sn;
        try {
            Statement statement = DCM.createStatement();
            ResultSet resultSet = statement.executeQuery(VIEW_QUERY);

            while (resultSet.next()) {
                    bil.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bil;
    }





}


