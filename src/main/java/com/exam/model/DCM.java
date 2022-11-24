package com.exam.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DCM {

    private static String hostname;
    private static String username;
    private static String password;
    private static Connection conn = createConnection();

    private static Connection createConnection() {
        hostname = System.getenv("hostname");
        username = System.getenv("username");
        password = System.getenv("password");
        try {
            conn = DriverManager.getConnection(hostname, username, password);
        } catch (SQLException e) {
            System.out.println("TASK FAILED, THROWING EXCEPTION");
            throw new RuntimeException();
        }
        System.out.println("CREATING CONNECTION");
        return conn;
        }

    public static Connection getConn() {
        return conn;
    }
}
