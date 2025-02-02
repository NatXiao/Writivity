package src.java.Controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.Mergeable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import src.java.SessionManager;
import src.java.Utils.*;
import src.java.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import src.java.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import src.java.model.Text;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class TextController {

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/singleText/{challengeId}/{textId}")
    public String getTextDetail(@PathVariable("challengeId") Integer challengeId,
                                @PathVariable("textId") Integer textId, Model model, HttpSession session) {
        // Récupérer le texte par son ID et l'ID du challenge
        Text text = textRepository.findTextByChallengeIdAndTextId(challengeId, textId).orElse(null);

        if (text != null) {
            model.addAttribute("text", text);  // Ajouter le texte au modèle
            model.addAttribute("challengeId", challengeId);  // Ajouter l'ID du challenge au modèle
        } else {
            // Si le texte n'est pas trouvé, rediriger vers la page d'accueil ou afficher une erreur
            return "redirect:/home";  // Ou une autre page d'erreur
        }

        List<Rate> rates = rateRepository.findByTextId(textId);
        List<Comment> comments = text.getComments();

        model.addAttribute("comments", comments);

        model.addAttribute("commentCreator", new Comment());
        model.addAttribute("report", new Report());

        float mean = 0;
        if (!rates.isEmpty()) {
            for (Rate rate : rates) {
                mean += rate.getRate();
            }
            mean /= rates.size();
        }

        int roundedMean = Math.round(mean);

        model.addAttribute("roundedMean", roundedMean);
        model.addAttribute("mean", mean);

        List<Rate> userRate = rateRepository.findByTextIdAndUserId(textId, ((Users)session.getAttribute("user")).getUserId());

        if (userRate.isEmpty())
            model.addAttribute("userRate", 0);
        else
            model.addAttribute("userRate", userRate.get(0).getRate());

        model.addAttribute("userId", ((Users)session.getAttribute("user")).getUserId());

        return "singleText";  // Nom de la page HTML à afficher
    }

    @GetMapping("/rate/{challengeId}/{textId}/{rate}")
    public String rateText(@PathVariable("challengeId") Integer challengeId,
                                @PathVariable("textId") Integer textId, @PathVariable("rate") Integer rate, Model model, HttpSession session) {

        Rate r = new Rate();
        r.setTextId(textId);
        r.setUserId(((Users)session.getAttribute("user")).getUserId());
        r.setRate(rate);

        System.out.println(r.getUserId() + " / " + r.getTextId() + " / " + r.getRate());

        List<Rate> userRate = rateRepository.findByTextIdAndUserId(textId, ((Users)session.getAttribute("user")).getUserId());

        if(userRate.isEmpty())
            rateRepository.save(r);
        else
            rateRepository.updateRateByRateId(r.getRate(), userRate.get(0).getRateId());

        return "redirect:/singleText/" + challengeId + "/" + textId;
    }


    @GetMapping("/createText/{challengeId}")
    public String redirectToTextCreate(@PathVariable("challengeId") Integer challengeId, Model model, HttpSession session) {

        Optional<Challenge> challenge = challengeRepository.findById(Long.valueOf(challengeId));

        if (challenge.isPresent()) {
            model.addAttribute("challengeName", challenge.get().getChallengeName());
        } else {
            model.addAttribute("challengeName", "Unknown Challenge");
        }

        model.addAttribute("text", new Text());
        model.addAttribute("isAdmin", SessionManager.IsAdmin(session));
        model.addAttribute("challenge", challenge.get());

        return "createText";
    }

    @PostMapping("/register_new_text/{challengeId}")
    @Transactional
    public String registerNewText(@ModelAttribute("text") Text text, @PathVariable("challengeId") Integer challengeId,
                                  Model model, HttpSession session,  RedirectAttributes redirectAttributes) {
        Challenge challenge = challengeRepository.findByChallengeId(challengeId);
        /*if(text.getBody().trim().split("\\s").length > challenge.getWordLimit() )
            return "redirect:/createText/{challengeId}";*/
            //redirectAttributes.addFlashAttribute("toomany", "Vous avez écrit trop de mots!");

        // Enregistrer le nouveau texte dans la base de données
        model.addAttribute("text", text);
        Users user = (Users) session.getAttribute("user");
        //user = entityManager.merge(user);
        //Challenge challenge = challengeRepository.findByChallengeId(challengeId);

        text.setStatus("pending");
        text.setTextSubmit(true);
        text.setSubmittedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        text.setUser(user);
        text.setChallenge(challenge);

        System.out.println("Username: " + user.getUsername());
        System.out.println("User ID: " + text.getUserId());
        System.out.println("Challenge ID: " + text.getChallenge().getChallengeId());
        System.out.println("Title: " + text.getTextTitle());
        System.out.println("Body: " + text.getBody());

        textRepository.save(text);

        return "redirect:/home";

    }


    @PostMapping("/comment/{challengeId}/{textId}")
    public String StoreComment(@PathVariable String challengeId, @PathVariable String textId, @ModelAttribute("commentCreator") Comment comment, Model model, HttpSession session) {

        model.addAttribute("commentCreator", comment);

        if(!comment.getBody().isEmpty()){
            comment.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
            comment.setUser((Users) session.getAttribute("user"));
            comment.setText(textRepository.findById(Integer.parseInt(textId)).get());
            commentRepository.save(comment);
        }

        return "redirect:/singleText/" + challengeId + "/" + textId;

    }


    @PostMapping("/report/{challengeId}/{textId}")
    public String StoreComment(@PathVariable String challengeId, @PathVariable String textId, @ModelAttribute("report") Report report, Model model, HttpSession session) {

        model.addAttribute("report", report);

        if(!report.getProblem().isEmpty()){
            report.setReporterId(((Users) session.getAttribute("user")).getUserId());
            report.setText(textRepository.findById(Integer.parseInt(textId)).get());
            reportRepository.save(report);
        }

        return "redirect:/singleText/" + challengeId + "/" + textId;

    }


}
