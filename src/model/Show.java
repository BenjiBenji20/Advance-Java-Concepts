package model;

import java.sql.ResultSet;
import java.sql.Statement;

public class Show {

    public User showUser() {
        Config config = new Config();
        User user = new User();
        String query = "SELECT * FROM users";

        try(Statement statement = config.getConnection().createStatement();
            ResultSet res = statement.executeQuery(query)) {

            if(res.next()) {
                user.setUsername(res.getString("username"));
            } else {
                System.out.println("No user found");
                return null;
            }

            return user;
        } catch (Exception e) {
            System.out.println("Error found");
            e.printStackTrace();
            return null;
        }

    }
}
