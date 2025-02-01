package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import src.java.SessionManager;
import src.java.Utils.ChallengeRepository;
import src.java.Utils.TextRepository;
import src.java.model.Challenge;
import src.java.model.Text;
import src.java.model.Users;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private TextRepository textRepository;
    @Autowired
    private ChallengeRepository challengeRepository;

    @GetMapping("/profile")
    public String Profile(@PathVariable("id") int id,Model model, HttpSession session) {

        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        //List<Text> texts = textRepository.findByUserId(id);
        //Challenge Challenge = challengeRepository.findByChallengeId();

        model.addAttribute("Users", ((Users) session.getAttribute("user")));
        model.addAttribute("isAdmin", SessionManager.IsAdmin(session));
        model.addAttribute("Themes", new ArrayList<Challenge>());
        model.addAttribute("Text", new ArrayList<Text>());
        return "profile";
    }
}
