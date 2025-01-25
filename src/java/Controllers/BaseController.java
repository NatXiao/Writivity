package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import src.java.SessionManager;
import src.java.model.Challenge;
import src.java.model.Text;
import src.java.model.Theme;
import src.java.model.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import src.java.Utils.ChallengeRepository;

@Controller
public class BaseController {

    @Autowired
    private ChallengeRepository challengeRepository;

    /// Return to Home page
    @GetMapping("/")
    public String Home1() {
        return "redirect:/login";
    }

    @GetMapping("/error")
    public String Error() { return "error"; }

    @GetMapping("/home")
    public String Home2(Model model, HttpSession session) {

        if (!SessionManager.isLoggedIn(session))
            return "redirect:/login";

        // Récupérer la liste des challenges en cours depuis la DB
        List<Challenge> currentChallenges = challengeRepository.findCurrentChallenges(LocalDateTime.now());

        // Récupérer les anciens challenges
        List<Challenge> oldChallenges = challengeRepository.findOldChallenges(LocalDateTime.now());

        model.addAttribute("Theme", currentChallenges); // Challenges en cours
        model.addAttribute("OldTheme", oldChallenges); // Challenges passés
        model.addAttribute("isAdmin", SessionManager.IsAdmin(session));
        return "home";
    }
    @GetMapping("/profile")
    public String Profile(Model model, Model model2, Model model3, HttpSession session) {

        if (!SessionManager.isLoggedIn(session)) return "redirect:/login";

        model.addAttribute("Users", ((Users) session.getAttribute("user")));
        model2.addAttribute("Themes", new ArrayList<Theme>());
        model3.addAttribute("Text", new ArrayList<Text>());
        return "profile";
    }
    @GetMapping("/createText")
    public String createText(){
        return "createText";
    }



    @GetMapping("/stats")
    public String Stats(Model model, HttpSession session) {

        if (!SessionManager.IsAdmin(session))
            return "/error403";

        return "/stats";
    }

}