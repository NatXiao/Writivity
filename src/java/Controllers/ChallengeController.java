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
import src.java.model.Theme;
import java.util.List;

import java.time.LocalDateTime;

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
        Challenge challenge = challengeRepository.findById(id).orElse(null);
        if (challenge == null) {
            return "error"; // Redirige vers une page d'erreur si le challenge n'existe pas
        }

        // Récupérer les textes associés à ce challenge
        List<Text> texts = textRepository.findTextsByChallengeId(id);

        // Ajout du print pour vérifier si la liste de textes est vide ou non
        System.out.println("Texte récupéré pour le challenge " + id + ": " + texts);

        // Ajouter le challenge et les textes au modèle
        model.addAttribute("Challenge", challenge);
        model.addAttribute("Text", texts);

        return "singleChallenge";
    }

}