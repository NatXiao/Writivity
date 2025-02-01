package src.java.Test;

import org.junit.Test;

import static org.junit.Assert.*;

import src.java.model.Challenge;

import java.time.LocalDate;

public class ChallengeTest {

    @Test
    public void testChallengeName() {
        // Créer un objet Challenge
        Challenge challenge = new Challenge();
        challenge.setChallengeName("Défi de programmation");

        // Vérifier que le nom du challenge est correct
        assertEquals("Défi de programmation", challenge.getChallengeName());
    }

    @Test
    public void testChallengeWordLimit() {
        // Créer un objet Challenge
        Challenge challenge = new Challenge();
        challenge.setWordLimit(500);

        // Vérifier que la limite de mots est correcte
        assertEquals((Integer) 500, challenge.getWordLimit());
    }

    @Test
    public void testChallengeOpenAt() {
        // Créer un objet Challenge
        Challenge challenge = new Challenge();
        LocalDate openDate = LocalDate.of(2025, 2, 1);
        challenge.setOpenAt(openDate);

        // Vérifier que la date d'ouverture est correcte
        assertEquals(openDate, challenge.getOpenAt());
    }

    @Test
    public void testChallengeCloseAt() {
        // Créer un objet Challenge
        Challenge challenge = new Challenge();
        LocalDate closeDate = LocalDate.of(2025, 3, 1);
        challenge.setCloseAt(closeDate);

        // Vérifier que la date de fermeture est correcte
        assertEquals(closeDate, challenge.getCloseAt());
    }

    @Test
    public void testChallengeConditions() {
        // Créer un objet Challenge
        Challenge challenge = new Challenge();
        challenge.setConditions("Conditions spéciales");

        // Vérifier que les conditions sont correctes
        assertEquals("Conditions spéciales", challenge.getConditions());
    }

    @Test
    public void testChallengeId() {
        // Créer un objet Challenge
        Challenge challenge = new Challenge();
        challenge.setChallengeId(1);

        // Vérifier que l'ID du challenge est correct
        assertEquals((Integer) 1, challenge.getChallengeId());
    }

    @Test
    public void quickTest() {
        // Test trivial qui réussit immédiatement
        assertTrue(true);
    }
}