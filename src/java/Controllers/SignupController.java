package src.java.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import src.java.model.HelloObject;
import src.java.model.Users;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@Controller
public class SignupController {

    /// Return to signup page
    //@GetMapping("/signup")
    //public String Home1() {return "signup";}

    // localhost:8080/signup

    @GetMapping("/signup")
    public String HelloWorld(Model model) {
        //model.addAttribute("message", "Hello World!");

        model.addAttribute("user", new Users());

        return "signup";
    }

    public static Dictionary<String, Users> userDictionary = new Hashtable<String, Users>();

    @PostMapping("/register_new_user")
    public String Post(Model model, @ModelAttribute("user") Users user) {

        model.addAttribute("user", user);

        if (userDictionary.get(user.getUsername()) != null) {
            return "error";
        }

        userDictionary.put(user.getUsername(), user);

        System.out.println("New User Registered :");
        System.out.println("UserName : " + user.getUsername());
        System.out.println("Password : " + user.getPassword());
        System.out.println("Mail : " + user.getMail());

        return "home";
    }

}
