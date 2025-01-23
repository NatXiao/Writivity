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

import java.time.LocalDate;

@Controller
public class ChallengeController {

    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private TextRepository textRepository;

    @GetMapping("/challengeid/{id}")
    public String singleChallenge(@PathVariable("id") int id, Model model, HttpSession session) {

        /*if (id == 1)
            return "error403";

        if(id == 7)
            return "error";*/
        Challenge t1 = challengeRepository.findByThemeId(id).get();
        /*Challenge t4 = new Challenge();
        t4.setThemeId(0);
        t4.setThemeName("Aventure");
        t4.setWordLimit(2000);
        t4.setOpenAt(LocalDate.now());
        t4.setCloseAt(LocalDate.of(2026, 10, 9));
        t4.setConditions("Sans la lettre \"Y\"");*/

        model.addAttribute("Challenge", t1);


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
        System.out.println("Nom : " + challenge.getThemeName());
        System.out.println("Condition : " + challenge.getConditions());
        System.out.println("Limite de mots : " + challenge.getWordLimit());
        System.out.println("Ouverture : " + challenge.getOpenAt());
        System.out.println("Fermeture : " + challenge.getCloseAt());

        challengeRepository.save(challenge);

        return "redirect:/home";
    }
}
