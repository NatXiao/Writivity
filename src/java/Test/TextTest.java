package src.java.Test;

import org.junit.Test;

import static org.junit.Assert.*;

import src.java.model.Text;
import src.java.model.Users;
import src.java.model.Challenge;
import src.java.model.Comment;

import java.sql.Timestamp;
import java.util.Arrays;

public class TextTest {

    @Test
    public void testTextId() {
        // Créer un objet Text
        Text text = new Text();
        text.setTextId(1);

        // Vérifier que le textId est correctement assigné et récupéré
        assertEquals((Integer) 1, text.getTextId());
    }

    @Test
    public void testUser() {
        // Créer un objet User
        Users user = new Users();
        user.setUserId(1);

        // Créer un objet Text et lier l'utilisateur
        Text text = new Text();
        text.setUser(user);

        // Vérifier que l'utilisateur est correctement assigné et récupéré
        assertEquals((Integer) 1, text.getUserId());
    }

    @Test
    public void testChallenge() {
        // Créer un objet Challenge
        Challenge challenge = new Challenge();
        challenge.setChallengeId(1);

        // Créer un objet Text et lier le challenge
        Text text = new Text();
        text.setChallenge(challenge);

        // Vérifier que le challenge est correctement assigné et récupéré
        assertEquals((Integer) 1, text.getChallengeId());
    }

    @Test
    public void testTextTitle() {
        // Créer un objet Text
        Text text = new Text();
        text.setTextTitle("Test Title");

        // Vérifier que le textTitle est correctement assigné et récupéré
        assertEquals("Test Title", text.getTextTitle());
    }

    @Test
    public void testBody() {
        // Créer un objet Text
        Text text = new Text();
        text.setBody("This is the body of the text.");

        // Vérifier que le body est correctement assigné et récupéré
        assertEquals("This is the body of the text.", text.getBody());
    }

    @Test
    public void testStatus() {
        // Créer un objet Text
        Text text = new Text();
        text.setStatus("Submitted");

        // Vérifier que le status est correctement assigné et récupéré
        assertEquals("Submitted", text.getStatus());
    }

    @Test
    public void testTextSubmit() {
        // Créer un objet Text
        Text text = new Text();
        text.setTextSubmit(true);

        // Vérifier que textSubmit est correctement assigné et récupéré
        assertTrue(text.getTextSubmit());
    }

    @Test
    public void testSubmittedAt() {
        // Créer un objet Text
        Text text = new Text();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        text.setSubmittedAt(timestamp);

        // Vérifier que submittedAt est correctement assigné et récupéré
        assertEquals(timestamp, text.getSubmittedAt());
    }

    @Test
    public void testReported() {
        // Créer un objet Text
        Text text = new Text();
        text.setReported(true);

        // Vérifier que reported est correctement assigné et récupéré
        assertTrue(text.getReported());
    }

    @Test
    public void testDisqualified() {
        // Créer un objet Text
        Text text = new Text();
        text.setDisqualified(true);

        // Vérifier que disqualified est correctement assigné et récupéré
        assertTrue(text.getDisqualified());
    }

    @Test
    public void testComments() {
        // Créer un objet Comment
        Comment comment = new Comment();
        comment.setBody("This is a comment");

        // Créer un objet Text et ajouter le commentaire
        Text text = new Text();
        text.setComments(Arrays.asList(comment));

        // Vérifier que les commentaires sont correctement assignés et récupérés
        assertNotNull(text.getComments());
        assertEquals(1, text.getComments().size());
        assertEquals("This is a comment", text.getComments().get(0).getBody());
    }

    @Test
    public void quickTest() {
        // Test trivial qui réussit immédiatement
        assertTrue(true);
    }
}