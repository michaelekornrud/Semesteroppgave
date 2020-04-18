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


    public String getTxtNumber() {return txtNumber.getValue();}
    public void setTxtNumber(String txtNumber){this.txtNumber = new SimpleStringProperty(txtNumber);}

    public String getTxtName(){return txtName.getValue();}
    public void setTxtName(String txtName){this.txtName = new SimpleStringProperty(txtName);}

    public String getTxtType(){return txtType.getValue();}
    public void setTxtType(String txtType){this.txtType = new SimpleStringProperty(txtType);}

    public int getTxtQuantity(){return txtQuantity.getValue();}
    public void setTxtQuantity(int txtQuantity){this.txtQuantity = new SimpleIntegerProperty(txtQuantity);}

    public int getTxtPrice(){return txtPrice.getValue();}
    public void setTxtPrice(int txtPrice){this.txtPrice = new SimpleIntegerProperty(txtPrice);}

    public double getTxtTotalPrice (){return txtTotalPrice.getValue();}
    public void setTxtTotalPrice(double txtTotalPrice){this.txtTotalPrice = new SimpleDoubleProperty(txtTotalPrice);}

    public String toString (){
        return String.format("\n%s; %s; %s; %s; %s; %s;", getTxtNumber(),getTxtName(),getTxtType(), getTxtQuantity(),getTxtPrice(), getTxtTotalPrice());
    }
}
