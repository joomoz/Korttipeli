package korttipeli;

/**
 * Pelaaja (Ihmispelaaja ja jakaja)
 *
 * @author Joonas Moilanen, 2016
 */
public class Pelaaja {

    private String nimi;
    private double peliraha;
    private Pelikortti[] pelikasi = new Pelikortti[10];
    private int korttienmaara;

    public Pelaaja(String nimi) {
        this.nimi = nimi;
        this.peliraha = 100;
        this.korttienmaara = 0;

        this.tyhjennaKasi();
    }
    
    public Pelaaja() {
        this("Ei nimeä");
    }

    //Tyhjenna pelaajan käsi
    public void tyhjennaKasi() {
        for (int i = 0; i < 10; i++) {
            this.pelikasi[i] = null;
            this.korttienmaara = 0;
        }
    }

    public boolean lisaaKortti(Pelikortti kortti) {
        //Lisää kortti pelaajan käteen ja kasvata kädessä olevien korttien määrää.
        this.pelikasi[this.korttienmaara] = kortti;
        this.korttienmaara++;

        return (this.getSumma() <= 21);
    }

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

    public void tulostaKasi(boolean naytaEkaKortti) {
        for (int i = 0; i < this.korttienmaara; i++) {
            if (i == 0 && !naytaEkaKortti) {
                System.out.println("Kortti vielä piilossa");
            } else {
                System.out.println(this.pelikasi[i]);
            }
        }
    }

    public double getRaha() {
        return this.peliraha;
    }

    public void setRaha(double maara) {
        this.peliraha += maara;
    }

    public int korttienMaara() {
        return this.korttienmaara;
    }

}

