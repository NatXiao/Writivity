package src.java.Test;

import src.java.Utils.PasswordUtil;

public class PasswordTest {
    public static void main(String[] args) {
        String inputPassword = "a";
        String storedPasswordHash = "$2a$10$pJ.XdHUWQDeAqy7VlLE5CeAdUQ5ZoZdIk7uZEVMfwYjda5aipXctm";

        boolean isMatch = PasswordUtil.verifyPassword(inputPassword, storedPasswordHash);
        System.out.println("Password valid: " + isMatch);
    }
}

