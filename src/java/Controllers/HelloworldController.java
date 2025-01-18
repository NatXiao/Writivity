package src.java.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import src.java.model.HelloObject;

@Controller
public class HelloworldController {

    @GetMapping("/hello-world")
    public String HelloWorld(Model model) {
        model.addAttribute("message", "Hello World!");

        model.addAttribute("postvar", new HelloObject());

        return "hello-world";
    }


    @PostMapping("/post")
    public String Post(Model model, @ModelAttribute("postvar") HelloObject postvar) {

        model.addAttribute("postvar", postvar);

        System.out.println(postvar.getStr());

        return "hello-world";
    }


}
