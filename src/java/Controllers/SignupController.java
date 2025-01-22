package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import src.java.Utils.PasswordUtil;
import src.java.Utils.UserRepository;
import src.java.model.HelloObject;
import src.java.model.Users;

import java.util.Dictionary;
import java.util.Hashtable;

@Controller
public class SignupController {

    /// Return to signup page
    //@GetMapping("/signup")
    //public String Home1() {return "signup";}

    // localhost:8080/signup
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String Signup(Model model, HttpSession session) {

        model.addAttribute("user", new Users());
        model.addAttribute("passwordValidation", "");

        System.out.println(session.getAttribute("page"));
        System.out.println(session.getAttribute("error"));

        if (session.getAttribute("page") == "signup")
            model.addAttribute("error", session.getAttribute("error"));
        else
            model.addAttribute("error", "");

        return "signup";
    }

    @PostMapping("/register_new_user")
    public String Post(Model model, @ModelAttribute("user") Users user, @ModelAttribute("passwordValidation") String pw, HttpSession session) {

        model.addAttribute("user", user);
        model.addAttribute("passwordValidation", pw);

        if (user.getPassword().isEmpty()) {
            session.setAttribute("error", "The password cannot be empty !");
            session.setAttribute("page", "signup");
            return "redirect:/signup";
        }
        else if(user.getUsername().isEmpty()) {
            session.setAttribute("error", "The username cannot be empty !");
            session.setAttribute("page", "signup");
            return "redirect:/signup";
        }
        else if (user.getMail().isEmpty()) {
            session.setAttribute("error", "The email cannot be empty !");
            session.setAttribute("page", "signup");
            return "redirect:/signup";
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            session.setAttribute("error", "Username is already used !");
            session.setAttribute("page", "signup");
            return "redirect:/signup"; // Renvoie une vue d'erreur si l'utilisateur existe déjà
        }
        if (userRepository.findByMail(user.getMail()).isPresent()) {
            session.setAttribute("error", "Email is already used !");
            session.setAttribute("page", "signup");
            return "redirect:/signup";
        }

        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));

        System.out.println("New User Registered :");
        System.out.println("UserName : " + user.getUsername());
        System.out.println("Pseudo : " + user.getPseudo());
        System.out.println("Password : " + user.getPassword());
        System.out.println("Mail : " + user.getMail());

        userRepository.save(user);

        session.setAttribute("error", "");

        return "redirect:/home";
    }

}
