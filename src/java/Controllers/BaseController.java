package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import src.java.SessionManager;
import src.java.model.Challenge;
import src.java.Utils.ChallengeRepository;
import src.java.model.Challenge;
import src.java.model.Text;
import src.java.model.Challenge;
import src.java.model.Users;

import java.time.LocalDate;
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
        List<Challenge> currentChallenges = challengeRepository.findAll();
        currentChallenges.removeIf(c -> c.getCloseAt().isBefore(LocalDate.now()) || c.getCloseAt().isEqual(LocalDate.now()));
        // Récupérer les anciens challenges
        List<Challenge> oldChallenges = challengeRepository.findAll();
        oldChallenges.removeIf(c -> c.getCloseAt().isAfter(LocalDate.now()));

        System.out.println("All challenges: " + challengeRepository.findAll().size());
        System.out.println("Current Challenges : " + currentChallenges.size());

        model.addAttribute("Theme", currentChallenges); // Challenges en cours
        model.addAttribute("OldTheme", oldChallenges); // Challenges passés
        model.addAttribute("isAdmin", SessionManager.IsAdmin(session));
        return "home";
    }


}