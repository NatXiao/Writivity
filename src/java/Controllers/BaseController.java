package src.java.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BaseController {

    /// Return to Home page
    @GetMapping("/")
    public String Home1() {
        return "login";
    }
    @GetMapping("/home")
    public String Home2() {
        return "home";
    }

}
