package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import src.java.model.Theme;

import java.time.LocalDateTime;

@Controller
public class ChallengeController {

    @GetMapping("/challengeid/{id}")
    public String singleChallenge(@PathVariable("id") int id, Model model, HttpSession session) {

        /*if (id == 1)
            return "error403";

        if(id == 7)
            return "error";*/

        Theme t4 = new Theme();
        t4.setTheme_id(0);
        t4.setTheme_name("Aventure");
        t4.setWord_limit(2000);
        t4.setOpen_at(LocalDateTime.now());
        t4.setClose_at(LocalDateTime.of(2026, 10, 9, 12, 0, 0));
        t4.setConditions("Sans la lettre \"Y\"");

        model.addAttribute("Challenge", t4);


        return "singleChallenge";
    }


    @GetMapping("/createChallenge")
    public String redirectToChallengeCreate(Model model) {

        model.addAttribute("challenge", new Theme());

        return "createChallenge";
    }

    @PostMapping("/register_new_challenge")
    public String registerNewChallenge(Model model, @ModelAttribute("challenge") Theme theme) {

        model.addAttribute("challenge", theme);

        System.out.println("New Challenge : ");
        System.out.println("Nom : " + theme.getTheme_name());
        System.out.println("Condition : " + theme.getConditions());

        return "redirect:/home";
    }

}
