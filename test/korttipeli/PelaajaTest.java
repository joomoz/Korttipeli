
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
 * PelaajaTest
 * @author Joonas
 */
public class PelaajaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    private Pelaaja pleijeri;
    private Pelikortti kortti;
    private Pelikortti kortti2;
    private Pelikortti kortti3;

    public PelaajaTest() {
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
        pleijeri = new Pelaaja("test");
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setErr(null);
    }

    /**
     * Testaa käden tyhjentämisen.
     */
    @Test
    public void testTyhjennaKasi() {
        pleijeri.tyhjennaKasi();
        assertEquals(pleijeri.korttienMaara(), 0);
    }

    /**
     * Testaa lisäkortin ottamisen.
     */
    @Test
    public void korttienMaaraKasvaaYhdella() {
        kortti = new Pelikortti(Maa.values()[1], 10, null);
        int maaraEnnen = pleijeri.korttienMaara();
        boolean arvo = pleijeri.lisaaKortti(kortti);
        assertEquals(maaraEnnen + 1, pleijeri.korttienMaara());
    }
    
    /**
     * Testaa lisäkortin ottamisen palauttaamaa totuusarvoa.
     */
    @Test
    public void arvoTrueKunArvoKadenArvoAlle21() {
        kortti = new Pelikortti(Maa.values()[1], 10, null);
        boolean arvo = pleijeri.lisaaKortti(kortti);
        assertTrue(arvo);
    }
    
    /**
     * Testaa lisäkortin ottamisen palauttaamaa totuusarvoa, 
     * kun arvo menee yli 21.
     */
    @Test
    public void arvoFalseKunKadenArvoYli21() {
        kortti = new Pelikortti(Maa.values()[0], 8, null);
        kortti2 = new Pelikortti(Maa.values()[1], 8, null);
        kortti3 = new Pelikortti(Maa.values()[2], 8, null);
        boolean arvo = pleijeri.lisaaKortti(kortti);
        arvo = pleijeri.lisaaKortti(kortti2);
        arvo = pleijeri.lisaaKortti(kortti3);
        assertFalse(arvo);
    }

    /**
     * Testaa kädessä olevien korttien summan laskemisen.
     * Tässä testissä vain yksi kortti, arvo 10.
     */
    @Test
    public void kadenSumma10() {
        kortti = new Pelikortti(Maa.values()[1], 10, null);
        boolean arvo = pleijeri.lisaaKortti(kortti);
        assertEquals(pleijeri.getSumma(), 10);
    }
    
    /**
     * Testaa kädessä olevien korttien summan laskemisen.
     * Tässä testissä vain yksi kortti, Kuningas, arvo 10.
     */
    @Test
    public void kadenSummaKunKadessaKuningas() {
        kortti = new Pelikortti(Maa.values()[1], 13, null);
        boolean arvo = pleijeri.lisaaKortti(kortti);
        assertEquals(pleijeri.getSumma(), 10);
    }
    
    /**
     * Testaa kädessä olevien korttien summan laskemisen.
     * Tässä testissä otetaan kaksi korttia, Ässä ja 4, joiden summa tulisi olla 15.
     */
    @Test
    public void kadenSummaAssallaJaNelosella() {
        kortti = new Pelikortti(Maa.values()[1], 4, null);
        boolean arvo = pleijeri.lisaaKortti(kortti);
        kortti2 = new Pelikortti(Maa.values()[1], 1, null);
        arvo = pleijeri.lisaaKortti(kortti2);
        assertEquals(pleijeri.getSumma(), 15);
    }
    
    /**
     * Testaa kädessä olevien korttien summan laskemisen.
     * Tässä testissä otetaan kolme korttia, kaksi ässää ja 9, joiden summa tulisi olla 21.
     */
    @Test
    public void kadenSummaKahdellaAssalla() {
        kortti = new Pelikortti(Maa.values()[1], 1, null);
        boolean arvo = pleijeri.lisaaKortti(kortti);
        kortti2 = new Pelikortti(Maa.values()[2], 1, null);
        arvo = pleijeri.lisaaKortti(kortti2);
        kortti3 = new Pelikortti(Maa.values()[3], 9, null);
        arvo = pleijeri.lisaaKortti(kortti3);
        assertEquals(pleijeri.getSumma(), 21);
    }

    /**
     * Test of getRaha method, of class Pelaaja.
     */
    @Test
    public void testGetRaha() {
        double expResult = 100.0;
        double result = pleijeri.getRaha();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setRaha method, of class Pelaaja.
     */
    @Test
    public void testSetRaha() {;
        double maara = 15.5;
        pleijeri.setRaha(maara);
        assertEquals(pleijeri.getRaha(), 100 + maara, 0.01);
    }

    /**
     * Test of korttienMaara method, of class Pelaaja.
     */
    @Test
    public void korttienMaaraAlussa() {
        int expResult = 0;
        int result = pleijeri.korttienMaara();
        assertEquals(expResult, result);
    }

    /**
     * Test of korttienMaara method, of class Pelaaja.
     */
    @Test
    public void korttienMaaraLisakortinJalkeen() {
        kortti = new Pelikortti(Maa.values()[1], 2, null);
        kortti2 = new Pelikortti(Maa.values()[2], 3, null);
        kortti3 = new Pelikortti(Maa.values()[3], 5, null);
        pleijeri.lisaaKortti(kortti);
        pleijeri.lisaaKortti(kortti2);
        pleijeri.lisaaKortti(kortti3);
        int expResult = 3;
        int result = pleijeri.korttienMaara();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRaha method, of class Pelaaja.
     */
    @Test
    public void rahamaaranKasvatus() {
        pleijeri.setRaha(20);
        assertEquals(pleijeri.getRaha(), 120, 0.1);
    }

    /**
     * Test of setRaha method, of class Pelaaja.
     */
    @Test
    public void rahamaaranVahennys() {
        pleijeri.setRaha(-20);
        assertEquals(pleijeri.getRaha(), 80, 0.1);
    }

}
