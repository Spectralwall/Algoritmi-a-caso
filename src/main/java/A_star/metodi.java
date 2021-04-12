package A_star;

/*
 *  classe per metodi e altri metodi
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class metodi {

    public static boolean A_star_Torre(int[][] mat,List<Cordinata> frontiera,List<Cordinata>memoria,Cordinata partenza ,Cordinata destinazione){
        boolean soluzione = false;
        if(!memoria.contains(partenza)){
            memoria.add(partenza);
        }
        if((partenza.getAltezza()-1) >= 0 ){//nord
            if(partenza.getAltezza()-1 == destinazione.getAltezza() && partenza.getLarghezza() == destinazione.getLarghezza()){//controlliamo che sia la casella di destinazione
                mat[partenza.getAltezza()-1][partenza.getLarghezza()] = 1;
                return true;
            }else{
                int val = FunzioneValutazione(partenza, destinazione);
                mat[partenza.getAltezza()-1][partenza.getLarghezza()] = -1;
                frontiera.add(new Cordinata(partenza.getAltezza()-1,partenza.getLarghezza(),val));
                System.out.println(partenza.getAltezza()-1 + "<-- altezza " + partenza.getLarghezza() + "<-- larghezza "+ val +"<-- valore");
            }
        }
        if((partenza.getAltezza()+1) < mat.length){//sud
            if(partenza.getAltezza()+1 == destinazione.getAltezza() && partenza.getLarghezza() == destinazione.getLarghezza()){
                mat[partenza.getAltezza()+1][partenza.getLarghezza()] = 1;
                return true;
            }else{
                int val = FunzioneValutazione(partenza, destinazione);
                frontiera.add(new Cordinata(partenza.getAltezza()+1,partenza.getLarghezza(),val));
                System.out.println(partenza.getAltezza()+1 + "<-- altezza " + partenza.getLarghezza() + "<-- larghezza "+ val +"<-- valore");
            }
        }
        if(partenza.getLarghezza()+1 < mat[0].length){//est
            if(partenza.getAltezza() == destinazione.getAltezza() && partenza.getLarghezza()+1 == destinazione.getLarghezza()){
                mat[partenza.getAltezza()][partenza.getLarghezza()+1] = 1;
                return true;
            }else{
                int val = FunzioneValutazione(partenza, destinazione);
                mat[partenza.getAltezza()][partenza.getLarghezza()+1] = -1;
                frontiera.add(new Cordinata(partenza.getAltezza(),partenza.getLarghezza()+1,val));
                System.out.println(partenza.getAltezza()+ "<-- altezza " + (partenza.getLarghezza()+1) + "<-- larghezza "+ val +"<-- valore");
            }
        }
        if(partenza.getLarghezza()-1 >= 0){//ovest
            if(partenza.getAltezza() == destinazione.getAltezza() && partenza.getLarghezza()-1 == destinazione.getLarghezza()){
                mat[partenza.getAltezza()][partenza.getLarghezza()-1] = 1;
                return true;
            }else{
                int val = FunzioneValutazione(partenza, destinazione);
                mat[partenza.getAltezza()][partenza.getLarghezza()-1] = -1;
                frontiera.add(new Cordinata(partenza.getAltezza(),partenza.getLarghezza()-1,val));
                System.out.println(partenza.getAltezza() + "<-- altezza " + (partenza.getLarghezza()-1) + "<-- larghezza "+ val +"<-- valore");
            }
        }
        Cordinata max = Collections.max(frontiera, new Comparator<Cordinata>() {
            @Override
            public int compare(Cordinata o1, Cordinata o2) {
                return Integer.compare(o2.getPunteggio(), o1.getPunteggio());
            }
        });
        frontiera.remove(max);
        PrintMatrix(mat);
        return A_star_Torre(mat,frontiera,memoria,max,destinazione);


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

    public static int FunzioneValutazione(Cordinata partenza ,Cordinata destinazione){
        return  DistanzaLineaAria(partenza.getAltezza(), partenza.getLarghezza()+1, destinazione.getAltezza(),
                destinazione.getLarghezza())+EuristicaManhattan(partenza.getAltezza(), partenza.getLarghezza()+1 ,
                destinazione.getAltezza(), destinazione.getLarghezza());
    }

    //distanza in linea d'aria tra due punti del grafico
    public static int DistanzaLineaAria(int xa, int xl, int ya, int yl){
        return (int) Math.sqrt(Math.pow(ya-xa,2)+Math.pow(yl-xl,2));
    }

    //euristica per il movimento in lunghezza e in larghezza (una torre degli scacchi)
    public static int EuristicaManhattan(int xa, int xl, int ya, int yl){
        return (Math.abs(xa - ya))+(Math.abs(xl - yl));

    }

    //euristica per il movimento in lunghezza,in larghezza e in obliquo (una regina/re degli scacchi)
    public static int EuristicaCebysev(int xa, int xl, int ya, int yl){
        return Math.max((Math.abs(xa - ya)),(Math.abs(xl - yl)));
    }

}
