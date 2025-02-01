package src.java.Test;
import src.java.Controllers.LoginController;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import src.java.Utils.PasswordUtil;
import src.java.Utils.UserRepository;
import src.java.model.LoginModel;
import src.java.model.Users;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordUtil passwordUtil;

    @InjectMocks
    private LoginController loginController;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        session = new MockHttpSession();
    }

    @Test
    public void testShowLoginForm_WhenUserIsLoggedIn() throws Exception {
        // Simuler un utilisateur déjà connecté
        session.setAttribute("user", new Users());

        mockMvc.perform(get("/login").session(session))
                .andExpect(status().is3xxRedirection())  // Vérifie que la redirection est effectuée
                .andExpect(redirectedUrl("/home"));  // Vérifie la redirection vers la page d'accueil
    }

    @Test
    public void testShowLoginForm_WhenUserIsNotLoggedIn() throws Exception {
        // Session sans utilisateur connecté
        mockMvc.perform(get("/login").session(session))
                .andExpect(status().isOk())  // Vérifie que la page de login est renvoyée
                .andExpect(view().name("login"));  // Vérifie que la vue est "login"
    }

    @Test
    public void testLogin_SuccessWithEmail() throws Exception {
        // Création de données de test pour un utilisateur existant
        String usernameOrEmail = "test@example.com";
        String password = "password123";
        Users mockUser = new Users();
        mockUser.setMail(usernameOrEmail);
        mockUser.setPassword("hashedPassword");

        // Mocking de la méthode de récupération de l'utilisateur par email
        when(userRepository.findByMail(usernameOrEmail)).thenReturn(Optional.of(mockUser));
        when(PasswordUtil.verifyPassword(password, "hashedPassword")).thenReturn(true);

        mockMvc.perform(post("/login_user")
                        .param("identifiant", usernameOrEmail)
                        .param("password", password)
                        .session(session))
                .andExpect(status().is3xxRedirection())  // Vérifie la redirection après une connexion réussie
                .andExpect(redirectedUrl("/home"));  // Vérifie la redirection vers la page d'accueil
    }

    @Test
    public void testLogin_FailureWithWrongPassword() throws Exception {
        // Création de données de test pour un utilisateur existant
        String usernameOrEmail = "test@example.com";
        String password = "wrongPassword";
        Users mockUser = new Users();
        mockUser.setMail(usernameOrEmail);
        mockUser.setPassword("hashedPassword");

        // Mocking de la méthode de récupération de l'utilisateur par email
        when(userRepository.findByMail(usernameOrEmail)).thenReturn(Optional.of(mockUser));
        when(PasswordUtil.verifyPassword(password, "hashedPassword")).thenReturn(false);

        mockMvc.perform(post("/login_user")
                        .param("identifiant", usernameOrEmail)
                        .param("password", password)
                        .session(session))
                .andExpect(status().is3xxRedirection())  // Vérifie la redirection après un échec de la connexion
                .andExpect(redirectedUrl("/login"))  // Vérifie la redirection vers la page de login
                .andExpect(request().sessionAttribute("error", "Error, retry please !"));  // Vérifie que l'erreur est présente dans la session
    }

    @Test
    public void testLogin_UserNotFound() throws Exception {
        // Test si l'utilisateur n'est pas trouvé
        String usernameOrEmail = "nonexistent@example.com";
        String password = "password123";

        // Mocking de la méthode de récupération de l'utilisateur (aucun utilisateur trouvé)
        when(userRepository.findByMail(usernameOrEmail)).thenReturn(Optional.empty());

        mockMvc.perform(post("/login_user")
                        .param("identifiant", usernameOrEmail)
                        .param("password", password)
                        .session(session))
                .andExpect(status().is3xxRedirection())  // Vérifie la redirection après un échec de la connexion
                .andExpect(redirectedUrl("/login"))  // Vérifie la redirection vers la page de login
                .andExpect(request().sessionAttribute("error", "Il n'y a pas d'utilisateur avec cet identifiant ou cet email !"));  // Vérifie le message d'erreur dans la session
    }

    @Test
    public void testLogout() throws Exception {
        // Simuler un utilisateur connecté
        session.setAttribute("user", new Users());

        mockMvc.perform(get("/logout").session(session))
                .andExpect(status().is3xxRedirection())  // Vérifie la redirection
                .andExpect(redirectedUrl("/login"));  // Vérifie la redirection vers la page de login après la déconnexion

        // Vérifie que la session a bien été invalidée
        assertNull(session.getAttribute("user"));
    }
}
