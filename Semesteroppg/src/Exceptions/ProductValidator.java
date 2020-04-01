package Exceptions;

import ProductWindow.Controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProductValidator {

    public static String testProductName(String name) throws InvalidProductNameException{
        if (!name.matches("[a-zæøåA-ZÆØÅ0-9- ]*")){
            throw new InvalidProductNameException("Productname is invalid!");
        }
        return name;
    }

    public static String testProductType(String type) throws InvalidTypeException{
        if (!type.matches("[a-zæøåA-ZÆØÅ]*")){
            throw new InvalidTypeException("Product-type is invalid");
        }
        return type;
    }

    public static String testProductBrand(String brand) throws InvalidBrandException{
        if (!brand.matches("[a-zA-Z]*")){
            throw new InvalidBrandException("Product-brand is invalid");
        }
        return brand;
    }

    public static int testNumberOfProducts(int number) throws InvalidNumberOfProductsException{
        if (number < 0 && number >100_000_0){
            throw new InvalidNumberOfProductsException("Invalid input in number-field!");
        }
        return number;
    }

    public static double testPrice(double price) throws InvalidPriceException{
        if (price < 0){
            throw new InvalidPriceException("Invalid input in price-field");
        }
        return price;
    }

    public static String testProductNumber(String number /*String test*/) throws InvalidProductNumberException{

        //Metode for å teste om prduktnr er unikt!
        // Trenger å koble denne sammen med innholdet i coice-boksene for å sjekke om nummeret er unikt.

        /*if (test.contains(number)){
            throw new InvalidProductNumberException("Error! The productNr already exists.");
        }*/

        if (!number.matches("([a-zæøåA-ZÆØÅ]{2})+([0-9]{8})")){
            throw new InvalidProductNumberException("Invalid input in productnumber-field.\nYou must follow this layout: xx12345678 (2 letters first, then 8 numbers)");
        }
        return number;
    }











}
