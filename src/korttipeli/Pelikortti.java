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

    /**
     * Metodi tulostukseen.
     * @return tulostus String
     */
    @Override
    public String toString() {
        if(this.numero == 11) {
            return this.maa + " Jätkä";  
        }
        if(this.numero == 12) {
            return this.maa + " Rouva";  
        }
        if(this.numero == 13) {
            return this.maa + " Kuningas";  
        }
        if(this.numero == 1) {
            return this.maa + " Ässä";  
        } else
        return this.maa + " " + this.numero;    
    }

}

