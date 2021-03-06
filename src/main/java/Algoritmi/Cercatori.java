package Algoritmi;

import java.util.ArrayList;
import java.util.List;

//thread che hanno lo scopo di sfruttare la ricerca bidirezionale per risolvere il problema
public class Cercatori extends Thread{

    private static Cordinata obiettivo;
    private static Cordinata partenza;
    private static String[][] matrice;

    public Cercatori(Cordinata target,Cordinata start,String[][] mat){
        this.obiettivo = target;
        this.partenza = start;
        this.matrice = mat;
    }

    @Override
    public void run() {
        List<Cordinata> frontiera = new ArrayList<Cordinata>();//la lista della frontiera
        List<Cordinata> memoria = new ArrayList<Cordinata>();//lista di codirnate gia visitate
        Bidirezionale.A_star_Regina_Bidirezionale(matrice,frontiera,memoria,partenza,obiettivo);
    }
}
