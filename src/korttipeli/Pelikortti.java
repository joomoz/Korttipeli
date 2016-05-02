package korttipeli;

import java.awt.Image;

/**
 * Pelikortti
 * @author Joonas Moilanen, 2016
 */

public class Pelikortti {

    //Kortin maa
    private Maa maa;
    //Kortin numero: 1(Ässä), 2-10, 11(J), 12(Q), 13(K)
    private int numero;
    //Kortin kuva
    private Image kuva;
    
    /**
     * Konstruktori Pelikortille.
     * @param maa kortin maa
     * @param numero kortin arvo
     * @param kuva kortin kuva
     */
    public Pelikortti(Maa maa, int numero, Image kuva) {
        this.maa = maa;
        this.numero = numero;  
        this.kuva = kuva;
    }

    /**
     * Metodi palauttaa kortin arvon.
     * @return numero
     */
    public int getNumero() {
        return this.numero;
    }

    /**
     * Metodi palauttaa kortin maan.
     * @return maa
     */
    public Maa getMaa() {
        return this.maa;
    }
    
    /**
     * Metodi palauttaa kortin kuvan.
     * @return palautettava kuva
     */
    public Image getKuva() {
        return this.kuva;
    }

}

