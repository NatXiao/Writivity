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
import org.springframework.ui.Model;
import org.springframework.mock.web.MockHttpSession;
import src.java.Controllers.BaseController;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import src.java.Utils.ChallengeRepository;
import src.java.model.Challenge;
import src.java.SessionManager;

@SpringBootTest
public class BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ChallengeRepository challengeRepository;

    @InjectMocks
    private BaseController baseController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(baseController).build();
    }

    @Test
    public void testHome2_UserNotLoggedIn() throws Exception {
        // Simuler une session sans connexion
        MockHttpSession session = new MockHttpSession();

        mockMvc.perform(get("/home").session(session))
                .andExpect(status().is3xxRedirection())  // Vérifie la redirection
                .andExpect(redirectedUrl("/login"));     // Vérifie que la redirection se fait vers /login
    }

    @Test
    public void testHome2_UserLoggedIn() throws Exception {
        // Créer des mocks de challenges
        List<Challenge> allChallenges = new ArrayList<>();
        Challenge currentChallenge = new Challenge();
        currentChallenge.setCloseAt(LocalDate.now().plusDays(1));  // Challenge en cours
        Challenge oldChallenge = new Challenge();
        oldChallenge.setCloseAt(LocalDate.now().minusDays(1));     // Challenge passé
        allChallenges.add(currentChallenge);
        allChallenges.add(oldChallenge);

        // Mock des comportements
        when(challengeRepository.findAll()).thenReturn(allChallenges);
        when(SessionManager.isLoggedIn(any(HttpSession.class))).thenReturn(true); // Simuler que l'utilisateur est connecté
        when(SessionManager.IsAdmin(any(HttpSession.class))).thenReturn(false);  // Simuler que l'utilisateur n'est pas admin

        // Simuler une session avec un utilisateur connecté
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("isLoggedIn", true);

        // Appeler l'endpoint et vérifier le comportement
        mockMvc.perform(get("/home").session(session))
                .andExpect(status().isOk())  // Vérifie que le statut HTTP est 200 OK
                .andExpect(view().name("home"))  // Vérifie que la vue est "home"
                .andExpect(model().attributeExists("Theme"))  // Vérifie que "Theme" (challenges en cours) est présent dans le modèle
                .andExpect(model().attributeExists("OldTheme"))  // Vérifie que "OldTheme" (anciens challenges) est présent dans le modèle
                .andExpect(model().attribute("isAdmin", false)); // Vérifie que "isAdmin" est false dans le modèle
    }

    @Test
    public void testHome2_UserLoggedInWithNoChallenges() throws Exception {
        // Créer une liste vide de challenges
        List<Challenge> allChallenges = new ArrayList<>();

        // Mock des comportements
        when(challengeRepository.findAll()).thenReturn(allChallenges);
        when(SessionManager.isLoggedIn(any(HttpSession.class))).thenReturn(true); // Simuler que l'utilisateur est connecté

        // Simuler une session avec un utilisateur connecté
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("isLoggedIn", true);

        // Appeler l'endpoint et vérifier le comportement
        mockMvc.perform(get("/home").session(session))
                .andExpect(status().isOk())  // Vérifie que le statut HTTP est 200 OK
                .andExpect(view().name("home"))  // Vérifie que la vue est "home"
                .andExpect(model().attribute("Theme", new ArrayList<>()))  // Vérifie que "Theme" est vide
                .andExpect(model().attribute("OldTheme", new ArrayList<>())); // Vérifie que "OldTheme" est vide
    }
}