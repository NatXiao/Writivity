package src.java.Controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.Mergeable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import src.java.SessionManager;
import src.java.Utils.ChallengeRepository;
import src.java.Utils.UserRepository;
import src.java.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import src.java.Utils.RateRepository;
import src.java.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import src.java.Utils.TextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import src.java.model.Text;

import java.util.List;

@Controller
public class SingleTextController {

    //@GetMapping("/textId")
    //public String singleText(Model model1, Model model2){
    //    model1.addAttribute("Text", new Text());
    //    model2.addAttribute("Comment", new ArrayList<Comment>());
    //    return "singleText";
    //}
    @Autowired
    private TextRepository textRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private ChallengeRepository challengeRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

        float mean = 0;
        for (Rate rate : rates) {
            mean += rate.getRate();
        }
        mean /= rates.size();

        int roundedMean = Math.round(mean);

        model.addAttribute("roundedMean", roundedMean);
        model.addAttribute("mean", mean);


        List<Rate> userRate = rateRepository.findByTextIdAndUserId(textId, ((Users)session.getAttribute("user")).getUser_id());

        if (userRate.isEmpty())
            model.addAttribute("userRate", 0);
        else
            model.addAttribute("userRate", userRate.get(0).getRate());


        return "singleText";  // Nom de la page HTML à afficher
    }

    @GetMapping("/createText/{challengeId}")
    public String redirectToTextCreate(@PathVariable("challengeId") Integer challengeId, Model model, HttpSession session) {
        model.addAttribute("text", new Text());
        model.addAttribute("isAdmin", SessionManager.IsAdmin(session));
        model.addAttribute("challengeId", challengeId);

        System.out.println("Challenge ID reçu dans la page: " + challengeId); // Debug

        return "createText";
    }

    @GetMapping("/rate/{challengeId}/{textId}/{rate}")
    public String rateText(@PathVariable("challengeId") Integer challengeId,
                                @PathVariable("textId") Integer textId, @PathVariable("rate") Integer rate, Model model, HttpSession session) {

        Rate r = new Rate();
        r.setTextId(textId);
        r.setUserId(((Users)session.getAttribute("user")).getUser_id());
        r.setRate(rate);

        System.out.println(r.getUserId() + " / " + r.getTextId() + " / " + r.getRate());

        List<Rate> userRate = rateRepository.findByTextIdAndUserId(textId, ((Users)session.getAttribute("user")).getUser_id());

        if(userRate.isEmpty())
            rateRepository.save(r);
        else
            rateRepository.updateRateByRateId(r.getRate(), userRate.get(0).getRateId());

        model.addAttribute("userId", ((Users)session.getAttribute("user")).getUser_id());

        return "redirect:/singleText/" + challengeId + "/" + textId;  // Nom de la page HTML à afficher
    }


    @PostMapping("/register_new_text/{challengeId}")
    @Transactional
    public String registerNewText(@ModelAttribute("text") Text text, @PathVariable("challengeId") Integer challengeId,
                                  Model model, HttpSession session) {
        // Enregistrer le nouveau texte dans la base de données
        model.addAttribute("text", text);
        Users user = (Users) session.getAttribute("user");
        //user = entityManager.merge(user);
        Challenge challenge = challengeRepository.findByChallengeId(challengeId);

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

        return "redirect:/home";  // Rediriger vers la page d'accueil
    }
}
