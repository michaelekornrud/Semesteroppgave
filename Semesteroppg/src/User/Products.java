package User;

import javafx.beans.property.*;

public class Products {

    private SimpleStringProperty txtNumber, txtName, txtType;
    private SimpleIntegerProperty txtQuantity;
    private SimpleDoubleProperty txtPrice;
    private SimpleIntegerProperty txtStorage;

    public Products(String number, String name, String type, int quantity, double price, int storage){
        if(!number.isEmpty() &&!name.isEmpty() && !type.isEmpty() && quantity > 0 && price > 0 ){
            this.txtNumber = new SimpleStringProperty(number);
            this.txtName = new SimpleStringProperty(name);
            this.txtType = new SimpleStringProperty(type);
            this.txtQuantity = new SimpleIntegerProperty(quantity);
            this.txtPrice = new SimpleDoubleProperty(price);
            this.txtStorage = new SimpleIntegerProperty(storage);

        }

    }




    public String getTxtNumber() {return txtNumber.getValue();}
    public void setTxtNumber(String txtNumber){this.txtNumber = new SimpleStringProperty(txtNumber);}
    public StringProperty numberProperty() {return this.txtNumber;}

    public String getTxtName(){return txtName.getValue();}
    public void setTxtName(String txtName){this.txtName = new SimpleStringProperty(txtName);}
    public StringProperty nameProperty (){return this.txtName;}

    public String getTxtType(){return txtType.getValue();}
    public void setTxtType(String txtType){this.txtType = new SimpleStringProperty(txtType);}
    public StringProperty typeProperty() {return this.txtType;}

    public int getTxtQuantity(){
        return txtQuantity.getValue();
    }
    public void setTxtQuantity(int txtQuantity){
        this.txtQuantity.set(txtQuantity);
    }
    public IntegerProperty quantityProperty(){return this.txtQuantity;}
    public void setNewQuantity (int txtQuantity){
        if (txtQuantity < 1){
            throw  new IllegalArgumentException("Verdien kan ikke være 0 eller negativ");
        }
        this.txtQuantity.set(getTxtQuantity() + txtQuantity);
        txtQuantity = 1;
    }

    public double getTxtPrice(){return txtPrice.getValue();}

    public void setTxtPrice(double txtPrice){this.txtPrice = new SimpleDoubleProperty(txtPrice);}
    public DoubleProperty priceProperty(){return this.txtPrice;}

    public Integer getTxtStorage(){return txtStorage.getValue();}
    public IntegerProperty storageProperty(){return this.txtStorage;}


    public String toString (){
        return String.format("\n%s; %s; %s; %s; %s; %s", getTxtNumber(),getTxtName(),getTxtType(), getTxtQuantity(),getTxtPrice(), getTxtStorage());
    }


}
