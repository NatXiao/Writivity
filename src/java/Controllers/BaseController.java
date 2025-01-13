package src.java.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class BaseController {

    /// Return to Home page
    @GetMapping("/")
    public RedirectView Home(RedirectAttributes attributes, Model model) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");

        System.out.println("Reditection !");

        //model.addAttribute("greeting", new HomeTest());

        return new RedirectView("home.html");
    }

}
