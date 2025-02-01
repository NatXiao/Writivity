package src.java.Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.mock.web.MockHttpSession;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import src.java.model.Challenge;
import src.java.model.Text;
import src.java.model.Users;
import src.java.Controllers.AdminController;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class AdminControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChallengeRepository challengeRepository;

    @Mock
    private TextRepository textRepository;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Initialisation de MockMvc pour les tests d'API REST
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void testAdminAccessWithValidAdminSession() throws Exception {
        // Préparer les mocks pour les dépôts
        List<Users> mockUsers = Arrays.asList(new Users("user1"), new Users("user2"));
        List<Challenge> mockChallenges = Arrays.asList(new Challenge("challenge1"), new Challenge("challenge2"));
        List<Text> mockTexts = Arrays.asList(new Text("text1"), new Text("text2"));

        when(userRepository.findAll()).thenReturn(mockUsers);
        when(challengeRepository.findAll()).thenReturn(mockChallenges);
        when(textRepository.findAll()).thenReturn(mockTexts);

        // Simuler un administrateur dans la session
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("isAdmin", true);

        // Effectuer une requête HTTP simulée
        mockMvc.perform(get("/admin").session(session))
                .andExpect(status().isOk())  // Vérifie que le statut HTTP est 200 OK
                .andExpect(view().name("admin"))  // Vérifie que la vue retournée est "admin"
                .andExpect(model().attribute("users", mockUsers))  // Vérifie que le modèle contient les utilisateurs
                .andExpect(model().attribute("challenges", mockChallenges))  // Vérifie que le modèle contient les challenges
                .andExpect(model().attribute("texts", mockTexts));  // Vérifie que le modèle contient les textes
    }

    @Test
    void testAdminAccessWithNonAdminSession() throws Exception {
        // Simuler un utilisateur non administrateur dans la session
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("isAdmin", false);

        // Effectuer une requête HTTP simulée
        mockMvc.perform(get("/admin").session(session))
                .andExpect(status().isFound())  // Vérifie que le statut HTTP est 302 (Redirection)
                .andExpect(redirectedUrl("/error403"));  // Vérifie que la redirection se fait vers /error403
    }
}
