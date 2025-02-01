package src.java.Test;

import org.junit.Test;

import static org.junit.Assert.*;

import src.java.model.Comment;
import src.java.model.Text;
import src.java.model.Users;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommentTest {

    @Test
    public void testCommentId() {
        // Créer un objet Comment
        Comment comment = new Comment();
        comment.setCommentId(1);

        // Vérifier que l'ID du commentaire est correct
        assertEquals((Integer) 1, comment.getCommentId());
    }

    @Test
    public void testText() {
        // Créer un objet Text (simulation simple pour ce test)
        Text text = new Text();
        text.setTextId(1); // Simuler un ID de texte

        // Créer un objet Comment
        Comment comment = new Comment();
        comment.setText(text);

        // Vérifier que le texte associé au commentaire est correct
        assertEquals(text, comment.getText());
    }

    @Test
    public void testUser() {
        // Créer un objet Users (simulation simple pour ce test)
        Users user = new Users();
        user.setUserId(1); // Simuler un ID d'utilisateur

        // Créer un objet Comment
        Comment comment = new Comment();
        comment.setUser(user);

        // Vérifier que l'utilisateur associé au commentaire est correct
        assertEquals(user, comment.getUser());
    }

    @Test
    public void testBody() {
        // Créer un objet Comment
        Comment comment = new Comment();
        comment.setBody("Ceci est un commentaire");

        // Vérifier que le corps du commentaire est correct
        assertEquals("Ceci est un commentaire", comment.getBody());
    }

    @Test
    public void testReported() {
        // Créer un objet Comment
        Comment comment = new Comment();
        comment.setReported(true);

        // Vérifier que le commentaire est marqué comme signalé
        assertTrue(comment.getReported());
    }

    @Test
    public void testCreatedAt() {
        // Créer un objet Comment
        Comment comment = new Comment();
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        comment.setCreatedAt(timestamp);

        // Vérifier que la date de création est correcte
        assertEquals(timestamp, comment.getCreatedAt());
    }

    @Test
    public void testDeleted() {
        // Créer un objet Comment
        Comment comment = new Comment();
        comment.setDeleted(true);

        // Vérifier que le commentaire est marqué comme supprimé
        assertTrue(comment.getDeleted());
    }

    @Test
    public void quickTest() {
        // Test trivial qui réussit immédiatement
        assertTrue(true);
    }
}