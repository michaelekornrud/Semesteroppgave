package Exceptions;

import ProductWindow.ComponentType;
import ProductWindow.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.regex.Pattern;

public class ProductValidator {


    public static String testProductName(String name) throws InvalidProductNameException{
        if (!name.matches("[a-zæøåA-ZÆØÅ0-9- ]*")){
            throw new InvalidProductNameException("Productname is invalid!");

        }
        return name;
    }


    public static String testProductType(String type) throws InvalidTypeException{
            if (type.contains("Memory") ||type.contains("Cabinet") ||type.contains("Maincard") ||type.contains("Processor")
                    ||type.contains("Videocard") ||type.contains("Powersupply") ||type.contains("Harddrive") ||type.contains("HDD")
                    ||type.contains("CPUfan") ||type.contains("Fans") ||type.contains("Casemods") ||type.contains("Screen")
                    ||type.contains("Keyboard") ||type.contains("Headset") ||type.contains("Mouse")){
                return type;
        }
        throw new InvalidTypeException("Product-type is invalid");
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
        if (price <= 0){
            throw new InvalidPriceException("Invalid input in price-field");
        }
        return price;
    }

   /* public static String testProductNumber(String number /*String test) throws InvalidProductNumberException{

        if (!number.matches("([a-zæøåA-ZÆØÅ]{2})+([0-9]{8})")){
            throw new InvalidProductNumberException("Invalid input in productnumber-field.\nYou must follow this layout: xx12345678 (2 letters first, then 8 numbers)");
        }
        return number;
    }

    public boolean testID(String id){
        for (Product p : newProduct){
            if (id.equals(p.getTxtProductNumber())){
                return true;
            }
        }
        return false;
    }*/

}
