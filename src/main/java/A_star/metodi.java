package A_star;

/*
 *  classe per metodi e altri metodi
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class metodi {

    public static boolean A_star_Torre(int[][] mat,List<Cordinata> frontiera,Cordinata statoAttuale ,Cordinata statoDestinazione){
        boolean soluzione = false;


        if((statoAttuale.getAltezza()-1) >= 0 ){//nord
            if(statoAttuale.getAltezza()-1 == statoDestinazione.getAltezza() && statoAttuale.getLarghezza() == statoDestinazione.getLarghezza()){//controlliamo che sia la casella di destinazione
                mat[statoAttuale.getAltezza()-1][statoAttuale.getLarghezza()] = 1;
                return true;
            }else{
                double val = FunzioneValutazione(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()-1][statoAttuale.getLarghezza()] = -1;
                frontiera.add(new Cordinata(statoAttuale.getAltezza()-1,statoAttuale.getLarghezza(),val));
                System.out.println(statoAttuale.getAltezza()-1 + "<-- altezza " + statoAttuale.getLarghezza() + "<-- larghezza "+ val +"<-- valore");
            }
        }
        if((statoAttuale.getAltezza()+1) < mat.length){//sud
            if(statoAttuale.getAltezza()+1 == statoDestinazione.getAltezza() && statoAttuale.getLarghezza() == statoDestinazione.getLarghezza()){
                mat[statoAttuale.getAltezza()+1][statoAttuale.getLarghezza()] = 1;
                return true;
            }else{
                double val = FunzioneValutazione(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()+1][statoAttuale.getLarghezza()] = -1;
                frontiera.add(new Cordinata(statoAttuale.getAltezza()+1,statoAttuale.getLarghezza(),val));
                System.out.println(statoAttuale.getAltezza()+1 + "<-- altezza " + statoAttuale.getLarghezza() + "<-- larghezza "+ val +"<-- valore");
            }
        }
        if(statoAttuale.getLarghezza()+1 < mat[0].length ){//est
            if(statoAttuale.getAltezza() == statoDestinazione.getAltezza() && statoAttuale.getLarghezza()+1 == statoDestinazione.getLarghezza()){
                mat[statoAttuale.getAltezza()][statoAttuale.getLarghezza()+1] = 1;
                return true;
            }else{
                double val = FunzioneValutazione(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()][statoAttuale.getLarghezza()+1] = -1;
                frontiera.add(new Cordinata(statoAttuale.getAltezza(),statoAttuale.getLarghezza()+1,val));
                System.out.println(statoAttuale.getAltezza()+ "<-- altezza " + (statoAttuale.getLarghezza()+1) + "<-- larghezza "+ val +"<-- valore");
            }
        }
        if(statoAttuale.getLarghezza()-1 >= 0 ){//ovest
            if(statoAttuale.getAltezza() == statoDestinazione.getAltezza() && statoAttuale.getLarghezza()-1 == statoDestinazione.getLarghezza()){
                mat[statoAttuale.getAltezza()][statoAttuale.getLarghezza()-1] = 1;
                return true;
            }else{
                double val = FunzioneValutazione(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()][statoAttuale.getLarghezza()-1] = -1;
                frontiera.add(new Cordinata(statoAttuale.getAltezza(),statoAttuale.getLarghezza()-1,val));
                System.out.println(statoAttuale.getAltezza() + "<-- altezza " + (statoAttuale.getLarghezza()-1) + "<-- larghezza "+ val +"<-- valore");
            }
        }
        Cordinata max = Collections.max(frontiera, new Comparator<Cordinata>() {
            @Override
            public int compare(Cordinata o1, Cordinata o2) {
                return Double.compare(o2.getPunteggio(), o1.getPunteggio());
            }
        });
        frontiera.remove(max);
        PrintMatrix(mat);
        return A_star_Torre(mat,frontiera,max,statoDestinazione);


    }

    //metodo che controlla se un elemento e stato gia aggiunto nella frontiera
    private static boolean exist(List<Cordinata> frontiera,int altezza, int larghezza) {
        for(Cordinata x : frontiera){
            if(x.getAltezza()== altezza && x.getLarghezza() == larghezza){
                return true;
            }
        }
        return false;
    }

    //metodo che stampa la matrice di int degli oggetti casella
    public static void PrintMatrix(int[][] mat){
        for (int x = 0;x<mat.length;x++){
            for (int y = 0;y<mat[x].length;y++){
                System.out.print("[ " + mat[x][y] + " ]");
            }
            System.out.println();
        }
    }

    //f(x) = g(x) + h(x) funzione di valutazione = funzione euristica + distanza in linea d'aria
    public static double FunzioneValutazione(Cordinata partenza ,Cordinata destinazione){
        return  DistanzaLineaAria(partenza.getAltezza(), partenza.getLarghezza()+1, destinazione.getAltezza(),
                destinazione.getLarghezza())+ EuristicaManhattan(partenza.getAltezza(), partenza.getLarghezza()+1 ,
                destinazione.getAltezza(), destinazione.getLarghezza());
    }

    //distanza in linea d'aria tra due punti del grafico
    public static double DistanzaLineaAria(int xa, int xl, int ya, int yl){
        return Math.sqrt(Math.pow(ya-xa,2)+Math.pow(yl-xl,2));
    }

    //euristica per il movimento in lunghezza e in larghezza (una torre degli scacchi)
    public static double EuristicaManhattan(int xa, int xl, int ya, int yl){
        return (Math.abs(xa - ya))+(Math.abs(xl - yl));

    }

    //euristica per il movimento in lunghezza,in larghezza e in obliquo (una regina/re degli scacchi)
    public static double EuristicaCebysev(int xa, int xl, int ya, int yl){
        return Math.max((Math.abs(xa - ya)),(Math.abs(xl - yl)));
    }

}
