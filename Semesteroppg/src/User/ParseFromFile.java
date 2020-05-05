package User;

import Exceptions.InvalidFormatExeption;

public class ParseFromFile {
    public static Products parseProducts (String str) throws InvalidFormatExeption {
        String [] strings = str.split(ProductParsing.DELIMITER);
        if(strings.length != 5){
            throw new InvalidFormatExeption("Feil antal strings i fil");
        }
        String number = strings[0];
        String name = strings[1];
        String type = strings[2];
        String quantityToString = strings[3];
        String priceToString = strings[4];

        int quantity = Integer.parseInt(quantityToString);
        Double price = Double.parseDouble(priceToString);

        return new Products(number,name,type,quantity,price);
    }
}
