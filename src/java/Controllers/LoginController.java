package src.java.Controllers;

import org.hibernate.cfg.Compatibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;
import src.java.UserService;
import src.java.Utils.PasswordUtil;
import src.java.Utils.UserRepository;
import src.java.model.LoginModel;

import java.io.IOException;

@Controller       // <- @RestController
public class LoginController {

    public static String errorMessage = "";

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {

        model.addAttribute("log", new LoginModel());
        model.addAttribute("error", errorMessage);

        return "login";
    }

    @PostMapping("/login_user")
    public String login(Model model, @ModelAttribute("log") LoginModel login) {

        model.addAttribute("log", login);
        model.addAttribute("error", errorMessage);

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
            return "redirect:/home";
        } else {
            errorMessage = "Error, retry please !";
            return "redirect:/login";
        }
    }
}
