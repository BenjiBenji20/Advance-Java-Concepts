import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConfigConnection {

    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/jdbc";
        String user = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM users");

            while (res.next()) {
                System.out.println("ID: " + res.getInt("id")
                                    + " USERNAME: " + res.getString("username")
                );
            }

            res.close();
            statement.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}