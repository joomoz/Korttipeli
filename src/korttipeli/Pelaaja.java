package korttipeli;

/**
 * Pelaaja (Ihmispelaaja tai jakaja)
 * @author Joonas Moilanen, 2016
 */
public class Pelaaja {

    private double peliraha;
    private Pelikortti[] pelikasi = new Pelikortti[10];
    private int korttienmaara;

    /**
     * Konstruktori Pelaajalle.
     */
    public Pelaaja() {
        this.peliraha = 100;
        this.korttienmaara = 0;
        this.pelikasi = new Pelikortti[10];
    }

    /**
     * Metodi tyhjentää pelaajan käden.
     */
    public void tyhjennaKasi() {
        for (int i = 0; i < 10; i++) {
            this.pelikasi[i] = null;
            this.korttienmaara = 0;
        }
    }

    /**
     * Metodi lisää kortin pelaajan käteen ja kasvattaa kädessä olevien korttien määrää. 
     * @param kortti: käteen lisättävä kortti
     * @return true, jos käden summa on korkeintaan 21
     */
    public boolean lisaaKortti(Pelikortti kortti) {
        this.pelikasi[this.korttienmaara] = kortti;
        this.korttienmaara++;

        return (this.getSumma() <= 21);
    }

    /**
     * Metodi laskee käden arvon.
     * @return käden arvo
     */
    public int getSumma() {
        int summa = 0;
        int kortinArvo;
        //Ässien määrä
        int assienMaara = 0;

        for (int i = 0; i < this.korttienmaara; i++) {
            kortinArvo = this.pelikasi[i].getNumero();
            if (kortinArvo == 1) {
                assienMaara++;
                summa += 11;
            } else if (kortinArvo > 10) {
                summa += 10;
            } else {
                summa += kortinArvo;
            }

            //Jos kädessä on ässiä ja summa on yli 21, muutetaan Ässien arvot tarvittaessa ykköseksi.
            while (assienMaara > 0 && summa > 21) {
                summa -= 10;
                assienMaara--;
            }
        }
        return summa;
    }

    /**
     * Metodi tulostaa kädessä olevat kortit.
     * @param naytaEkaKortti tarvittaessa ei näytä kortin arvoa
     */
    public void tulostaKasi(boolean naytaEkaKortti) {
        for (int i = 0; i < this.korttienmaara; i++) {
            if (i == 0 && !naytaEkaKortti) {
                System.out.println("Kortti vielä piilossa");
            } else {
                System.out.println(this.pelikasi[i]);
            }
        }
    }

    /**
     * Metodi palauttaa pelaajan rahamäärän.
     * @return peliraha
     */
    public double getRaha() {
        return this.peliraha;
    }

    /**
     * Metodi muuttaa pelaajan rahamäärää.
     * @param maara rahamäärän muutos
     */
    public void setRaha(double maara) {
        this.peliraha += maara;
    }

    /**
     * Metodi kertoo kädessä olevien korttien määrän.
     * @return korttienmäärä
     */
    public int korttienMaara() {
        return this.korttienmaara;
    }

}

