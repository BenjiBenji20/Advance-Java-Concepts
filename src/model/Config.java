package model;

import java.sql.*;

public class Config {


    private Connection conn;
    public Connection getConnection() {
        return conn;
    }

    public Config() {
        try {
            final String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
            final String user = "root";
            final String password = "";
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e) {
            System.out.println("Error found");
            e.printStackTrace();
        }
    }
}
