package src.java.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalTime;

@RestController
public class DemoController {


    @GetMapping("/print")
    public void print() {
        System.out.println(LocalTime.now().toString());
    }

    @GetMapping("/error")
    public String sayError() {
        return String.format("ERROR 404 !");
    }

    @GetMapping("/redir")
    public RedirectView redirectWithUsingRedirectView(
            RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");

        System.out.println("Reditection !");

        return new RedirectView("/index.html");
    }

    // href="src/templates/challenge.html"

    @GetMapping("/backtohome")
    public RedirectView redirectToHome(
            RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");

        System.out.println("Reditection !");

        return new RedirectView("/home.html");
    }

}
