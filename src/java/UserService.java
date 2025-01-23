package src.java;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.*;

public class UserService {

    public static void main(String[] args) {
        //String email = "example@mail.com";  // Email entered by user
        //String enteredPassword = "userPassword";  // Password entered by user


        String email = "Bob.BobbyBob@example.com";
        String enteredPassword = "1234";

        if (verifyUserPassword(email, enteredPassword)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    // Method to verify entered password against the stored hash
    public static boolean verifyUserPassword(String email, String enteredPassword) {
        String hashedPassword = getPasswordHashFromDatabase(email);

        if (hashedPassword == null) {
            return false;  // If no hash is found for the given email
        }

        // Verify the entered password against the stored hash
        return BCrypt.verifyer().verify(enteredPassword.toCharArray(), hashedPassword).verified;
    }

    // Method to fetch the stored password hash from the database using the user's email
    public static String getPasswordHashFromDatabase(String email) {
        String url = "jdbc:postgresql://localhost:5432/writivity"; // Database URL
        String user = "postgres"; // Database username
        String passwordDb = "myverysecretpassword"; // Database password

        String query = "SELECT password FROM users WHERE mail = ?";

        try (Connection connection = DriverManager.getConnection(url, user, passwordDb);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("password");  // Return the stored hash
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Return null if no user found with the given email
    }
}
