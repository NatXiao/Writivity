package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        System.out.println(session.getId());

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

        List<Theme> themes = new ArrayList<>();
        themes.add(t1);
        themes.add(t2);


        model.addAttribute("Theme", themes); // new ArrayList<Theme>()
        model.addAttribute("OldTheme", new ArrayList<Theme>()); // new ArrayList<Theme>()
        return "home";
    }
    @GetMapping("/profile")
    public String Profile(Model model, Model model2, Model model3) {
        model.addAttribute("Users", new Users());
        model2.addAttribute("Themes", new ArrayList<Theme>());
        model3.addAttribute("Text", new ArrayList<Text>());
        return "profile";
    }
    @GetMapping("/createText")
    public String createText(){
        return "createText";
    }
    @GetMapping("/Challengeid")
    public String singleChallenge(){
        return "singleChallenge";
    }
}
