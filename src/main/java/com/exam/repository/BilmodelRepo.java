package com.exam.repository;

import com.exam.model.entities.biler.Bil;
import com.exam.model.entities.biler.BilModel;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BilmodelRepo {
    private final Connection DCM = com.exam.Utilities.DCM.getConn();



}
