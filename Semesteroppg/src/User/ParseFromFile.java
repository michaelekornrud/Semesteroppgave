package User;

import Exceptions.InvalidFormatExeption;

public class ParseFromFile {
    public static Products parseProducts (String str) throws InvalidFormatExeption {
        String [] strings = str.split(ProductParsing.DELIMITER);
        if(strings.length != 6){
            throw new InvalidFormatExeption("Feil antal strings i fil");
        }
        String number = strings[0];
        String name = strings[1];
        String type = strings[2];
        String quantityAsString = strings[3];
        String priceAsString = strings[4];
        String storageAsString = strings[5];

        int storage = Integer.parseInt(storageAsString);
        int quantity = Integer.parseInt(quantityAsString);
        Double price = Double.parseDouble(priceAsString);

        return new Products(number,name,type,quantity,price, storage);
    }
}
