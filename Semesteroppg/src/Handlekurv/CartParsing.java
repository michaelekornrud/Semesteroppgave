package Handlekurv;

import java.io.IOException;

public class CartParsing {

    public static Objects parseObjects (String str) throws IOException  {
        String [] strings = str.split(CartFormater.DELIMITER);
        if (strings.length != 15) {
            throw new IOException("Feil bruk av spesialtegn");
        }
        String kabinett = strings [0];
        String hovedkort = strings [1];
        String prosessor = strings [2];
        String skjermkort = strings [3];
        String minne = strings [4];
        String strømforskyning = strings [5];
        String harddisk1 = strings [6];
        String harddisk2 = strings [7];
        String CPUVifte = strings [8];
        String vifter = strings [9];
        String casemods = strings [10];
        String skjerm = strings [11];
        String tastatur = strings [12];
        String mus = strings [13];
        String hodetelefoner = strings [14];

        return new Objects(kabinett,hovedkort,prosessor,skjermkort,minne,
                strømforskyning,harddisk1,harddisk2,CPUVifte,vifter,
                casemods,skjerm,tastatur,mus,hodetelefoner);


    }
}
