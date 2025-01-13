package src.java.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import src.java.UserService;

import java.io.IOException;

@RestController
public class LoginController {

    @GetMapping("/login")
    public ModelAndView showLoginForm() throws IOException {
        // Charger le fichier HTML depuis src/resources/static/login.html
        Resource resource = new ClassPathResource("static/login.html");

        // Créer une vue avec le fichier HTML
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        modelAndView.addObject("resource", resource);

        return modelAndView;
    }

    @PostMapping("/login")
    public RedirectView login(String email, String password, RedirectAttributes attributes) {
        boolean loginSuccessful = UserService.verifyUserPassword(email, password);

        if (loginSuccessful) {
            attributes.addFlashAttribute("message", "Login successful!");
            return new RedirectView("/home");  // Redirection vers la page d'accueil
        } else {
            attributes.addFlashAttribute("error", "Invalid email or password.");
            return new RedirectView("/login");  // Retour à la page de login
        }
    }
}
