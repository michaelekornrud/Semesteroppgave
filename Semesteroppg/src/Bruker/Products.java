package Bruker;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Products {

    private SimpleStringProperty txtNumber, txtName, txtType;
    public SimpleIntegerProperty txtQuantity;
    private SimpleDoubleProperty txtPrice;

    public Products(String number, String name, String type, int quantity, double price){
        if(!number.isEmpty() &&!name.isEmpty() && !type.isEmpty() && quantity > 0 && price > 0 ){
            this.txtNumber = new SimpleStringProperty(number);
            this.txtName = new SimpleStringProperty(name);
            this.txtType = new SimpleStringProperty(type);
            this.txtQuantity = new SimpleIntegerProperty(quantity);
            this.txtPrice = new SimpleDoubleProperty(price);

        }

    }


    public String getTxtNumber() {return txtNumber.getValue();}
    public void setTxtNumber(String txtNumber){this.txtNumber = new SimpleStringProperty(txtNumber);}

    public String getTxtName(){return txtName.getValue();}
    public void setTxtName(String txtName){this.txtName = new SimpleStringProperty(txtName);}

    public String getTxtType(){return txtType.getValue();}
    public void setTxtType(String txtType){this.txtType = new SimpleStringProperty(txtType);}

    public int getTxtQuantity(){
        return txtQuantity.getValue();
    }
    public void setTxtQuantity(int txtQuantity){
        if(txtQuantity < 1){
            throw new IllegalArgumentException("Verdien kan ikke være negativ");
        }
        this.txtQuantity.set(txtQuantity);
        System.out.print("Fra setTxtQuantity: " + txtQuantity);
    }

    public double getTxtPrice(){return txtPrice.getValue();}

    public void setTxtPrice(double txtPrice){this.txtPrice = new SimpleDoubleProperty(txtPrice);}


    public String toString (){
        return String.format("\n%s; %s; %s; %s; %s;", getTxtNumber(),getTxtName(),getTxtType(), getTxtQuantity(),getTxtPrice());
    }
}
