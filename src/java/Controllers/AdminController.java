package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import src.java.SessionManager;
import src.java.Utils.*;
import src.java.model.*;

import java.util.List;


@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private TextRepository textRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/admin")
    public String Admin(Model model, HttpSession session) {

        if (!SessionManager.IsAdmin(session))
            return "/error403";

        List<Users> users = userRepository.findAll();
        List<Challenge> challenges = challengeRepository.findAll();
        List<Text> texts = textRepository.findAll();
        List<Report> reports = reportRepository.findAll();
        List<Feedback> feedbacks = feedbackRepository.findAll();



        model.addAttribute("users", users);
        model.addAttribute("challenges", challenges);
        model.addAttribute("texts", texts);
        model.addAttribute("reports", reports);
        model.addAttribute("feedbacks", feedbacks);

        return "admin";
    }

    @PostMapping("/admin/disqualification/{id}")
    public String disqualifieText(@PathVariable int id, Model model) {

        Text text = textRepository.findById(id).get();

        if (text.getDisqualified())
            text.setDisqualified(false);
        else
            text.setDisqualified(true);

        textRepository.UpdateDisqualification(id, text.getDisqualified());

        return "redirect:/admin";
    }


    @PostMapping("/admin/unreport/{id}")
    public String deleteReport(@PathVariable int id, Model model) {

        reportRepository.deleteReportsByReportId(id);

        return "redirect:/admin";
    }

    @PostMapping("/admin/set/{id}")
    public String modifieAdmin(@PathVariable int id, Model model) {

        Users user = userRepository.findByUserId(id);

        if (user.isAdmin())
            userRepository.unsetAdmin(id);
        else
            userRepository.setAdmin(id);

        return "redirect:/admin";
    }

}