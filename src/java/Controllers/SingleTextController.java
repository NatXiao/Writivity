package src.java.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import src.java.model.Comment;
import src.java.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import src.java.model.Text;
import src.java.Utils.TextRepository;

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
                                @PathVariable("textId") Integer textId, Model model) {
        // Récupérer le texte par son ID et l'ID du challenge
        Text text = textRepository.findTextByChallengeIdAndTextId(challengeId, textId).orElse(null);

        if (text != null) {
            model.addAttribute("text", text);  // Ajouter le texte au modèle
        } else {
            // Si le texte n'est pas trouvé, rediriger vers la page d'accueil ou afficher une erreur
            return "redirect:/home";  // Ou une autre page d'erreur
        }

        return "singleText";  // Nom de la page HTML à afficher
    }
}
