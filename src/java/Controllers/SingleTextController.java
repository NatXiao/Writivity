package src.java.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import src.java.model.Comment;
import src.java.model.Text;

import java.util.ArrayList;

@Controller
public class SingleTextController {

    @GetMapping("/textId")
    public String singleText(Model model1, Model model2){
        model1.addAttribute("Text", new Text());
        model2.addAttribute("Comment", new ArrayList<Comment>());
        return "singleText";
    }
}
