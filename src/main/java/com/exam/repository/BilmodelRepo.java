package com.exam.repository;

import com.exam.model.entities.biler.Bil;
import com.exam.model.entities.biler.BilModel;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class BilmodelRepo {
    private final Connection DCM = com.exam.Utilities.DCM.getConn();

    public void getBilModel(BilModel bilModel, int Model_ID) {
        int id = bilModel.getModel_ID();
        String QUERY = "SELECT FROM bilmodel WHERE Model_ID=?";

        try {
            PreparedStatement preparedStatement = DCM.prepareStatement(QUERY);

            if (Model_ID == id) {
                preparedStatement.setInt(1, Model_ID);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(bilModel.toString());
    }




}
