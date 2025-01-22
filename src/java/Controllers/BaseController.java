package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import src.java.SessionManager;
import src.java.model.Text;
import src.java.model.Theme;
import src.java.model.Users;

import java.time.LocalDateTime;
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

        Theme t1 = new Theme();
        t1.setTheme_id(0);
        t1.setTheme_name("Aventure");
        t1.setWord_limit(2000);
        t1.setOpen_at(LocalDateTime.now());
        t1.setClose_at(LocalDateTime.of(2026, 10, 9, 12, 0, 0));
        t1.setConditions("Sans la lettre \"Y\"");

        Theme t2 = new Theme();
        t2.setTheme_id(1);
        t2.setTheme_name("Comic");
        t2.setWord_limit(300);
        t2.setOpen_at(LocalDateTime.now());
        t2.setClose_at(LocalDateTime.of(2025, 10, 9, 12, 0, 0));
        t2.setConditions("R.D.T.");

        Theme t3 = new Theme();
        t3.setTheme_id(2);
        t3.setTheme_name("Action");
        t3.setWord_limit(12345);
        t3.setOpen_at(LocalDateTime.now());
        t3.setClose_at(LocalDateTime.of(2028, 10, 9, 12, 0, 0));
        t3.setConditions("...");

        List<Theme> themes = new ArrayList<>();
        themes.add(t1);
        themes.add(t2);
        themes.add(t3);


        model.addAttribute("Theme", themes); // new ArrayList<Theme>()
        model.addAttribute("OldTheme", new ArrayList<Theme>()); // new ArrayList<Theme>()
        model.addAttribute("isAdmin", SessionManager.IsAdmin(session));
        return "home";
    }
    @GetMapping("/profile")
    public String Profile(Model model, Model model2, Model model3, HttpSession session) {

        if (!SessionManager.isLoggedIn(session)) return "redirect:/login";

        model.addAttribute("Users", ((Users) session.getAttribute("user")));
        model2.addAttribute("Themes", new ArrayList<Theme>());
        model3.addAttribute("Text", new ArrayList<Text>());
        return "profile";
    }
    @GetMapping("/createText")
    public String createText(){
        return "createText";
    }

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

    @GetMapping("/stats")
    public String Stats(Model model, HttpSession session) {

        if (!SessionManager.IsAdmin(session))
            return "/error403";

        return "/stats";
    }

}
