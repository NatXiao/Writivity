package src.java.Utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {

    public static String hashPassword(String password) {
        final int cost = 10; // bcrypt cost
        return BCrypt.withDefaults().hashToString(cost, password.toCharArray());
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }
}