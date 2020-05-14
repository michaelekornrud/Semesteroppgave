package User;

import java.util.List;

public class ProductParsing {
    public static String DELIMITER = ";";

    public static String formatProducts (Products p) {
        return p.getTxtNumber() + DELIMITER + p.getTxtName() + DELIMITER + p.getTxtType() + DELIMITER + p.getTxtQuantity() + DELIMITER + p.getTxtPrice();
    }

    public static String productsFormater (List<Products> plist){
        StringBuffer str = new StringBuffer();
        for(Products p : plist){
            str.append(formatProducts(p));
            str.append("\n");
        }
        return str.toString();
    }

    public static int getStorage (Products o){
        return o.getTxtStorage();

    }
    public static int storage (List<Products> plist){
        StringBuffer str = new StringBuffer();
        for (Products o : plist){
            str.append(getStorage(o));
        }
        return Integer.parseInt(String.valueOf(str));
    }

    public static int counter (int value){
        for(int i = 0; i < 16; i++){
            value = i;
        }
        return value;
    }
}
