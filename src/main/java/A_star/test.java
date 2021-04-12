package A_star;

import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        //creo matrice di caselle
        int[][]mat = new int[3][5];
        //la inizializzo
        List<Cordinata> frontiera = new ArrayList<Cordinata>();
        Cordinata partenza = new Cordinata(0,0,0);
        Cordinata arrivo = new Cordinata(2,4,0);
        //stampo la matrice
        metodi.PrintMatrix(mat);
        metodi.A_star(frontiera,mat,partenza,arrivo);
        metodi.PrintMatrix(mat);
    }
}
