package Exceptions;

import ProductWindow.Product;
import User.ComponentDataHandler;


public class ProductValidator {


    public static String testProductName(String name) throws InvalidProductNameException{ //navn-test
        if (!name.matches("[a-zæøåA-ZÆØÅ0-9 ]*")){
            throw new InvalidProductNameException("Productname is invalid!");

        }
        return name;
    }


    public static String testProductType(String type) throws InvalidTypeException{
            if (type.contains("Minne") ||type.contains("Kabinett") ||type.contains("Minnekort") ||type.contains("Prosessor")
                    ||type.contains("Videokort") ||type.contains("Energitilførsel") ||type.contains("Harddisk") ||type.contains("HDD")
                    ||type.contains("CPU-vifte") ||type.contains("Vifter") ||type.contains("Casemods") ||type.contains("Skjerm")
                    ||type.contains("Tastatur") ||type.contains("Hodetelefoner") ||type.contains("Mus") || type.contains("Hovedkort")) {
                return type;
        }
        throw new InvalidTypeException("Product-type: " +type+ " is invalid");
    }


    public static String testProductBrand(String brand) throws InvalidBrandException{ //merke-sjekk

        if (!brand.matches("[a-zA-Z]*")){
            throw new InvalidBrandException("Product-brand is invalid");
        }
        return brand;
    }

    public static int testNumberOfProducts(int number) throws InvalidNumberOfProductsException{ //antall produkter-sjekk
        if (number < 0 && number >100_000_0){
            throw new InvalidNumberOfProductsException("Invalid input in number-field!");
        }
        return number;
    }

    public static double testPrice(double price) throws InvalidPriceException{ //pris-sjekk
        if (price <= 0){
            throw new InvalidPriceException("Invalid input in price-field");
        }
        return price;
    }


    public static int testIfProductsIsEmty(int number) throws InvalidNumberOfProductsException{
        int quant = Integer.parseInt(ComponentDataHandler.componentQuantity);

        if (number <= quant){
            return number;
        } else {
            throw new InvalidNumberOfProductsException("There is not enough amount of the product in out store");
        }

    }

}
