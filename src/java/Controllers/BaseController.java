package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import src.java.SessionManager;
import src.java.model.Text;
import src.java.model.Challenge;
import src.java.model.Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BaseController {

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

        /*Challenge t1 = new Challenge();
        t1.setThemeId(0);
        t1.setTheme_name("Aventure");
        t1.setWord_limit(2000);
        t1.setOpenAt(LocalDate.now());
        t1.setClose_at(LocalDate.of(2026, 10, 9));
        t1.setConditions("Sans la lettre \"Y\"");

        Challenge t2 = new Challenge();
        t2.setThemeId(1);
        t2.setTheme_name("Comic");
        t2.setWord_limit(300);
        t2.setOpenAt(LocalDate.now());
        t2.setClose_at(LocalDate.of(2025, 10, 9));
        t2.setConditions("R.D.T.");

        Challenge t3 = new Challenge();
        t3.setThemeId(2);
        t3.setTheme_name("Action");
        t3.setWord_limit(12345);
        t3.setOpenAt(LocalDate.now());
        t3.setClose_at(LocalDate.of(2028, 10, 9));
        t3.setConditions("...");*/

        List<Challenge> challenges = new ArrayList<>();
        /*challenges.add(t1);
        challenges.add(t2);
        challenges.add(t3);*/

        model.addAttribute("Theme", challenges); // new ArrayList<Theme>()
        model.addAttribute("OldTheme", new ArrayList<Challenge>()); // new ArrayList<Theme>()
        model.addAttribute("isAdmin", SessionManager.IsAdmin(session));
        return "home";
    }
    @GetMapping("/profile")
    public String Profile(Model model, Model model2, Model model3, HttpSession session) {

        if (!SessionManager.isLoggedIn(session)) return "redirect:/login";

        model.addAttribute("Users", ((Users) session.getAttribute("user")));
        model2.addAttribute("Themes", new ArrayList<Challenge>());
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
