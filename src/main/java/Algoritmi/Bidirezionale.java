package Algoritmi;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Bidirezionale{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //algorimo A* che per il movimento come una torre ovvero solo su/giu/destra/sinistra
    public static boolean A_star_Regina_Bidirezionale(String[][] mat, List<Cordinata> frontiera, List<Cordinata> memoria, Cordinata statoAttuale , Cordinata statoDestinazione){
        if(!metodi.existMemoria(memoria,statoAttuale.getAltezza(), statoAttuale.getLarghezza())){
            memoria.add(statoAttuale);
            //segniamo che la casella è stata esplorata
            synchronized (mat){
                mat[statoAttuale.getAltezza()][statoAttuale.getLarghezza()]= ANSI_RED+"E"+ANSI_RESET;
            }
        }
        if((statoAttuale.getAltezza()-1) >= 0 && !metodi.existMemoria(memoria,statoAttuale.getAltezza()-1, statoAttuale.getLarghezza()) && !metodi.existFrontiera(frontiera,statoAttuale.getAltezza()-1, statoAttuale.getLarghezza())){//nord
            //controllo se la casella sia lo stato target o uno stato scoperto da un altro processo
            if((metodi.TargetControlBidirezional(mat,statoDestinazione,statoAttuale.getAltezza()-1,statoAttuale.getLarghezza()))){
                return true;
            }else{
                double val = metodi.FunzioneValutazioneTorre(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()-1][statoAttuale.getLarghezza()] = ANSI_PURPLE+"F"+ANSI_RESET;
                frontiera.add(new Cordinata(statoAttuale.getAltezza()-1,statoAttuale.getLarghezza(),val));
                System.out.println(statoAttuale.getAltezza()-1 + "<-- altezza " + statoAttuale.getLarghezza() + "<-- larghezza "+ val + "<-- valore");
            }
        }
        if((statoAttuale.getAltezza()+1) < mat.length && !metodi.existMemoria(memoria,statoAttuale.getAltezza()+1, statoAttuale.getLarghezza()) && !metodi.existFrontiera(frontiera,statoAttuale.getAltezza()+1, statoAttuale.getLarghezza())){//sud
            if(statoAttuale.getAltezza()+1 == statoDestinazione.getAltezza() && statoAttuale.getLarghezza() == statoDestinazione.getLarghezza()){
                mat[statoAttuale.getAltezza()+1][statoAttuale.getLarghezza()] = ANSI_RED+"D"+ANSI_RESET;
                return true;
            }else{
                double val = metodi.FunzioneValutazioneTorre(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()+1][statoAttuale.getLarghezza()] = ANSI_PURPLE+"F"+ANSI_RESET;
                frontiera.add(new Cordinata(statoAttuale.getAltezza()+1,statoAttuale.getLarghezza(),val));
                System.out.println(statoAttuale.getAltezza()+1 + "<-- altezza " + statoAttuale.getLarghezza() + "<-- larghezza "+ val + "<-- valore");
            }
        }
        if(statoAttuale.getLarghezza()+1 < mat[0].length && !metodi.existMemoria(memoria,statoAttuale.getAltezza(), statoAttuale.getLarghezza()+1) && !metodi.existFrontiera(frontiera,statoAttuale.getAltezza(), statoAttuale.getLarghezza()+1)){//est
            if(statoAttuale.getAltezza() == statoDestinazione.getAltezza() && statoAttuale.getLarghezza()+1 == statoDestinazione.getLarghezza()){
                mat[statoAttuale.getAltezza()][statoAttuale.getLarghezza()+1] = ANSI_RED+"D"+ANSI_RESET;
                return true;
            }else{
                double val = metodi.FunzioneValutazioneTorre(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()][statoAttuale.getLarghezza()+1] = ANSI_PURPLE+"F"+ANSI_RESET;
                frontiera.add(new Cordinata(statoAttuale.getAltezza(),statoAttuale.getLarghezza()+1,val));
                System.out.println(statoAttuale.getAltezza()+ "<-- altezza " + (statoAttuale.getLarghezza()+1) + "<-- larghezza "+ val + "<-- valore");
            }
        }
        if(statoAttuale.getLarghezza()-1 >= 0 && !metodi.existMemoria(memoria,statoAttuale.getAltezza(), statoAttuale.getLarghezza()-1) && !metodi.existFrontiera(frontiera,statoAttuale.getAltezza(), statoAttuale.getLarghezza()-1)){//ovest
            if(statoAttuale.getAltezza() == statoDestinazione.getAltezza() && statoAttuale.getLarghezza()-1 == statoDestinazione.getLarghezza()){
                mat[statoAttuale.getAltezza()][statoAttuale.getLarghezza()-1] = ANSI_RED+"D"+ANSI_RESET;
                return true;
            }else{
                double val = metodi.FunzioneValutazioneTorre(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()][statoAttuale.getLarghezza()-1] = ANSI_PURPLE+"F"+ANSI_RESET;
                frontiera.add(new Cordinata(statoAttuale.getAltezza(),statoAttuale.getLarghezza()-1,val));
                System.out.println(statoAttuale.getAltezza() + "<-- altezza " + (statoAttuale.getLarghezza()-1) + "<-- larghezza "+ val + "<-- valore");
            }
        }
        if(statoAttuale.getAltezza()-1 >= 0 && statoAttuale.getLarghezza()+1 <= mat[0].length && !metodi.existMemoria(memoria,statoAttuale.getAltezza()-1, statoAttuale.getLarghezza()+1) && !metodi.existFrontiera(frontiera,statoAttuale.getAltezza()-1, statoAttuale.getLarghezza()+1)){//nord est
            if(statoAttuale.getAltezza()-1 == statoDestinazione.getAltezza() && statoAttuale.getLarghezza()+1 == statoDestinazione.getLarghezza()){
                mat[statoAttuale.getAltezza()-1][statoAttuale.getLarghezza()+1] = ANSI_RED+"D"+ANSI_RESET;
                return true;
            }else{
                double val = metodi.FunzioneValutazioneRegina(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()-1][statoAttuale.getLarghezza()+1] = ANSI_PURPLE+"F"+ANSI_RESET;
                frontiera.add(new Cordinata(statoAttuale.getAltezza()-1,statoAttuale.getLarghezza()+1,val));
                System.out.println(statoAttuale.getAltezza()-1 + "<-- altezza " + (statoAttuale.getLarghezza()+1) + "<-- larghezza "+ val + "<-- valore");
            }
        }
        if(statoAttuale.getAltezza()-1 >= 0 && statoAttuale.getLarghezza()-1 >= 0 && !metodi.existMemoria(memoria,statoAttuale.getAltezza()-1, statoAttuale.getLarghezza()-1) && !metodi.existFrontiera(frontiera,statoAttuale.getAltezza()-1, statoAttuale.getLarghezza()-1)){//nord ovest
            if(statoAttuale.getAltezza()-1 == statoDestinazione.getAltezza() && statoAttuale.getLarghezza()-1 == statoDestinazione.getLarghezza()){
                mat[statoAttuale.getAltezza()-1][statoAttuale.getLarghezza()-1] = ANSI_RED+"D"+ANSI_RESET;
                return true;
            }else{
                double val = metodi.FunzioneValutazioneRegina(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()-1][statoAttuale.getLarghezza()-1] = ANSI_PURPLE+"F"+ANSI_RESET;
                frontiera.add(new Cordinata(statoAttuale.getAltezza()-1,statoAttuale.getLarghezza()-1,val));
                System.out.println(statoAttuale.getAltezza()-1 + "<-- altezza " + (statoAttuale.getLarghezza()-1) + "<-- larghezza "+ val + "<-- valore");
            }
        }
        if(statoAttuale.getAltezza()+1 <= mat.length && statoAttuale.getLarghezza()+1 <= mat[0].length && !metodi.existMemoria(memoria,statoAttuale.getAltezza()+1, statoAttuale.getLarghezza()+1) && !metodi.existFrontiera(frontiera,statoAttuale.getAltezza()+1, statoAttuale.getLarghezza()+1)){//sud est
            if(statoAttuale.getAltezza()+1 == statoDestinazione.getAltezza() && statoAttuale.getLarghezza()+1 == statoDestinazione.getLarghezza()){
                mat[statoAttuale.getAltezza()+1][statoAttuale.getLarghezza()+1] = ANSI_RED+"D"+ANSI_RESET;
                return true;
            }else{
                double val = metodi.FunzioneValutazioneRegina(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()+1][statoAttuale.getLarghezza()+1] = ANSI_PURPLE+"F"+ANSI_RESET;
                frontiera.add(new Cordinata(statoAttuale.getAltezza()+1,statoAttuale.getLarghezza()+1,val));
                System.out.println(statoAttuale.getAltezza()+1 + "<-- altezza " + (statoAttuale.getLarghezza()+1) + "<-- larghezza "+ val + "<-- valore");
            }
        }
        if(statoAttuale.getAltezza()+1 <= mat.length && statoAttuale.getLarghezza()-1 >= 0 && !metodi.existMemoria(memoria,statoAttuale.getAltezza()+1, statoAttuale.getLarghezza()-1) && !metodi.existFrontiera(frontiera,statoAttuale.getAltezza()+1, statoAttuale.getLarghezza()-1)){//sud ovest
            if(statoAttuale.getAltezza()+1 == statoDestinazione.getAltezza() && statoAttuale.getLarghezza()-1 == statoDestinazione.getLarghezza()){
                mat[statoAttuale.getAltezza()+1][statoAttuale.getLarghezza()-1] = ANSI_RED+"D"+ANSI_RESET;
                return true;
            }else{
                double val = metodi.FunzioneValutazioneRegina(statoAttuale, statoDestinazione);
                mat[statoAttuale.getAltezza()+1][statoAttuale.getLarghezza()-1] = ANSI_PURPLE+"F"+ANSI_RESET;
                frontiera.add(new Cordinata(statoAttuale.getAltezza()+1,statoAttuale.getLarghezza()-1,val));
                System.out.println(statoAttuale.getAltezza()+1 + "<-- altezza " + (statoAttuale.getLarghezza()-1) + "<-- larghezza "+ val + "<-- valore");
            }
        }
        Cordinata max = Collections.max(frontiera, new Comparator<Cordinata>() {
            @Override
            public int compare(Cordinata o1, Cordinata o2) {
                return Double.compare(o2.getPunteggio(), o1.getPunteggio());
            }
        });
        frontiera.remove(max);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        metodi.PrintMatrix(mat);
        return A_star_Regina_Bidirezionale(mat,frontiera,memoria,max,statoDestinazione);
    }
}
