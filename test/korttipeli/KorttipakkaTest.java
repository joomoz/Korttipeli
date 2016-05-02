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
 * KorttipakkaTest
 *
 * @author Joonas
 */
public class KorttipakkaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private Korttipakka pakka;

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
        pakka = new Korttipakka();
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setErr(null);
    }

    /**
     * Testataan, että ensimmäinen kortti vaihtuu.
     */
    @Test
    public void sekoitusVaihtaaEnsimmaisenKortin() {
        Pelikortti kortti1 = pakka.ensimmainenKortti();
        pakka.sekoitus();
        Pelikortti kortti2 = pakka.ensimmainenKortti();
        assertNotSame(kortti1, kortti2);
    }

    /**
     * Testataan korttimäärän vähentymistä.
     *
     */
    @Test
    public void otaKortti() {
        int maaraAlussa = pakka.korttienMaara();
        Pelikortti kortti = pakka.otaKortti();
        assertEquals(maaraAlussa - 1, pakka.korttienMaara());
    }

    /**
     * Testataan, että uudessa pakassa on 52 korttia.
     *
     */
    @Test
    public void korttienMaaraOnAlussa52() {
        assertEquals(pakka.korttienMaara(), 52);
    }

    /**
     * Testataan ensimmäisen kortin ottamista.
     *
     */
    @Test
    public void otaKorttiAntaaPelikortin() {
        Pelikortti kortti = pakka.otaKortti();
        assertNotNull(kortti);
    }

}
