package korttipeli;

import java.util.Scanner;

/**
 * Blackjack
 * 
 * @author Joonas Moilanen, 2016
 */

public class Blackjack {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Tervetuloa pelaamaan Blackjackia!");
        //Luodaan uusi sekoitettu korttipakka
        Korttipakka korttipakka;
        int panos = 1;

        //Luo pelaaja
        Pelaaja ihminen = new Pelaaja("Joonas");
        Pelaaja jakaja = new Pelaaja("Jakaja");

        /**
         * Tästä alkaa itse peli.
         * Peli loppuu kun pelaajan rahat loppuvat.
         */
        while (ihminen.getRaha() > 0) {
            System.out.println("** Uusi pelikierros **");
            //Kerrotaan pelaajan rahatilanne
            System.out.println("Pelaajalla on " + ihminen.getRaha() + " euroa.");

            /**
             * Panoksen asetus.
             */
            System.out.println("Kerro panoksesi (int): ");
            while (true) {
                panos = Integer.parseInt(lukija.nextLine());
                if (panos > ihminen.getRaha() || panos < 1) {
                    System.out.println("Panos on alle 1 tai sinulla ei ole tarpeeksi rahaa! Aseta uusi panos.");
                } else {
                    break;
                }
            }
            /**
             * Joka kerta pakka alustetaan, koska ei haluta pelata vajaalla
             * pakalla ja molempien pelaajien edellisen kierroksen kortit
             * kerätään pois.
             */
            korttipakka = new Korttipakka();
            ihminen.tyhjennaKasi();
            jakaja.tyhjennaKasi();

            /**
             * Aluksi molemmille jaetaan kaksi korttia.
             */
            ihminen.lisaaKortti(korttipakka.otaKortti());
            jakaja.lisaaKortti(korttipakka.otaKortti());
            ihminen.lisaaKortti(korttipakka.otaKortti());
            jakaja.lisaaKortti(korttipakka.otaKortti());
            System.out.println("Alkutilanne:");
            System.out.println("Pelaajan käsi:");
            ihminen.tulostaKasi(true);
            System.out.println("Jakajan käsi:");
            jakaja.tulostaKasi(false);

            boolean ihminenValmis = false;
            boolean jakajaValmis = false;
            String vastaus;

            //Lisäkorttien ottaminen
            while (!ihminenValmis || !jakajaValmis) {
                //Ihminen ensin
                while (!ihminenValmis) {
                    System.out.println("Otatko lisää vai jäätkö tähän? (L/J)");
                    vastaus = lukija.nextLine();
                    if (vastaus.equalsIgnoreCase("L")) {
                        ihminenValmis = !ihminen.lisaaKortti(korttipakka.otaKortti());
                        ihminen.tulostaKasi(true);
                    } else { //if (vastaus.equalsIgnoreCase("J"))
                        System.out.println("Pelaaja valmis!\n");
                        ihminenValmis = true;
                    }
                }
                //Jakajan vuoro
                while (!jakajaValmis) {
                    System.out.println("Jakaja ottaa lisää kunnes summa on vähintään 17.");
                    while (jakaja.getSumma() < 17) {
                        System.out.println("Jakaja ottaa lisäkortin.");
                        jakajaValmis = !jakaja.lisaaKortti(korttipakka.otaKortti());
                        jakaja.tulostaKasi(true);
                    }
                    System.out.println("Jakaja jää tähän.\n");
                    jakajaValmis = true;
                }

                // Tulosta lopulliset kädet
                System.out.println("* * * * * *");
                System.out.println("Lopulliset kädet:");
                System.out.println("Pelaaja:");
                ihminen.tulostaKasi(true);
                System.out.println("Summa: " + ihminen.getSumma());

                System.out.println("* * * * * *");
                System.out.println("Jakaja:");
                jakaja.tulostaKasi(true);
                System.out.println("Summa: " + jakaja.getSumma());

                voittikoPelaaja(ihminen, jakaja, panos);
            }
        }

        System.out.println("Rahasi loppuivat ja sinut heitettiin ulos pöydästä.");
    }

    /**
     * Metodi voiton tarkastamiseen.
     * @param ihminen pelaaja
     * @param jakaja jakaja
     * @param panos kierroksen panos
     */
    private static void voittikoPelaaja(Pelaaja ihminen, Pelaaja jakaja, int panos) {
        int ihmisenSumma = ihminen.getSumma();
        int jakajanSumma = jakaja.getSumma();
        int ihmisenKorttienLkm = ihminen.korttienMaara();
        int jakajanKorttienLkm = jakaja.korttienMaara();

        //Ihminen voittaa Black Jackilla
        if (ihmisenSumma > 21) {
            System.out.println("Jakaja voitti!");
            ihminen.setRaha(-1.0 * panos);
        } else if (ihmisenSumma == 21 && ihmisenKorttienLkm == 2 && jakajanSumma != 21) {
            System.out.println("Pelaaja voitti " + 1.5 * panos + " euroa!");
            ihminen.setRaha(1.5 * panos);
            //Pelaajalla ja jakajalla on molemmilla blackjack, joten pelaaja saa panoksen takaisin
        } else if (ihmisenSumma == 21 && ihmisenKorttienLkm == 2 && jakajanSumma == 21 && jakajanKorttienLkm == 2) {
            System.out.println("Tasapeli, pelaaja saa panoksen takaisin.");
            // ei siis muuteta pelaajan rahasummaa
            //Pelaaja voittaa normaalisti
        } else if (ihmisenSumma > jakajanSumma && ihmisenSumma <= 21 || jakajanSumma > 21 && ihmisenSumma <= 21) {
            System.out.println("Pelaaja voitti!");
            ihminen.setRaha(1.0 * panos);
            //Jakaja voittaa
        } else {
            System.out.println("Jakaja voitti!");
            ihminen.setRaha(-1.0 * panos);
        }

    }
}
