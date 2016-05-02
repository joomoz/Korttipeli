package korttipeli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Blackjack
 *
 * @author Joonas Moilanen, 2016
 */
public class Blackjack implements ActionListener, ChangeListener {
    
    private Kayttoliittyma kayttoliittyma;
    private Korttipakka korttipakka;
    protected Pelaaja ihminen;
    protected Pelaaja jakaja;
    private double panos;
    
    public Blackjack() {
        kayttoliittyma = new Kayttoliittyma(this);
        SwingUtilities.invokeLater(kayttoliittyma);
        kayttoliittyma.panoksenSaadin.addChangeListener(Blackjack.this);
        uusiPeli();
    }

    /**
     * Metodi aloittaa uuden pelikierroksen. Peli loppuu kun pelaajan rahat
     * loppuvat.
     */
    public void uusiPeli() {
        korttipakka = new Korttipakka();
        ihminen = new Pelaaja("ihminen");
        jakaja = new Pelaaja("jakaja");
        panos = 20;
        
        kayttoliittyma.asetaNappuloidenTila("jää");
        kayttoliittyma.asetaTekstinaytto("Aseta panos ja paina jakoa!");
        paivitaTekstit();
    }

    /**
     * Metodi tarkastaa voiton ja päivittää tekstinäytön sekä tarkastaa jos
     * rahaa on alle 10.
     */
    public void tarkastaVoittaja() {
        double voitto = tarkastaVoittosumma();
        ihminen.setRaha(voitto);
        
        if (Math.abs(voitto) <= 0.001) {
            kayttoliittyma.asetaTekstinaytto("Jakaja voitti! Aseta panos ja paina jakoa.");
        } else if (Math.abs(voitto) <= panos * 1.001) {
            kayttoliittyma.asetaTekstinaytto("Tasapeli, pelaaja saa rahat takaisin. Aseta panos ja paina jakoa.");
        } else {
            kayttoliittyma.asetaTekstinaytto("Pelaaja voitti " + voitto + " euroa! Aseta panos ja paina jakoa.");
        }
        
        paivitaTekstit();
        kayttoliittyma.asetaNappuloidenTila("jää");

        //Jos rahaa jää alle 10 euroa, lukitaan panossäädin ja laitetaan loput rahat peliin.
        if (ihminen.getRaha() < 10) {
            rahatLopussa();
        }
    }

    /**
     * Tarkastaa paljonko pelaaja voitti, jos voitti.
     *
     * @return voittosumma
     */
    public double tarkastaVoittosumma() {
        int ihSumma = ihminen.getSumma();
        int jaSumma = jakaja.getSumma();
        int ihKorttienLkm = ihminen.korttienMaara();
        int jaKorttienLkm = jakaja.korttienMaara();
        double voitto;

        //Pelaajan käden summa menee yli 21.
        if (ihSumma > 21) {
            voitto = 0;
            //Pelaaja voittaa Blackjackillä. Jos molemmilla 21, pelaaja voittaa koska hänellä on vain 2 korttia.
        } else if (ihSumma == 21 && ihKorttienLkm == 2 && jaSumma != 21 || ihSumma == 21 && ihKorttienLkm == 2 && jaKorttienLkm != 2) {
            voitto = 2.5 * panos;
            //Pelaajalla ja jakajalla on molemmilla blackjack, joten pelaaja saa panoksen takaisin
        } else if (ihSumma == 21 && ihKorttienLkm == 2 && jaSumma == 21 && jaKorttienLkm == 2) {
            voitto = 1.0 * panos;
            //Pelaaja voittaa normaalisti
        } else if (ihSumma > jaSumma && ihSumma <= 21 || jaSumma > 21 && ihSumma <= 21) {
            voitto = 2.0 * panos;
            //Muulloin jakaja voittaa
        } else {
            voitto = 0;
        }
        return voitto;
    }

    /**
     * Toimii sen mukaan mitä nappia käyttöliittymässä painetaan.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String toiminto = e.getActionCommand();
        switch (toiminto) {
            case "jako":
                if (!tarkastaPanos(panos)) {
                    return;
                }
                jaaKortit();
                break;
            case "lisää":
                otaLisaa();
                break;
            case "jää":
                jakajaOttaaKortit();
                break;
            case "exit":
                System.exit(0);
        }
    }

    /**
     * Muuttaa panoksen sliderin mukaan. Jos rahaa on alle 10, asetetaan loput
     * rahat peliin.
     *
     * @param e
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            double luku = (source.getValue());
            if (tarkastaPanos(luku)) {
                panos = luku;
            } else if (ihminen.getRaha() < 10) {
                panos = ihminen.getRaha();
            }
            paivitaTekstit();
        }
    }

    /**
     * Tarkastaa onko pelaajalla vähintään panoksen verran rahaa.
     *
     * @param panos panoksen määrä
     * @return oliko tarpeeksi rahaa panokseen nähden
     */
    private boolean tarkastaPanos(double panos) {
        if (panos > ihminen.getRaha() && ihminen.getRaha() >= 10) {
            kayttoliittyma.asetaNappuloidenTila("panos");
            kayttoliittyma.asetaTekstinaytto("Aseta pienempi panos.");
            return false;
        } else {
            kayttoliittyma.asetaNappuloidenTila("jää");
            kayttoliittyma.asetaTekstinaytto("Aseta panos ja paina jakoa!");
            return true;
        }
    }

    /**
     * Joka kerta pakka alustetaan, koska ei haluta pelata vajaalla pakalla ja
     * molempien pelaajien edellisen kierroksen kortit kerätään pois.
     */
    private void jaaKortit() {
        korttipakka = new Korttipakka();
        ihminen.tyhjennaKasi();
        jakaja.tyhjennaKasi();
        kayttoliittyma.tyhjenna();
        ihminen.setRaha(-panos);
        
        Pelikortti kortti;
        //Aluksi molemmille jaetaan kaksi korttia.
        for (int k = 0; k < 4; k++) {
            kortti = korttipakka.otaKortti();
            if (k % 2 == 0) {
                ihminen.lisaaKortti(kortti);
                kayttoliittyma.piirraKortti(kortti, ihminen);
            } else {
                jakaja.lisaaKortti(kortti);
                kayttoliittyma.piirraKortti(kortti, jakaja);
            }
        }
        kayttoliittyma.asetaTekstinaytto("Ota lisää tai jää tähän!");
        kayttoliittyma.asetaNappuloidenTila("jako");
        paivitaTekstit();
    }

    /**
     * Pelaajan lisäkorttien otto. Jos summa menee yli 21, jatkaa peli suoraan
     * voittajan tarkistukseen.
     */
    private void otaLisaa() {
        Pelikortti kortti = korttipakka.otaKortti();
        boolean alle21 = ihminen.lisaaKortti(kortti);
        kayttoliittyma.piirraKortti(kortti, ihminen);
        if (!alle21) {
            kayttoliittyma.asetaNappuloidenTila("jää");
            jakajaOttaaKortit();
            tarkastaVoittaja();
        } else {
            kayttoliittyma.asetaTekstinaytto("Ota lisää tai jää tähän!");
            paivitaTekstit();
        }
    }

    /**
     * Jakaja ottaa lisää kunnes summa on vähintään 17.
     */
    private void jakajaOttaaKortit() {
        kayttoliittyma.naytaJakajanEkaKortti(jakaja);
        boolean menikoYli;
        while (jakaja.getSumma() < 17) {
            Pelikortti kortti = korttipakka.otaKortti();
            menikoYli = jakaja.lisaaKortti(kortti);
            kayttoliittyma.piirraKortti(kortti, jakaja);
        }
        tarkastaVoittaja();
        paivitaTekstit();
    }

    /**
     * Hoitaa pelin lopun kun rahaa on alle 10 euroa.
     */
    private void rahatLopussa() {
        kayttoliittyma.asetaNappuloidenTila("vainjako");
        panos = ihminen.getRaha();
        paivitaTekstit();
        if (ihminen.getRaha() == 0) {
            kayttoliittyma.asetaNappuloidenTila("loppu");
            kayttoliittyma.asetaTekstinaytto("Rahasi loppuivat ja sinut heitettiin ulos pöydästä!");
        }
    }

    /**
     * Päivittää kaikki tekstinäytöt.
     */
    private void paivitaTekstit() {
        kayttoliittyma.asetaRahanaytto("" + ihminen.getRaha());
        kayttoliittyma.asetaPanosnaytto("" + panos);
        kayttoliittyma.asetaPelaajanKadenSumma("" + ihminen.getSumma());
        kayttoliittyma.asetaJakajanKadenSumma("" + jakaja.getSumma());
    }

    /**
     * Luo pelin
     *
     * @param args
     */
    public static void main(String[] args) {
        Blackjack peli = new Blackjack();
    }
    
}
