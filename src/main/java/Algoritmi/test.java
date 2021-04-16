package Algoritmi;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {

        //codice dedicato all'esecuzione di A_star
        /*
        //creo matrice di caselle
        String[][]mat = new String[7][12];

        //inizializzo tutto il necessario
        List<Cordinata> frontiera = new ArrayList<Cordinata>();//la lista della frontiera
        List<Cordinata> memoria = new ArrayList<Cordinata>();//lista di codirnate gia visitate
        Cordinata partenza = new Cordinata(0,0,0);//nodo di partenza
        Cordinata arrivo = new Cordinata(6,11,0);//nodo di destinazione
        metodi.Popola(mat,partenza,arrivo);

        //esecuzione
        metodi.PrintMatrix(mat);
        //A_star.A_star_Torre(mat,frontiera,memoria,partenza,arrivo);
        A_star.A_star_Regina(mat,frontiera,memoria,partenza,arrivo);
        metodi.PrintMatrix(mat);
        */

        //codice dedicato all'esecuzione della ricerca bidirezionale
        /*
        //creo matrice di caselle
        String[][]mat = new String[6][6];

        //inizializzo tutto il necessario
        Cordinata partenza = new Cordinata(0,0,0);//nodo di partenza
        Cordinata arrivo = new Cordinata(5,5,0);//nodo di destinazione
        metodi.Popola(mat,partenza,arrivo);

        //esecuzione
        metodi.PrintMatrix(mat);
        Cercatori a = new Cercatori(arrivo,partenza,mat);
        Cercatori b = new Cercatori(partenza,arrivo,mat);
        b.run();
        a.run();
        metodi.PrintMatrix(mat);
        */
    }
}
