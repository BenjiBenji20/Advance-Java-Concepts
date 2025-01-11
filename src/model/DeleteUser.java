package model;

import java.sql.PreparedStatement;

public class DeleteUser {

    public void deleteUser(String username) {
        Config config = new Config();
        String query = "DELETE FROM users WHERE username = ?";

        try(PreparedStatement preparedStatement = config.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, username);
            int rows = preparedStatement.executeUpdate();
            System.out.println("Deleted rows: " + rows);
        }
        catch (Exception e) {
            System.out.println("Error found");
            e.printStackTrace();
        }
    }
}
