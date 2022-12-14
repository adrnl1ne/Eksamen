package com.exam.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DCM {

    private static String hostname;
    private static String username;
    private static String password;
    private static Connection conn = createConnection();

    public static Connection createConnection() {
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
