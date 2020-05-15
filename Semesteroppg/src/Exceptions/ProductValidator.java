package Exceptions;

public class ProductValidator {


    public static String testProductName(String name) throws InvalidProductNameException { //navn-test
        if (!name.matches("[a-zæøåA-ZÆØÅ0-9 ]*")) {
            throw new InvalidProductNameException("Productname is invalid!");

        }
        return name;
    }

    public static String testProductType(String type) throws InvalidTypeException {
        if (type.equals("Minne") || type.equals("Kabinett") || type.equals("Prosessor")
                || type.equals("Skjermkort") || type.equals("Strømforskyning") || type.equals("Harddisk") || type.equals("HDD")
                || type.equals("CPU-vifte") || type.equals("Vifter") || type.equals("Casemods") || type.equals("Skjerm")
                || type.equals("Tastatur") || type.equals("Hodetelefoner") || type.equals("Mus") || type.equals("Hovedkort")) {
            return type;
        }
        throw new InvalidTypeException("Product-type: " + type + " is invalid");
    }


    public static String testProductBrand(String brand) throws InvalidBrandException { //merke-sjekk

        if (!brand.matches("[a-zA-Z]*")) {
            throw new InvalidBrandException("Product-brand is invalid");
        }
        return brand;
    }

    public static int testNumberOfProducts(int number) throws InvalidNumberOfProductsException { //antall produkter-sjekk
        if (number < 0 && number > 100_000_0) {
            throw new InvalidNumberOfProductsException("Invalid input in number-field!");
        }
        return number;
    }

    public static double testPrice(double price) throws InvalidPriceException { //pris-sjekk
        if (price < 0) {
            throw new InvalidPriceException("Invalid input in price-field");
        }
        return price;
    }
}
