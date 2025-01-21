package src.java.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import src.java.model.LoginModel;
import src.java.model.Text;
import src.java.model.Theme;

import java.util.ArrayList;

@Controller
public class ChallengeController {

    @GetMapping("/challengeId")
    public String redirectToChallenge(Model model, Model model2) {
        model.addAttribute("Challenge", new Theme());
        model2.addAttribute("Text", new ArrayList<Text>());

        return "singleChallenge";
    }


    @GetMapping("/createChallenge")
    public String redirectToChallengeCreate(Model model) {

        model.addAttribute("challenge", new Theme());

        return "createChallenge";
    }

    @PostMapping("/register_new_challenge")
    public String registerNewChallenge(Model model, @ModelAttribute("challenge") Theme theme){

        model.addAttribute("challenge", theme);

        System.out.println("New Challenge : ");
        System.out.println("Nom : " + theme.getTheme_name());
        System.out.println("Condition : " + theme.getConditions());

        return "redirect:/home";
    }

}
