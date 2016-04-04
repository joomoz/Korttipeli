
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
public class PelikorttiTest {

    Pelikortti testikortti;
    
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

    @Test
    public void testLuoKortti() {
        testikortti = new Pelikortti(Maa.values()[3], 10);
        assertEquals(Maa.values()[3], testikortti.getMaa());
        assertEquals(testikortti.getNumero(), 10);
    }
}
