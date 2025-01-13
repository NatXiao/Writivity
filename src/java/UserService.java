/*package src.java;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.*;

public class UserService {

    public static void main(String[] args) {
        String email = "example@mail.com";  // Email entered by user
        String enteredPassword = "userPassword";  // Password entered by user
        
        if (verifyUserPassword(email, enteredPassword)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    // Method to verify entered password against the stored hash
    public static boolean verifyUserPassword(String email, String enteredPassword) {
        String hashedPassword = getPasswordHashFromDatabase(email);
        
        if (storedHash == null) {
            return false;  // If no hash is found for the given email
        }
        
        // Verify the entered password against the stored hash
        return BCrypt.verifyer().verify(enteredPassword.toCharArray(), hashedPassword).verified;
    }

    // Method to fetch the stored password hash from the database using the user's email
    public static String getPasswordHashFromDatabase(String email) {
        String url = "A définir";
        String user = "your_username";
        String password = "your_password";
        
        String query = "SELECT password FROM \"user\" WHERE mail = ?";
        
        try (Connection connection = DriverManager.getConnection(url, user, password);
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
}*/
