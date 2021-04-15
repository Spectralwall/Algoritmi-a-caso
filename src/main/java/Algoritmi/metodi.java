package Algoritmi;
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

    //metodo che controlla se una casella e gia stata visitata
    public static boolean existMemoria(List<Cordinata> memoria, double altezza, double larghezza) {
        for(Cordinata x : memoria){
            if(x.getAltezza() == altezza && x.getLarghezza() == larghezza){
                return true;
            }
        }
        return false;
    }

    //metodo che controlla se una casella é gia nella frontiera
    public static boolean existFrontiera(List<Cordinata> frontiera, double altezza, double larghezza){
        for(Cordinata x : frontiera){
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
    public static double FunzioneValutazioneTorre(Cordinata partenza , Cordinata destinazione){
        return  DistanzaLineaAria(partenza.getAltezza(), partenza.getLarghezza()+1, destinazione.getAltezza(),
                destinazione.getLarghezza())+ EuristicaManhattan(partenza.getAltezza(), partenza.getLarghezza()+1 ,
                destinazione.getAltezza(), destinazione.getLarghezza());
    }

    //f(x) = g(x) + h(x) funzione di valutazione = funzione euristica + distanza in linea d'aria
    public static double FunzioneValutazioneRegina(Cordinata partenza , Cordinata destinazione){
        return  DistanzaLineaAria(partenza.getAltezza(), partenza.getLarghezza()+1, destinazione.getAltezza(),
                destinazione.getLarghezza())+ EuristicaCebysev(partenza.getAltezza(), partenza.getLarghezza()+1 ,
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

    //popola la matrice di stringe con le lettere per simbolegiare la posizione
    public static void Popola(String[][] mat,Cordinata partenza, Cordinata target){
        //P verde = casella di partenza
        //D gialla = target / casella destinazione
        //S azzuerri = caselle normali
        for (int x = 0;x<mat.length;x++){
            for (int y = 0;y<mat[x].length;y++){
                if(x == partenza.getAltezza() && y == partenza.getLarghezza()){
                    mat[x][y] = ANSI_GREEN+"P"+ANSI_RESET;
                }else if (x == target.getAltezza() && y == target.getLarghezza()){
                    mat[x][y] = ANSI_GREEN+"D"+ANSI_RESET;
                }else{
                    mat[x][y] = ANSI_YELLOW+"S"+ANSI_RESET;
                }
            }
        }
    }

    //funzione che controlla se si tratta dello stato obiettivo
    public static boolean TargetControl(Cordinata target, int a , int l){
        if(a == target.getAltezza() && l == target.getLarghezza()){
            return true;
        }else {
            return false;
        }
    }

    //funzione che controlla se si tratta dello stato obiettivo o del l'altro processo
    public static boolean TargetControlBidirezional(String[][] mat,Cordinata target, int a , int l){
        if(a == target.getAltezza() && l == target.getLarghezza()){
            return true;
        }else if(mat[a][l].equals("D")){
            return true;
        }else{
            return false;
        }
    }

}
