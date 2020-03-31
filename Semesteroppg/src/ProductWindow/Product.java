package ProductWindow;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {

    private SimpleStringProperty txtName, txtType, txtBrand;
    private SimpleIntegerProperty txtNumberOfProducts, txtProductNumber;
    private SimpleDoubleProperty txtPrice;

    public Product(String name,int productNumber,int numberOfProducts ,String type, String brand, double price
    ){
        if (name != null && type != null && brand != null && numberOfProducts > 0 && price > 0 ){
            this.txtName = new SimpleStringProperty(name);
            this.txtType = new SimpleStringProperty(type);
            this.txtBrand = new SimpleStringProperty(brand);
            this.txtNumberOfProducts = new SimpleIntegerProperty(numberOfProducts);
            this.txtPrice = new SimpleDoubleProperty(price);
            this.txtProductNumber = new SimpleIntegerProperty(productNumber);
        }

    }

    public String getTxtName(){return txtName.getValue();}
    public void setTxtName(String txtName){this.txtName.set(txtName);}

    public String getTxtType(){return txtType.getValue();}
    public void setTxtType(String txtType){this.txtType.set(txtType);}

    public String getTxtBrand(){return txtBrand.getValue();}
    public void setTxtBrand(String txtBrand){this.txtBrand.set(txtBrand);}

    public int getTxtNumber(){return txtNumberOfProducts.getValue();}
    public void setTxtNumber(int txtNumber){this.txtNumberOfProducts.set(txtNumber);}

    public int getTxtProductNumber(){return txtProductNumber.getValue();}
    public void setTxtProductNumber(int txtProductNumber){this.txtProductNumber.set(txtProductNumber);}

    public double getTxtPrice(){return txtPrice.getValue();}
    public void setTxtPrice(double txtPrice){this.txtPrice.set(txtPrice);}

    public String toString(){
        return String.format("\b%s;%s;%s;%s;%s;%s\b",getTxtName(), getTxtProductNumber() ,getTxtType(), getTxtBrand(), getTxtPrice(), getTxtNumber());
    }


}
