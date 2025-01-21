package src.java;

import jakarta.servlet.http.HttpSession;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * Session manager 1.0
 * SIEDEL Joshua
 * Copyright © 2025 SIEDEL System
 */

public class SessionManager {

    public static Dictionary<String, String> sessions = new Hashtable<String, String>();

    public static boolean isLoggedIn(HttpSession session) {
        if(session.getAttribute("connected") == null)
            return false;
        return true;
    }

    public static void LogUser(HttpSession session, String email) {

        session.setAttribute("connected", true);

        /// TUDU : Autres infos (sauvegardé dans un cookie)

    }

    public static boolean IsAdmin(HttpSession session) {
        if (session.getAttribute("admin") == null)
            return false;
        return true;
    }

}
