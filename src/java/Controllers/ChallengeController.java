package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import src.java.Utils.ChallengeRepository;
import src.java.Utils.TextRepository;
import src.java.model.Challenge;
import src.java.model.Text;
import java.util.List;

import java.time.LocalDate;

@Controller
public class ChallengeController {


    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private TextRepository textRepository;

    @GetMapping("/challenge/{id}")
    public String singleChallenge(@PathVariable("id") int id, Model model, HttpSession session) {

        // Vérifie si l'utilisateur est connecté
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        // Récupérer le challenge via son ID
        Challenge challenge = challengeRepository.findById((long)id).orElse(null);
        if (challenge == null) {
            return "error"; // Redirige vers une page d'erreur si le challenge n'existe pas
        }

        // Récupérer les textes associés à ce challenge
        List<Text> texts = textRepository.findTextsByChallengeId(id);

        // Ajouter le challenge et les textes au modèle
        model.addAttribute("Challenge", challenge);
        model.addAttribute("Text", texts);

        return "singleChallenge";
    }


    @GetMapping("/createChallenge")
    public String redirectToChallengeCreate(Model model) {

        model.addAttribute("challenge", new Challenge());

        return "createChallenge";
    }

    @PostMapping("/register_new_challenge")
    public String registerNewChallenge(Model model, @ModelAttribute("challenge") Challenge challenge) {

        model.addAttribute("challenge", challenge);
        challenge.setOpenAt(LocalDate.now());
        challenge.setCloseAt(LocalDate.now().plusWeeks(3));

        System.out.println("New Challenge : ");
        System.out.println("Nom : " + challenge.getChallengeName());
        System.out.println("Condition : " + challenge.getConditions());
        System.out.println("Limite de mots : " + challenge.getWordLimit());
        System.out.println("Ouverture : " + challenge.getOpenAt());
        System.out.println("Fermeture : " + challenge.getCloseAt());

        challengeRepository.save(challenge);

        return "redirect:/home";
    }
}
