package src.java;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import src.java.Utils.UserRepository;
import src.java.model.Users;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Optional;

/**
 *
 * Session manager 1.0
 * SIEDEL Joshua
 * Copyright © 2025 SIEDEL System
 */

public class SessionManager {

    public static boolean isLoggedIn(HttpSession session) {
        if(session.getAttribute("user") == null)
            return false;
        return true;
    }

    public static void LogUser(HttpSession session, Users user) {
        session.setAttribute("user", user);
    }

    public static boolean IsAdmin(HttpSession session) {
        if (session.getAttribute("user") == null || !((Users)session.getAttribute("user")).isAdmin())
            return false;
        return true;
    }

}
