package model;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Show {

    public List<User> showUser() {
        Config config = new Config();
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try(Statement statement = config.getConnection().createStatement();
            ResultSet res = statement.executeQuery(query);) {
            while(res.next()) {
                User user = new User();
                user.setUsername(res.getString("username"));
                users.add(user);
            }

        } catch (Exception e) {
            System.out.println("Error found");
            e.printStackTrace();
        }

        return users;
    }
}
