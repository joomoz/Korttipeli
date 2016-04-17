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
        korttipakka = new Korttipakka();
        SwingUtilities.invokeLater(kayttoliittyma);
        kayttoliittyma.panoksenSaadin.addChangeListener(this);
        uusiPeli();
    }

    /**
     * Metodi aloittaa uuden pelikierroksen. Peli loppuu kun pelaajan rahat
     * loppuvat.
     */
    public void uusiPeli() {
        //Luo pelaajat
        ihminen = new Pelaaja();
        jakaja = new Pelaaja();

        while (ihminen.getRaha() > 0) {
            kayttoliittyma.asetaTekstinaytto("Set your bet and press deal!");
            kayttoliittyma.asetaRahanaytto("" + ihminen.getRaha());

            //Peli toimii pelaajan valintojen mukaan.

            // Tulosta lopulliset kädet
            //ihminen.tulostaKasi(true);
            //System.out.println("Summa: " + ihminen.getSumma());

            //jakaja.tulostaKasi(true);
            //System.out.println("Summa: " + jakaja.getSumma());
        }
    }

    /**
     * Metodi voiton tarkastamiseen.
     *
     * @param ihminen pelaaja
     * @param jakaja jakaja
     * @param panos kierroksen panos
     */
    private void tarkastaVoittaja() {
        int ihmisenSumma = ihminen.getSumma();
        int jakajanSumma = jakaja.getSumma();
        int ihmisenKorttienLkm = ihminen.korttienMaara();
        int jakajanKorttienLkm = jakaja.korttienMaara();

        if (ihmisenSumma > 21) {
            kayttoliittyma.asetaTekstinaytto("Jakaja voitti!");
            ihminen.setRaha(-1.0 * panos);
        } else if (ihmisenSumma == 21 && ihmisenKorttienLkm == 2 && jakajanSumma != 21) {
            kayttoliittyma.asetaTekstinaytto("Pelaaja voitti " + 1.5 * panos + " euroa!");
            ihminen.setRaha(1.5 * panos);
            //Pelaajalla ja jakajalla on molemmilla blackjack, joten pelaaja saa panoksen takaisin
        } else if (ihmisenSumma == 21 && ihmisenKorttienLkm == 2 && jakajanSumma == 21 && jakajanKorttienLkm == 2) {
            kayttoliittyma.asetaTekstinaytto("Tasapeli, pelaaja saa panoksen takaisin.");
            //ei siis muuteta pelaajan rahasummaa
            //Pelaaja voittaa normaalisti
        } else if (ihmisenSumma > jakajanSumma && ihmisenSumma <= 21 || jakajanSumma > 21 && ihmisenSumma <= 21) {
            kayttoliittyma.asetaTekstinaytto("Pelaaja voitti!");
            ihminen.setRaha(1.0 * panos);
            //Muulloin jakaja voittaa
        } else {
            kayttoliittyma.asetaTekstinaytto("Jakaja voitti!");
            ihminen.setRaha(-1.0 * panos);
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
            case "DEAL":
                if (!tarkastaPanos(panos)) {
                    return;
                }
                jaaKortit();
                break;
            case "HIT":
                otaLisaa();
                break;
            case "STAND":
                jakajaOttaaKortit();
                break;
            case "EXIT":
                System.exit(0);
        }
        //kayttoliittyma.setEnableButton(toiminto);
    }

    /**
     * Panoksen muuttaminen sliderilla.
     *
     * @param e
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            double luku = (source.getValue());
            if (tarkastaPanos(luku)) {
                panos = (luku < 10) ? ihminen.getRaha() : luku;
                kayttoliittyma.asetaPanosnaytto("" + panos);
            }
        }
    }

    /**
     * Muuttaa panoksen sliderin mukaan. Jos rahaa on alle 10, asetetaan loput
     * rahat peliin.
     *
     * @param e
     */
    public void muutaPanos(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            double luku = (source.getValue());
            if (tarkastaPanos(luku)) {
                panos = (luku < 10) ? ihminen.getRaha() : luku;
                kayttoliittyma.asetaPanosnaytto("" + panos);
            }
        }
    }

    /**
     * Tarkastaa onko pelaajalla vähintään panoksen verran rahaa. Alle 10 rahan
     * tilanteessa kaikki menee peliin automaattisesti.
     * @param panos
     * @return oliko tarpeeksi rahaa panokseen nähden
     */
    public boolean tarkastaPanos(double panos) {
        if (panos > ihminen.getRaha() && ihminen.getRaha() >= 10) {
            kayttoliittyma.asetaTekstinaytto("Place smaller bet.");
            return false;
        }
        return true;
    }

    /**
     * Joka kerta pakka alustetaan, koska ei haluta pelata vajaalla pakalla ja
     * molempien pelaajien edellisen kierroksen kortit kerätään pois.
     */
    private void jaaKortit() {
        korttipakka = new Korttipakka();
        ihminen.tyhjennaKasi();
        jakaja.tyhjennaKasi();

        //Aluksi molemmille jaetaan kaksi korttia.
        ihminen.lisaaKortti(korttipakka.otaKortti());
        jakaja.lisaaKortti(korttipakka.otaKortti());
        ihminen.lisaaKortti(korttipakka.otaKortti());
        jakaja.lisaaKortti(korttipakka.otaKortti());
        //Näytetään kortit käyttöliittymässä
        //TODO
        //ihminen.tulostaKasi(true);
        //jakaja.tulostaKasi(false);
    }

    private void otaLisaa() {
        kayttoliittyma.asetaTekstinaytto("HIT OR STAND!");
        boolean menikoYli = !ihminen.lisaaKortti(korttipakka.otaKortti());
        //Näytä uusi kortti käyttöliittymässä
        //TODO
        if (menikoYli) {
            tarkastaVoittaja();
        }
    }

    /**
     * Jakaja ottaa lisää kunnes summa on vähintään 17.
     */
    private void jakajaOttaaKortit() {
        boolean menikoYli;
        while (jakaja.getSumma() < 17) {
            menikoYli = !jakaja.lisaaKortti(korttipakka.otaKortti());
            jakaja.tulostaKasi(true);
        }
        //System.out.println("Jakaja jää tähän.\n");
    }

}
