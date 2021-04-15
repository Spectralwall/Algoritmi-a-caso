package Algoritmi;

/*
 * classe per salvare cordinata valore
 */

public class Cordinata {
    private int altezza;
    private int larghezza;
    private double punteggio;

    public Cordinata(int a, int l, double v){
        this.altezza = a;
        this.larghezza = l;
        this.punteggio = v;
    }

    public int getAltezza() {
        return altezza;
    }

    public double getPunteggio() {
        return punteggio;
    }

    public int getLarghezza() {
        return larghezza;
    }
    
}
