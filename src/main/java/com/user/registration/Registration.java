package com.user.registration;

import com.connection.config.ConnectionConfig;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/user-registration")
public class Registration extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/html");
        // create insert query
        String query = "INSERT INTO " +
                "users (complete_name, username, password)" +
                "VALUES (?, ?, ?)";

        try (
                // establish connection
                Connection conn = ConnectionConfig.getConnection();
                // set prepared statement
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                PrintWriter out = res.getWriter()
            ){
            // collects data from request body
            final String completeName = req.getParameter("completeName").trim();
            final String username = req.getParameter("username").trim();
            final String plainPassword = req.getParameter("password").trim();

            // input validation
            if(completeName.isEmpty() || username.isEmpty() || plainPassword.isEmpty()) {
                out.println("<h3>All fields are required. Please try again.</h3>");
                return;
            }

            // hashed the password
            final String hashedPassword = hashPassword(plainPassword);

            // pass data using prepared statement
            preparedStatement.setString(1, completeName);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, hashedPassword);

            // save to the db
            int result = preparedStatement.executeUpdate();

            // send response confirmation to the client
            String message = result > 0 ?
                    "<h3>Registration successful!</h3>" :
                    "<h3>Registration failed. Please try again.</h3>";

            out.println(message);
        }
        catch (Exception e) {
            System.out.println("Error found" + e.getMessage());
            try {
                res.getWriter().println("<h3>An error occured. Please try again</h3>");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // hash password for security
    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
//    // decrypt password for comparison
//    private boolean compareInputPassword(String plainPassword, String hashedPassword) {
//        return BCrypt.checkpw(plainPassword, hashedPassword);
//    }
}
