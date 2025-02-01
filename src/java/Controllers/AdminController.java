package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import src.java.SessionManager;
import src.java.Utils.ChallengeRepository;
import src.java.Utils.TextRepository;
import src.java.Utils.UserRepository;
import src.java.model.Challenge;
import src.java.model.Text;
import src.java.model.Users;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private TextRepository textRepository;


    @GetMapping("/admin")
    public String Admin(Model model, HttpSession session) {

        if (!SessionManager.IsAdmin(session))
            return "/error403";

        List<Users> users = userRepository.findAll();
        List<Challenge> challenges = challengeRepository.findAll();
        List<Text> texts = textRepository.findAll();
        Optional<Text> textsreported = textRepository.findByReportedTrue();

        model.addAttribute("users", users);
        model.addAttribute("challenges", challenges);
        model.addAttribute("texts", texts);

        return "admin";
    }

}
