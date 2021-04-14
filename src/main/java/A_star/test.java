package A_star;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        //creo matrice di caselle
        String[][]mat = new String[6][6];

        //inizializzo tutto il necessario
        List<Cordinata> frontiera = new ArrayList<Cordinata>();//la lista della frontiera
        List<Cordinata> memoria = new ArrayList<Cordinata>();//lista di codirnate gia visitate
        Cordinata partenza = new Cordinata(0,0,0);//nodo di partenza
        Cordinata arrivo = new Cordinata(5,5,0);//nodo di destinazione
        metodi.Popola(mat,partenza,arrivo);

        //esecuzione
        metodi.PrintMatrix(mat);
        //metodi.A_star_Torre(mat,frontiera,memoria,partenza,arrivo);
        metodi.A_star_Regina(mat,frontiera,memoria,partenza,arrivo);
        metodi.PrintMatrix(mat);
    }
}
