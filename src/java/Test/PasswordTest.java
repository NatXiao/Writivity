package src.java.Test;

import src.java.Utils.PasswordUtil;

public class PasswordTest {
    public static void main(String[] args) {
        String inputPassword = "1234";
        String storedPasswordHash = "$2a$10$.qE.nDy90JvHY9nahAZH9ujWtik0Yzl9ufBttj2x.LYvhK4TG42/i";

        boolean isMatch = PasswordUtil.verifyPassword(inputPassword, storedPasswordHash);
        System.out.println("Password valid: " + isMatch);
    }
}

