package com.exam.repository;

import com.exam.Utilities.DCM;
import com.exam.model.entities.biler.Bil;
import com.exam.model.entities.biler.BilModel;
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

    public void getBilByStelnummer(Bil bil, String stel) {
        String stelnummer = bil.getStelnummer();
        String QUERY = "SELECT FROM bilmodel WHERE Stelnummer=?";

        try {
            PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);

            if (stel == stelnummer) {
                preparedStatement.setString(1, stel);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(bil.toString());
    }





}


