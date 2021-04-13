package A_star;

/*
 * classe per salvare cordinata valore
 */

import java.util.Objects;

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
