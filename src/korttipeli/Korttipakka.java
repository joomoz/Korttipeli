package korttipeli;

import java.util.Random;

/**
 * Korttipakka
 * @author Joonas Moilanen, 2016
 */
public class Korttipakka {

    //Kaikki pakan kortit, ensimmäinen indeksi on pakan ylin kortti.
    private Pelikortti[] pakanKortit;
    
    //Pakassa olevien korttien lukumäärä.
    private int korttienmaara;

    /**
     * Konstruktori joka luo yhden pakallisen kortteja ja sekoittaa ne.
     */
    public Korttipakka() {
        this.korttienmaara = 52;
        this.pakanKortit = new Pelikortti[this.korttienmaara];

        int kortinPaikka = 0;
        //Luo kaikki kortit
        for (int m = 0; m < 4; m++) {
            for (int n = 1; n < 14; n++) {
                this.pakanKortit[kortinPaikka] = new Pelikortti(Maa.values()[m], n);
                kortinPaikka++;
            }
        }
        this.sekoitus();

    }

    /**
     * Metodi sekoittaa pakan satunnaisesti vaihtamalla kahden kortin paikkaa.
     */
    public void sekoitus() {
        Random satunnainenNumero = new Random();
        Pelikortti alkuperainenKortti;

        //Vaihda kahden kortin paikkaa
        for (int i = 0; i < this.korttienmaara; i++) {
            // Arvo satunnainen kortin paikka
            int j = satunnainenNumero.nextInt(this.korttienmaara);
            //Vaihda korttien paikkaa
            alkuperainenKortti = pakanKortit[i];
            pakanKortit[i] = pakanKortit[j];
            pakanKortit[j] = alkuperainenKortti;
        }
    }

    /**
     * Metodi ottaa pakasta päällimmäisen kortin.
     * @return otettu kortti
     */
    public Pelikortti otaKortti() {
        Pelikortti ekaKortti = this.pakanKortit[0];
        //Siirrää muut kortit yhden indeksin ylemmäs
        for (int i = 1; i < this.korttienmaara; i++) {
            this.pakanKortit[i - 1] = this.pakanKortit[i];
        }
        // Poista viimeinen kortti;
        this.pakanKortit[this.korttienmaara - 1] = null;

        // Nyt pakassa on yksi kortti vähemmän
        this.korttienmaara--;

        return ekaKortti;
    }
    
    /**
     * Metodi palauttaa testitarkoituksiin pakan ensimmäisen kortin.
     * @return Pelikortti.
     */
    public Pelikortti ensimmainenKortti() {
        return this.pakanKortit[0];
    }
    
    /**
     * Metodi kertoo pakassa olevien korttien maaran.
     * @return korttien maara
     */
    public int korttienMaara() {
        return this.korttienmaara;
    }
            
            
}

