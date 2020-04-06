package Handlekurv;

import java.util.List;

public class CartFormater {
    public static String DELIMITER  = ";";

    public static String formatCart (Objects o){
        return o.getKabinett() + DELIMITER + o.getHovedkort() + DELIMITER +  o.getProsessor() + DELIMITER
                +  o.getSkjermkort() + DELIMITER + o.getMinne() +  DELIMITER +  o.getStrømforskyning() + DELIMITER
                + o.getHarddisk1() + DELIMITER + o.getHarddisk2() + DELIMITER + o.getCPUVifte() + DELIMITER
                + o.getVifter() + DELIMITER + o.getCasemods() + DELIMITER +  o.getSkjerm() +  DELIMITER
                + o.getTastatur()  + DELIMITER + o.getMus() + DELIMITER + o.getHodetelefoner();
    }

    public static  String cartFormat (List<Objects> objectsList){
        StringBuffer str = new StringBuffer();
        for (Objects o : objectsList){
            str.append(formatCart(o));
            str.append("\n");
        }
        return str.toString();
    }
}
