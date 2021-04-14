package A_star;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class metodi {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public static boolean A_star_Torre(String[][] mat,List<Cordinata> frontiera,List<Cordinata> memoria,Cordinata statoAttuale ,Cordinata statoDestinazione){
        boolean soluzione = false;
        if(!exist(memoria,statoAttuale.getAltezza(), statoAttuale.getLarghezza())){
            memoria.add(statoAttuale);
        }
        if((statoAttuale.getAltezza()-1) >= 0 && !exist(memoria,statoAttuale.getAltezza()-1, statoAttuale.getLarghezza())){//nord
            if(statoAttuale.getAltezza()-1 == statoDestinazione.getAltezza() && statoAttuale.getLarghezza() == statoDestinazione.getLarghezza()){//controlliamo che sia la casella di destinazione
                return true;
            }else{
                double val = FunzioneValutazione(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()-1][statoAttuale.getLarghezza()] = "F";
                frontiera.add(new Cordinata(statoAttuale.getAltezza()-1,statoAttuale.getLarghezza(),val));
                System.out.println(statoAttuale.getAltezza()-1 + "<-- altezza " + statoAttuale.getLarghezza() + "<-- larghezza "+ val + "<-- valore");
            }
        }
        if((statoAttuale.getAltezza()+1) < mat.length && !exist(memoria,statoAttuale.getAltezza()+1, statoAttuale.getLarghezza())){//sud
            if(statoAttuale.getAltezza()+1 == statoDestinazione.getAltezza() && statoAttuale.getLarghezza() == statoDestinazione.getLarghezza()){
                return true;
            }else{
                double val = FunzioneValutazione(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()+1][statoAttuale.getLarghezza()] = "F";
                frontiera.add(new Cordinata(statoAttuale.getAltezza()+1,statoAttuale.getLarghezza(),val));
                System.out.println(statoAttuale.getAltezza()+1 + "<-- altezza " + statoAttuale.getLarghezza() + "<-- larghezza "+ val + "<-- valore");
            }
        }
        if(statoAttuale.getLarghezza()+1 < mat[0].length && !exist(memoria,statoAttuale.getAltezza(), statoAttuale.getLarghezza()+1)){//est
            if(statoAttuale.getAltezza() == statoDestinazione.getAltezza() && statoAttuale.getLarghezza()+1 == statoDestinazione.getLarghezza()){
                return true;
            }else{
                double val = FunzioneValutazione(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()][statoAttuale.getLarghezza()+1] = "F";
                frontiera.add(new Cordinata(statoAttuale.getAltezza(),statoAttuale.getLarghezza()+1,val));
                System.out.println(statoAttuale.getAltezza()+ "<-- altezza " + (statoAttuale.getLarghezza()+1) + "<-- larghezza "+ val + "<-- valore");
            }
        }
        if(statoAttuale.getLarghezza()-1 >= 0 && !exist(memoria,statoAttuale.getAltezza()-1, statoAttuale.getLarghezza()-1)){//ovest
            if(statoAttuale.getAltezza() == statoDestinazione.getAltezza() && statoAttuale.getLarghezza()-1 == statoDestinazione.getLarghezza()){
                return true;
            }else{
                double val = FunzioneValutazione(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()][statoAttuale.getLarghezza()-1] = "F";
                frontiera.add(new Cordinata(statoAttuale.getAltezza(),statoAttuale.getLarghezza()-1,val));
                System.out.println(statoAttuale.getAltezza() + "<-- altezza " + (statoAttuale.getLarghezza()-1) + "<-- larghezza "+ val + "<-- valore");
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
        return A_star_Torre(mat,frontiera,memoria,max,statoDestinazione);


    }

    //metodo che controlla se un elemento e gia stato visitato
    private static boolean exist(List<Cordinata> memoria,double altezza, double larghezza) {
        for(Cordinata x : memoria){
            if(x.getAltezza() == altezza && x.getLarghezza() == larghezza){
                return true;
            }
        }
        return false;
    }

    //metodo che stampa la matrice di int degli oggetti casella
    public static void PrintMatrix(String[][] mat){
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

    //popola la matrice di stringe con i valori
    public static void Popola(String[][] mat,Cordinata partenza, Cordinata target){
        for (int x = 0;x<mat.length;x++){
            for (int y = 0;y<mat[x].length;y++){
                if(x == partenza.getAltezza() && y == partenza.getLarghezza()){
                    mat[x][y] = ANSI_GREEN+"P"+ANSI_RESET;
                }else if (x == target.getAltezza() && y == target.getLarghezza()){
                    mat[x][y] = ANSI_RED+"D"+ANSI_RESET;
                }else{
                    mat[x][y] = ANSI_CYAN+"S"+ANSI_RESET;
                }
            }
        }
    }
}
