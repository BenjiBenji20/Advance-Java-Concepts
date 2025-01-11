import java.sql.*;
import java.util.Scanner;

public class InsertValue {

    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        String user = "root";
        String password = "";
        Scanner input = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement statement = conn.createStatement();

            System.out.println("Add account here");
            boolean isAdding = true;
            while(isAdding) {
                System.out.print("Username: ");
                String username = input.nextLine();

                //  Insert new value to the database
                String value = "INSERT INTO " +
                                "users (username) VALUES " +
                                "(?)";

                PreparedStatement prepStatement = conn.prepareStatement(value);
                prepStatement.setString(1, username);
                int rows = prepStatement.executeUpdate();

                System.out.print("Do you want to add another account? ");
                String action = input.nextLine();
                if(!action.equalsIgnoreCase("Yes")) {
                    System.out.println("Row/s affected: " + rows);
                    isAdding = false;
                    input.close(); // closing the input scanner
                }
            }

            //  query to fetch data
            ResultSet res = statement.executeQuery("SELECT * FROM users");

            while (res.next()) {
                System.out.println("ID: " + res.getInt("id")
                        + " USERNAME: " + res.getString("username")
                );
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
