package src.java.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import src.java.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import src.java.Utils.TextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import src.java.model.Comment;
import src.java.model.Text;

import java.util.ArrayList;

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

        return "singleText";  // Nom de la page HTML à afficher
    }

    @PostMapping("/register_new_text")
    public String registerNewText(@ModelAttribute("text") Text text, Model model) {
        // Enregistrer le nouveau texte dans la base de données
        model.addAttribute("text", text);
        text.setStatus("pending");
        text.setTextSubmit(true);
        text.setSubmittedAt(new java.sql.Timestamp(System.currentTimeMillis()));

        System.out.println("Username: " + text.getUser().getUsername());
        System.out.println("Challenge ID: " + text.getChallenge().getChallengeId());
        System.out.println("Title: " + text.getTextTitle());
        System.out.println("Body: " + text.getBody());

        textRepository.save(text);

        return "redirect:/singleChallenge";  // Rediriger vers la page d'accueil
    }
}
