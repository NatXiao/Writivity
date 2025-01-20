package src.java.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.View;
import src.java.model.LoginModel;

import java.io.IOException;

@Controller       // <- @RestController
public class LoginController {

    public static String errorMessage = "";
    private final View error;

    public LoginController(View error) {
        this.error = error;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {

        model.addAttribute("log", new LoginModel());
        model.addAttribute("error", errorMessage);

        return "login";
    }

    @PostMapping("/login_user")
    public String login(Model model, @ModelAttribute("log") LoginModel login) {

        model.addAttribute("log", login);
        //model.addAttribute("error", errorMessage);

        boolean loginSuccessful = false; //UserService.verifyUserPassword(login.getMail(), login.getPassword());

        if (loginSuccessful) {



            return "home";

            //attributes.addFlashAttribute("message", "Login successful!");
            //return new RedirectView("/home");  // Redirection vers la page d'accueil

        } else {

            //System.out.println(errorMessage);

            errorMessage = "Error, retry please !";

            return "/login";

            //attributes.addFlashAttribute("error", "Invalid email or password.");
            //return new RedirectView("/login");  // Retour à la page de login
        }
    }
}
