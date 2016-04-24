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
    private Pelaaja ihminen;
    private Pelaaja jakaja;
    private double panos;

    public Blackjack() {
        kayttoliittyma = new Kayttoliittyma(this);
        SwingUtilities.invokeLater(kayttoliittyma);
        kayttoliittyma.panoksenSaadin.addChangeListener(this);
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

        kayttoliittyma.asetaTekstinaytto("Aseta panos ja paina jakoa!");
        kayttoliittyma.asetaRahanaytto("" + ihminen.getRaha());
        kayttoliittyma.asetaPanosnaytto("" + panos);
    }

    /**
     * Metodi voiton tarkastamiseen.
     */
    private void tarkastaVoittaja() {
        int ihmisenSumma = ihminen.getSumma();
        int jakajanSumma = jakaja.getSumma();
        int ihmisenKorttienLkm = ihminen.korttienMaara();
        int jakajanKorttienLkm = jakaja.korttienMaara();

        if (ihmisenSumma > 21) {
            kayttoliittyma.asetaTekstinaytto("Jakaja voitti! Aseta panos ja paina jakoa.");
        } else if (ihmisenSumma == 21 && ihmisenKorttienLkm == 2 && jakajanSumma != 21) {
            kayttoliittyma.asetaTekstinaytto("Pelaaja voitti " + 1.5 * panos + " euroa! Aseta panos ja paina jakoa.");
            ihminen.setRaha(1.5 * panos);
            //Pelaajalla ja jakajalla on molemmilla blackjack, joten pelaaja saa panoksen takaisin
        } else if (ihmisenSumma == 21 && ihmisenKorttienLkm == 2 && jakajanSumma == 21 && jakajanKorttienLkm == 2) {
            kayttoliittyma.asetaTekstinaytto("Tasapeli, pelaaja saa rahat takaisin. Aseta panos ja paina jakoa.");
            //ei siis muuteta pelaajan rahasummaa
            //Pelaaja voittaa normaalisti
        } else if (ihmisenSumma > jakajanSumma && ihmisenSumma <= 21 || jakajanSumma > 21 && ihmisenSumma <= 21) {
            kayttoliittyma.asetaTekstinaytto("Pelaaja voitti " + 1.5 * panos + " euroa! Aseta panos ja paina jakoa.");
            ihminen.setRaha(1.5 * panos);
            //Muulloin jakaja voittaa
        } else {
            kayttoliittyma.asetaTekstinaytto("Jakaja voitti! Aseta panos ja paina jakoa.");
        }
        
        kayttoliittyma.asetaRahanaytto("" + ihminen.getRaha());
        kayttoliittyma.asetaNappuloidenTila("JÄÄ");
        
        //Jos rahaa jää alle 10 euroa, lukitaan panossäädin ja laitetaan loput rahat peliin.
        if (ihminen.getRaha() < 10) {
            rahatLopussa(); 
        }
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
            case "JAKO":
                if (!tarkastaPanos(panos)) {
                    return;
                }
                jaaKortit();
                break;
            case "LISÄÄ":
                otaLisaa();
                break;
            case "JÄÄ":
                kayttoliittyma.naytaJakajanEkaKortti(jakaja);
                jakajaOttaaKortit();
                break;
            case "EXIT":
                System.exit(0);
        }
        kayttoliittyma.asetaNappuloidenTila(toiminto);
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
            kayttoliittyma.asetaPanosnaytto("" + panos);
        }
    }

    /**
     * Tarkastaa onko pelaajalla vähintään panoksen verran rahaa.
     *
     * @param panos panoksen määrä
     * @return oliko tarpeeksi rahaa panokseen nähden
     */
    public boolean tarkastaPanos(double panos) {
        if (panos > ihminen.getRaha() && ihminen.getRaha() >= 10) {
            kayttoliittyma.asetaTekstinaytto("Aseta pienempi panos.");
            kayttoliittyma.asetaNappuloidenTila("PANOS");
            return false;
        } else {
            kayttoliittyma.asetaNappuloidenTila("JÄÄ");
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
        kayttoliittyma.asetaRahanaytto("" + ihminen.getRaha());

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
    }

    private void otaLisaa() {
        Pelikortti kortti = korttipakka.otaKortti();
        boolean alle21 = ihminen.lisaaKortti(kortti);
        kayttoliittyma.piirraKortti(kortti, ihminen);
        if (!alle21) {
            jakajaOttaaKortit();
            tarkastaVoittaja();
            kayttoliittyma.asetaNappuloidenTila("JÄÄ");
        } else {
            kayttoliittyma.asetaTekstinaytto("Ota lisää tai jää tähän!");
        }
    }

    /**
     * Jakaja ottaa lisää kunnes summa on vähintään 17.
     */
    private void jakajaOttaaKortit() {
        boolean menikoYli;
        while (jakaja.getSumma() < 17) {
            Pelikortti kortti = korttipakka.otaKortti();
            menikoYli = jakaja.lisaaKortti(kortti);
            kayttoliittyma.piirraKortti(kortti, jakaja);
        }
        tarkastaVoittaja();
    }

    /**
     * Hoitaa pelin lopun kun rahaa on alle 10 euroa.
     */
    private void rahatLopussa() {
        kayttoliittyma.asetaNappuloidenTila("VAINJAKO");
        panos = ihminen.getRaha();
        kayttoliittyma.asetaPanosnaytto("" + panos);
        if(ihminen.getRaha()==0) {
            kayttoliittyma.asetaNappuloidenTila("LOPPU");
            kayttoliittyma.asetaTekstinaytto("Rahasi loppuivat ja sinut heitettiin ulos pöydästä!");
        }
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
