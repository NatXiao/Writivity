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

}
