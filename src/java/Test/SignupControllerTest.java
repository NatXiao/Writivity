/*package src.java.Test;


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
import src.java.Utils.PasswordUtil;
import src.java.Utils.UserRepository;
import src.java.model.Users;
import src.java.Controllers.SignupController;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordUtil passwordUtil;

    @InjectMocks
    private SignupController signupController;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(signupController).build();
        session = new MockHttpSession();
    }

    @Test
    public void testShowSignupForm() throws Exception {
        mockMvc.perform(get("/signup").session(session))
                .andExpect(status().isOk())  // Vérifie que la page est rendue
                .andExpect(view().name("signup"));  // Vérifie que la vue est la page de signup
    }

    @Test
    public void testRegisterNewUser_Success() throws Exception {
        // Création de données de test pour un utilisateur valide
        Users mockUser = new Users();
        mockUser.setUsername("newuser");
        mockUser.setMail("newuser@example.com");
        mockUser.setPassword("password123");

        // Mocking pour vérifier que l'utilisateur n'existe pas déjà
        when(userRepository.findByUsername(mockUser.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByMail(mockUser.getMail())).thenReturn(Optional.empty());
        when(passwordUtil.hashPassword(mockUser.getPassword())).thenReturn("hashedPassword");

        mockMvc.perform(post("/register_new_user")
                        .param("username", "newuser")
                        .param("mail", "newuser@example.com")
                        .param("password", "password123")
                        .session(session))
                .andExpect(status().is3xxRedirection())  // Vérifie la redirection après l'enregistrement
                .andExpect(redirectedUrl("/home"));  // Vérifie que l'utilisateur est redirigé vers la page d'accueil

        verify(userRepository, times(1)).save(mockUser);  // Vérifie que la méthode save a bien été appelée
    }

    @Test
    public void testRegisterNewUser_UsernameAlreadyExists() throws Exception {
        // Création de données de test pour un utilisateur déjà existant
        Users mockUser = new Users();
        mockUser.setUsername("existinguser");
        mockUser.setMail("existinguser@example.com");

        // Mocking pour simuler un utilisateur déjà existant
        when(userRepository.findByUsername(mockUser.getUsername())).thenReturn(Optional.of(mockUser));

        mockMvc.perform(post("/register_new_user")
                        .param("username", "existinguser")
                        .param("mail", "newuser@example.com")
                        .param("password", "password123")
                        .session(session))
                .andExpect(status().is3xxRedirection())  // Vérifie la redirection après l'échec
                .andExpect(redirectedUrl("/signup"))  // Vérifie la redirection vers la page de signup
                .andExpect(request().sessionAttribute("error", "Username is already used !"));  // Vérifie le message d'erreur dans la session
    }

    @Test
    public void testRegisterNewUser_EmailAlreadyExists() throws Exception {
        // Création de données de test pour un utilisateur avec un email déjà pris
        Users mockUser = new Users();
        mockUser.setUsername("newuser");
        mockUser.setMail("existinguser@example.com");

        // Mocking pour simuler un email déjà pris
        when(userRepository.findByUsername(mockUser.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByMail(mockUser.getMail())).thenReturn(Optional.of(mockUser));

        mockMvc.perform(post("/register_new_user")
                        .param("username", "newuser")
                        .param("mail", "existinguser@example.com")
                        .param("password", "password123")
                        .session(session))
                .andExpect(status().is3xxRedirection())  // Vérifie la redirection après l'échec
                .andExpect(redirectedUrl("/signup"))  // Vérifie la redirection vers la page de signup
                .andExpect(request().sessionAttribute("error", "Email is already used !"));  // Vérifie le message d'erreur dans la session
    }

    @Test
    public void testRegisterNewUser_EmptyFields() throws Exception {
        // Test avec un champ de mot de passe vide
        mockMvc.perform(post("/register_new_user")
                        .param("username", "")
                        .param("mail", "")
                        .param("password", "")
                        .session(session))
                .andExpect(status().is3xxRedirection())  // Vérifie la redirection après une erreur
                .andExpect(redirectedUrl("/signup"))  // Vérifie la redirection vers la page de signup
                .andExpect(request().sessionAttribute("error", "The password cannot be empty !"));  // Vérifie le message d'erreur pour un mot de passe vide
    }
}*/
