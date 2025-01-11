package model;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class AddUser {
    Config config = new Config();

    public void addUser(Scanner input) {
        //  Insert new value to the database
        String query = "INSERT INTO " +
                "users (username) VALUES " +
                "(?)";

        try(PreparedStatement preparedStatement = config.getConnection().prepareStatement(query)) {
            System.out.println("Add account here: ");
            boolean isAdding = true;
            while(isAdding) {
                System.out.print("Username: ");
                String newUser = input.nextLine().trim();

                preparedStatement.setString(1, newUser);
                int rows = preparedStatement.executeUpdate();

                System.out.print("Do you want to add another account? ");
                String action = input.nextLine();
                if(!action.equalsIgnoreCase("Yes")) {
                    System.out.println("Row/s affected: " + rows);
                    isAdding = false;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error found");
            e.printStackTrace();
        }
    }
}
