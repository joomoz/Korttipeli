/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korttipeli;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joonas
 */
public class BlackjackTest {

    private Blackjack peli;
    private Pelaaja ihminen;
    private Pelaaja jakaja;
    private double panos;
    private double voitto;

    public BlackjackTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        peli = new Blackjack();
        ihminen = new Pelaaja("ihminen");
        jakaja = new Pelaaja("jakaja");
        panos = 20;
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pelaajaHaviaa() {
        ihminen.lisaaKortti(new Pelikortti(Maa.values()[3], 10, null));
        ihminen.lisaaKortti(new Pelikortti(Maa.values()[3], 11, null));
        ihminen.lisaaKortti(new Pelikortti(Maa.values()[3], 12, null));
        jakaja.lisaaKortti(new Pelikortti(Maa.values()[1], 10, null));
        jakaja.lisaaKortti(new Pelikortti(Maa.values()[1], 10, null));
        voitto = peli.tarkastaVoittosumma();
        assertEquals(0 * panos, voitto, 0.5);
    }

    @Test
    public void pelaajaVoittaaNormaalisti() {
        peli.ihminen.lisaaKortti(new Pelikortti(Maa.values()[3], 10, null));
        ihminen.lisaaKortti(new Pelikortti(Maa.values()[0], 10, null));
        jakaja.lisaaKortti(new Pelikortti(Maa.values()[1], 8, null));
        jakaja.lisaaKortti(new Pelikortti(Maa.values()[2], 9, null));
        voitto = peli.tarkastaVoittosumma();
        assertEquals(2 * panos, voitto, 0.05);
    }
}
