package model;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class AddUser {
    Config config = new Config();

    public void addUser(String username) {
        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Add account here: ");
            boolean isAdding = true;
            while(isAdding) {
                System.out.print("Username: ");
                String newUser = input.nextLine().trim();

                //  Insert new value to the database
                String query = "INSERT INTO " +
                        "users (username) VALUES " +
                        "(?)";

                PreparedStatement preparedStatement = config.getConnection().prepareStatement(query);
                preparedStatement.setString(1, newUser);
                int rows = preparedStatement.executeUpdate();

                System.out.print("Do you want to add another account? ");
                String action = input.nextLine();
                if(!action.equalsIgnoreCase("Yes")) {
                    System.out.println("Row/s affected: " + rows);
                    isAdding = false;

                    // Closing resources
                    preparedStatement.close();
                    input.close();
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error found");
            e.printStackTrace();
        }

    }
}
