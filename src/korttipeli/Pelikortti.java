package korttipeli;

/**
 * Pelikortti
 * @author Joonas Moilanen, 2016
 */

public class Pelikortti {

    //Kortin maa
    private Maa maa;
    //Kortin numero: 1(Ässä), 2-10, 11(J), 12(Q), 13(K)
    private int numero;
    
    /**
     * Konstruktori Pelikortille
     * @param maa, kortin maa
     * @param numero, kortin arvo
     */
    public Pelikortti(Maa maa, int numero) {
        this.maa = maa;
        this.numero = numero;   
    }

    /**
     * Metodi palauttaa kortin arvon
     * @return numero
     */
    public int getNumero() {
        return this.numero;
    }

    /**
     * Metodi palauttaa kortin maan
     * @return maa
     */
    public Maa getMaa() {
        return this.maa;
    }

    /**
     * Metodi tulostukseen
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

