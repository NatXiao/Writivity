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


@Controller       // <- @RestController
public class LoginController {

    public static String errorMessage = "";

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model, HttpSession session) {

        if (SessionManager.isLoggedIn(session))
            return "redirect:/home";

        model.addAttribute("log", new LoginModel());
        model.addAttribute("error", errorMessage);

        return "login";
    }

    @PostMapping("/login_user")
    public String login(Model model, @ModelAttribute("log") LoginModel login, HttpSession session) {

        model.addAttribute("log", login);
        model.addAttribute("error", errorMessage);

        //boolean loginSuccessful = UserService.verifyUserPassword(login.getMail(), login.getPassword());
        boolean loginSuccess = PasswordUtil.verifyPassword(login.getPassword(), userRepository.findByMail(login.getMail()).get().getPassword());
        System.out.println(userRepository.findByMail(login.getMail()).get().getPassword());

        if (loginSuccess) {
            SessionManager.LogUser(session, login.getMail());
            return "redirect:/home";
        } else {
            errorMessage = "Error, retry please !";
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String Logout(Model model, HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }

}
