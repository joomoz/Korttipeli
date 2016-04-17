
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

    Pelaaja pleijeri;
    Pelikortti kortti;

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
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setErr(null);
    }

    /**
     * Test of tyhjennaKasi method, of class Pelaaja.
     */
    @Test
    public void testTyhjennaKasi() {
        pleijeri = new Pelaaja();
        pleijeri.tyhjennaKasi();
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(pleijeri.korttienMaara(), 0);
    }

    /**
     * Test of lisaaKortti method, of class Pelaaja.
     */
    @Test
    public void testLisaaKortti() {
        pleijeri = new Pelaaja();
        kortti = new Pelikortti(Maa.values()[1], 10, null);
        int maaraEnnen = pleijeri.korttienMaara();
        boolean arvo = pleijeri.lisaaKortti(kortti);
        assertEquals(maaraEnnen + 1, pleijeri.korttienMaara());
    }

    /**
     * Test of getSumma method, of class Pelaaja.
     */
    @Test
    public void testGetSumma() {
        pleijeri = new Pelaaja();
        kortti = new Pelikortti(Maa.values()[1], 10, null);
        boolean arvo = pleijeri.lisaaKortti(kortti);
        assertEquals(pleijeri.getSumma(), 10);
    }

    /**
     * Test of tulostaKasi method, of class Pelaaja.
     */
    @Test
    public void testTulostaKasiFalse() {
        boolean naytaEkaKortti = false;
        pleijeri = new Pelaaja();
        Pelikortti kortti1 = new Pelikortti(Maa.values()[1], 2, null);
        Pelikortti kortti2 = new Pelikortti(Maa.values()[1], 3, null);
        pleijeri.lisaaKortti(kortti1);
        pleijeri.lisaaKortti(kortti2);
        pleijeri.tulostaKasi(naytaEkaKortti);
        assertEquals("Kortti vielä piilossa\r\nHertta 3\r\n", outContent.toString());
    }

    /**
     * Test of tulostaKasi method, of class Pelaaja.
     */
    @Test
    public void testTulostaKasiTrue() {
        boolean naytaEkaKortti = true;
        pleijeri = new Pelaaja();
        Pelikortti kortti1 = new Pelikortti(Maa.values()[1], 2, null);
        Pelikortti kortti2 = new Pelikortti(Maa.values()[1], 3, null);
        pleijeri.lisaaKortti(kortti1);
        pleijeri.lisaaKortti(kortti2);
        pleijeri.tulostaKasi(naytaEkaKortti);
        assertEquals("Hertta 2\r\nHertta 3\r\n", outContent.toString());
    }

    /**
     * Test of getRaha method, of class Pelaaja.
     */
    @Test
    public void testGetRaha() {
        pleijeri = new Pelaaja();
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
        pleijeri = new Pelaaja();
        pleijeri.setRaha(maara);
        assertEquals(pleijeri.getRaha(), 100 + maara, 0.01);
    }

    /**
     * Test of korttienMaara method, of class Pelaaja.
     */
    @Test
    public void korttienMaaraAlussa() {
        pleijeri = new Pelaaja();
        int expResult = 0;
        int result = pleijeri.korttienMaara();
        assertEquals(expResult, result);
    }

    /**
     * Test of korttienMaara method, of class Pelaaja.
     */
    @Test
    public void korttienMaaraLisakortinJalkeen() {
        pleijeri = new Pelaaja();
        Pelikortti kortti1 = new Pelikortti(Maa.values()[1], 2, null);
        Pelikortti kortti2 = new Pelikortti(Maa.values()[2], 3, null);
        Pelikortti kortti3 = new Pelikortti(Maa.values()[3], 5, null);
        pleijeri.lisaaKortti(kortti1);
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
        pleijeri = new Pelaaja();
        pleijeri.setRaha(20);
        assertEquals(pleijeri.getRaha(), 120, 0.1);
    }

    /**
     * Test of setRaha method, of class Pelaaja.
     */
    @Test
    public void rahamaaranVahennys() {
        pleijeri = new Pelaaja();
        pleijeri.setRaha(-20);
        assertEquals(pleijeri.getRaha(), 80, 0.1);
    }

}
