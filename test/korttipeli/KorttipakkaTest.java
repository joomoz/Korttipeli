/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korttipeli;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
public class KorttipakkaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    Korttipakka pakka;

    public KorttipakkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setErr(null);
    }

    /**
     * Test of sekoitus method, of class Korttipakka.
     *
     * Testataan, että ensimmäinen kortti vaihtuu.
     */
    @Test
    public void testSekoitus() {
        pakka = new Korttipakka();
        Pelikortti kortti1 = pakka.ensimmainenKortti();
        pakka.sekoitus();
        Pelikortti kortti2 = pakka.ensimmainenKortti();
        assertNotSame(kortti1, kortti2);
    }

    /**
     * Test of otaKortti method, of class Korttipakka. Korttimäärän pitäisi
     * vähentyä yhdellä.
     */
    @Test
    public void testOtaKortti() {
        pakka = new Korttipakka();
        int maaraAlussa = pakka.korttienMaara();
        Pelikortti kortti = pakka.otaKortti();
        assertEquals(maaraAlussa - 1, pakka.korttienMaara());
    }

}
