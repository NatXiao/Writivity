package src.java.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import src.java.model.Text;
import src.java.model.Theme;
import src.java.model.Users;

import java.util.ArrayList;

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
    public String Home2(Model model) {
        model.addAttribute("Theme", new ArrayList<Theme>());
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
