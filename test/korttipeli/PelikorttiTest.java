
package korttipeli;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * PelikorttiTest
 * @author Joonas
 */
public class PelikorttiTest {

    private Pelikortti testikortti;
    
    public PelikorttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Testataan että kortti luodaan oikein.
     */
    @Test
    public void korttiLuodaanOikein() {
        testikortti = new Pelikortti(Maa.values()[3], 10, null);
        assertEquals(Maa.values()[3], testikortti.getMaa());
        assertEquals(testikortti.getNumero(), 10);
    }
    
}
