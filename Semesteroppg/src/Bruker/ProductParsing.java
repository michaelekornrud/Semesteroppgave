package Bruker;

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
}
