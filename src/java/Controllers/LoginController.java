package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import src.java.SessionManager;
import src.java.Utils.PasswordUtil;
import src.java.Utils.UserRepository;
import src.java.model.LoginModel;
import src.java.model.Users;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session) {

        if (SessionManager.isLoggedIn(session))
            return "redirect:/home";

        model.addAttribute("log", new LoginModel());

        if (session.getAttribute("page") == "login")
            model.addAttribute("error", session.getAttribute("error"));
        else
            model.addAttribute("error", "");

        return "login";
    }

    @PostMapping("/login_user")
    public String login(Model model, @ModelAttribute("log") LoginModel login, HttpSession session) {

        model.addAttribute("log", login);

        System.out.println(login.getIdentifiant());
        System.out.println(login.getPassword());

        Users user = null;
        if(login.getIdentifiant().contains("@")) {
            if (userRepository.findByMail(login.getIdentifiant()).isPresent())
                user = userRepository.findByMail(login.getIdentifiant()).get();
        }
        else {
            if (userRepository.findByUsername(login.getIdentifiant()).isPresent())
                user = userRepository.findByUsername(login.getIdentifiant()).get();
        }

        if(user == null){
            session.setAttribute("error", "Il n'y a pas d'utilisateur avec cet identifiant ou cet email !");

            session.setAttribute("page", "login");
            model.addAttribute("error", session.getAttribute("error"));

            return "redirect:/login";
        }

        String hashPassword = user.getPassword();
        boolean loginSuccess = PasswordUtil.verifyPassword(login.getPassword(), hashPassword);

        if (loginSuccess) {
            System.out.println("Login successful");
            SessionManager.LogUser(session, user);
            session.setAttribute("error", "");
            return "redirect:/home";
        } else {
            session.setAttribute("error", "Error, retry please !");

            session.setAttribute("page", "login");
            model.addAttribute("error", session.getAttribute("error"));

            return "redirect:/login";
        }

    }

    @GetMapping("/logout")
    public String Logout(Model model, HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}
