package src.java.Test;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import src.java.Utils.ChallengeRepository;
import src.java.Utils.TextRepository;
import src.java.model.Challenge;
import src.java.model.Text;
import src.java.SessionManager;
import src.java.model.Comparator.TextComparator;
import src.java.Controllers.ChallengeController;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class ChallengeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ChallengeRepository challengeRepository;

    @Mock
    private TextRepository textRepository;

    @InjectMocks
    private ChallengeController challengeController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(challengeController).build();
    }

    @Test
    public void testSingleChallenge_ChallengeNotFound() throws Exception {
        // Mock d'une session avec un utilisateur connecté
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", "testUser");

        // Simuler un challenge inexistant
        when(challengeRepository.findById(anyLong())).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/challenge/1").session(session))
                .andExpect(status().isOk())  // Vérifie que le statut HTTP est 200 OK
                .andExpect(view().name("error"));  // Vérifie que la vue retournée est "error"
    }

    @Test
    public void testSingleChallenge_Success() throws Exception {
        // Mock d'une session avec un utilisateur connecté
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", "testUser");

        // Mock des données pour un challenge existant
        Challenge challenge = new Challenge();
        challenge.setId(1L);
        challenge.setChallengeName("Challenge 1");
        challenge.setConditions("Condition 1");
        challenge.setWordLimit(1000);

        List<Text> texts = new ArrayList<>();
        Text text = new Text();
        text.setId(1L);
        texts.add(text);

        // Simuler la récupération du challenge et des textes associés
        when(challengeRepository.findById(1L)).thenReturn(java.util.Optional.of(challenge));
        when(textRepository.findTextsByChallengeId(1)).thenReturn(texts);

        mockMvc.perform(get("/challenge/1").session(session))
                .andExpect(status().isOk())  // Vérifie que le statut HTTP est 200 OK
                .andExpect(view().name("singleChallenge"))  // Vérifie que la vue retournée est "singleChallenge"
                .andExpect(model().attribute("Challenge", challenge))  // Vérifie que le challenge est bien dans le modèle
                .andExpect(model().attribute("Text", texts));  // Vérifie que les textes sont bien dans le modèle
    }

    @Test
    public void testRedirectToChallengeCreate() throws Exception {
        // Mock d'une session avec un utilisateur connecté
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", "testUser");

        // Simuler qu'il est administrateur
        when(SessionManager.IsAdmin(session)).thenReturn(true);

        mockMvc.perform(get("/createChallenge").session(session))
                .andExpect(status().isOk())  // Vérifie que le statut HTTP est 200 OK
                .andExpect(view().name("createChallenge"))  // Vérifie que la vue retournée est "createChallenge"
                .andExpect(model().attributeExists("challenge"));  // Vérifie que l'attribut "challenge" est présent dans le modèle
    }

    @Test
    public void testRegisterNewChallenge() throws Exception {
        // Créer un challenge à enregistrer
        Challenge challenge = new Challenge();
        challenge.setChallengeName("New Challenge");
        challenge.setConditions("New conditions");
        challenge.setWordLimit(500);

        // Simuler l'enregistrement du challenge
        when(challengeRepository.save(challenge)).thenReturn(challenge);

        mockMvc.perform(post("/register_new_challenge")
                        .param("challengeName", "New Challenge")
                        .param("conditions", "New conditions")
                        .param("wordLimit", "500"))
                .andExpect(status().is3xxRedirection())  // Vérifie la redirection après l'enregistrement
                .andExpect(redirectedUrl("/home"));  // Vérifie la redirection vers la page d'accueil
    }
}
