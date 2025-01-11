package model;

import java.sql.*;

public class Config {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
    private static final String user = "root";
    private static final String password = "";

    static {
        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Configured");
//            String query = "SELECT * FROM users";
//            ResultSet res = statement.executeQuery(query);
//
//            //  Display users
//            while (res.next()) {
//                System.out.println("ID: " + res.getInt("id")
//                        + " USERNAME: " + res.getString("username")
//                );
//            }
        }
        catch (Exception e) {
            System.out.println("Error found");
            e.printStackTrace();
        }
    }
}
