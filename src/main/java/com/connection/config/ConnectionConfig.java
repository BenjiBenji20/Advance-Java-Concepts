package com.connection.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {
    private Connection connection;

    public static Connection getConnection() throws SQLException {
        final String url = "jdbc:mysql://127.0.0.1:3306/servlet_registration_web_app";
        final String user = "root";
        final String password = "";

        System.out.println("Connection established!");
        return DriverManager.getConnection(url, user, password);
    }
}
