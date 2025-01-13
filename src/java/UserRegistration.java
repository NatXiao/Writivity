package src.java;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRegistration {

    public static String hashPassword(String password) {
        final int cost = 10; // bcrypt cost
        return BCrypt.withDefaults().hashToString(cost, password.toCharArray());
    }

    public static void registerUser(String userName, String email, String password, boolean isAdmin) {
        String hashedPassword = hashPassword(password);
        String url = "jdbc:postgresql://localhost:5432/Writivity"; // Database URL
        String user = "postgres"; // Database username
        String passwordDb = "myverysecretpassword"; // Database password

        String insertQuery = "INSERT INTO users (user_name, mail, password, admin) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, passwordDb);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, hashedPassword);
            preparedStatement.setBoolean(4, isAdmin);

            preparedStatement.executeUpdate();
            System.out.println("User registered successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
        String userName = "Bob";
        String email = "Bob.BobbyBob@example.com";
        String password = "1234";
        boolean isAdmin = false;

        registerUser(userName, email, password, isAdmin);
    }
}