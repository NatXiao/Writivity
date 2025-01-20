package src.java.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import src.java.Utils.PasswordUtil;
import src.java.Utils.UserRepository;
import src.java.model.HelloObject;
import src.java.model.Users;

@Controller
public class SignupController {

    /// Return to signup page
    //@GetMapping("/signup")
    //public String Home1() {return "signup";}

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String HelloWorld(Model model) {
        //model.addAttribute("message", "Hello World!");

        model.addAttribute("user", new Users());

        return "signup";
    }

    //public static Dictionary<String, Users> userDictionary = new Hashtable<String, Users>();

    @PostMapping("/register_new_user")
    public String Post(Model model, @ModelAttribute("user") Users user) {

        model.addAttribute("user", user);

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "signup"; // Renvoie une vue d'erreur si l'utilisateur existe déjà
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return "error"; // Redirige vers une page d'erreur si nécessaire
        }

        //userDictionary.put(user.getUser_name(), user);
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        //userDictionary.put(user.getUsername(), user);

        System.out.println("New User Registered :");
        System.out.println("Name : " + user.getUser_name());
        System.out.println("Username : " + user.getUsername());
        System.out.println("Password : " + user.getPassword());
        System.out.println("Mail : " + user.getMail());

        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));

        userRepository.save(user);

        return "home";
    }

}