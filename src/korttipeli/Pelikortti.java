/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korttipeli;

/**
 * Pelikortti
 * 
 * @author Joonas Moilanen, 2016
 */

public class Pelikortti {

    //Kortin maa
    private Maa maa;
    //Kortin numero: 1(Ässä), 2-10, 11(J), 12(Q), 13(K)
    private int numero;
    
    //Konstruktori
    public Pelikortti(Maa maa, int numero) {
        this.maa = maa;
        this.numero = numero;   
    }

    public int getNumero() {
        return this.numero;
    }

    public Maa getMaa() {
        return this.maa;
    }

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

