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
        private String hashedPassword = hashPassword(password);

        private String url = "A définir"; // Replace with your database URL
        private String user = "your_username"; // Replace with your DB username
        private String passwordDb = "your_password"; // Replace with your DB password

        private String insertQuery = "INSERT INTO \"user\" (user_name, mail, password) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, passwordDb);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, hashedPassword);

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
