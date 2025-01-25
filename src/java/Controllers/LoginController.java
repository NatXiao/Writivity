package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import src.java.SessionManager;
import src.java.Utils.PasswordUtil;
import src.java.Utils.UserRepository;
import src.java.model.LoginModel;

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


        //System.out.println(login.getPassword());
        String hashPassword = userRepository.findByMail(login.getMail()).get().getPassword();
        //boolean loginSuccessful = UserService.verifyUserPassword(login.getMail(), login.getPassword());
        boolean loginSuccess = PasswordUtil.verifyPassword(login.getPassword(),
                hashPassword);
        System.out.println("Mail: " + login.getMail());
        System.out.println("Password: " + login.getPassword());
        System.out.println("Hashed Password: " + hashPassword);

        if (loginSuccess) {
            System.out.println("Login successful");
            SessionManager.LogUser(session, userRepository.findByMail(login.getMail()).get());
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
