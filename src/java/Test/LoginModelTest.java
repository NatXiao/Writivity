package src.java.Test;

import org.junit.Test;
import static org.junit.Assert.*;
import src.java.model.LoginModel;


public class LoginModelTest {

    @Test
    public void testId() {
        // Créer un objet LoginModel
        LoginModel loginModel = new LoginModel();
        loginModel.setId(1L);

        // Vérifier que l'ID est correctement assigné et récupéré
        assertEquals((Long) 1L, loginModel.getId());
    }

    @Test
    public void testIdentifiant() {
        // Créer un objet LoginModel
        LoginModel loginModel = new LoginModel();
        loginModel.setIdentifiant("test@domain.com");

        // Vérifier que l'identifiant est correctement assigné et récupéré
        assertEquals("test@domain.com", loginModel.getIdentifiant());
    }

    @Test
    public void testPassword() {
        // Créer un objet LoginModel
        LoginModel loginModel = new LoginModel();
        loginModel.setPassword("securePassword123");

        // Vérifier que le mot de passe est correctement assigné et récupéré
        assertEquals("securePassword123", loginModel.getPassword());
    }

    @Test
    public void quickTest() {
        // Test trivial qui réussit immédiatement
        assertTrue(true);
    }
}