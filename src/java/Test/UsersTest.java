package src.java.Test;

import org.junit.Test;

import static org.junit.Assert.*;

import src.java.model.Users;
import src.java.model.Text;

import java.util.List;
import java.util.ArrayList;

public class UsersTest {

    @Test
    public void testUserId() {
        // Créer un objet Users
        Users user = new Users();
        user.setUserId(1);

        // Vérifier que userId est correctement assigné et récupéré
        assertEquals((Integer) 1, user.getUserId());
    }

    @Test
    public void testPseudo() {
        // Créer un objet Users
        Users user = new Users();
        user.setPseudo("userPseudo");

        // Vérifier que pseudo est correctement assigné et récupéré
        assertEquals("userPseudo", user.getPseudo());
    }

    @Test
    public void testUsername() {
        // Créer un objet Users
        Users user = new Users();
        user.setUsername("userName");

        // Vérifier que username est correctement assigné et récupéré
        assertEquals("userName", user.getUsername());
    }

    @Test
    public void testMail() {
        // Créer un objet Users
        Users user = new Users();
        user.setMail("user@mail.com");

        // Vérifier que mail est correctement assigné et récupéré
        assertEquals("user@mail.com", user.getMail());
    }

    @Test
    public void testPassword() {
        // Créer un objet Users
        Users user = new Users();
        user.setPassword("password123");

        // Vérifier que password est correctement assigné et récupéré
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testIsAdmin() {
        // Créer un objet Users
        Users user = new Users();
        user.setAdmin(true);

        // Vérifier que isAdmin est correctement assigné et récupéré
        assertTrue(user.isAdmin());
    }

    @Test
    public void testSetText() {
        // Créer un objet Users
        Users user = new Users();
        Text text = new Text();  // Assurez-vous d'importer la classe Text
        List<Text> texts = new ArrayList<>();
        texts.add(text);
        user.setTexts(texts);

        // Vérifier que la liste des textes est correctement assignée et récupérée
        assertNotNull(user.getTexts());
        assertEquals(1, user.getTexts().size());
        assertEquals(text, user.getTexts().get(0));
    }

    @Test
    public void testAddTextToUser() {
        // Créer un objet Users et un Text
        Users user = new Users();
        Text text = new Text();

        // Ajouter le texte à l'utilisateur
        user.getTexts().add(text);

        // Vérifier que la liste des textes contient bien le texte ajouté
        assertTrue(user.getTexts().contains(text));
    }

    @Test
    public void testRemoveTextFromUser() {
        // Créer un objet Users et un Text
        Users user = new Users();
        Text text = new Text();

        // Ajouter et supprimer un texte de la liste
        user.getTexts().add(text);
        user.getTexts().remove(text);

        // Vérifier que la liste des textes est vide après suppression
        assertFalse(user.getTexts().contains(text));
    }

    @Test
    public void quickTest() {
        // Test trivial qui réussit immédiatement
        assertTrue(true);
    }
}