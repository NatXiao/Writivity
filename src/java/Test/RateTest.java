package src.java.Test;

import org.junit.Test;

import static org.junit.Assert.*;

import src.java.model.Rate;

public class RateTest {

    @Test
    public void testRateId() {
        // Créer un objet Rate
        Rate rate = new Rate();
        rate.setRateId(1);

        // Vérifier que le rateId est correctement assigné et récupéré
        assertEquals((Integer) 1, rate.getRateId());
    }

    @Test
    public void testUserId() {
        // Créer un objet Rate
        Rate rate = new Rate();
        rate.setUserId(100);

        // Vérifier que le userId est correctement assigné et récupéré
        assertEquals((Integer) 100, rate.getUserId());
    }

    @Test
    public void testTextId() {
        // Créer un objet Rate
        Rate rate = new Rate();
        rate.setTextId(200);

        // Vérifier que le textId est correctement assigné et récupéré
        assertEquals((Integer) 200, rate.getTextId());
    }

    @Test
    public void testRate() {
        // Créer un objet Rate
        Rate rate = new Rate();
        rate.setRate(5);

        // Vérifier que le rate est correctement assigné et récupéré
        assertEquals((Integer) 5, rate.getRate());
    }

    @Test
    public void quickTest() {
        // Test trivial qui réussit immédiatement
        assertTrue(true);
    }
}