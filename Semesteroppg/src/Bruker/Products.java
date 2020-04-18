package Bruker;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Products {

    private SimpleStringProperty txtNumber, txtName, txtType;
    private SimpleIntegerProperty txtQuantity, txtPrice;
    private SimpleDoubleProperty txtTotalPrice;

    public Products(String number, String name, String type, int quantity, int price, double totalPrice){
        if(!number.isEmpty() &&!name.isEmpty() && !type.isEmpty() && quantity > 0 && price > 0 && totalPrice > 0){
            this.txtNumber = new SimpleStringProperty(number);
            this.txtName = new SimpleStringProperty(name);
            this.txtType = new SimpleStringProperty(type);
            this.txtQuantity = new SimpleIntegerProperty(quantity);
            this.txtPrice = new SimpleIntegerProperty(price);
            this.txtTotalPrice = new SimpleDoubleProperty(totalPrice);
        }

    }
}
