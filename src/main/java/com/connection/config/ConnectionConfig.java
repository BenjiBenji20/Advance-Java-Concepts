package com.connection.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfig {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public ConnectionConfig() {
        try {
            final String url = "jdbc:mysql://127.0.0.1:3306/servlet_registration_web_app";
            final String user = "root";
            final String password = "";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established!");
        }
        catch (Exception e) {
            System.out.println("Error found");
            e.printStackTrace();
        }
    }
}
