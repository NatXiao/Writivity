package src.java.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ChallengeController {

    @GetMapping("/gotochallenge")
    public String redirectToChallenge() {

        return "singleChallenge";
    }

}
