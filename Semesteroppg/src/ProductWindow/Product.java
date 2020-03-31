package ProductWindow;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {

    private SimpleStringProperty txtProductName, txtType, txtBrand, txtProductNumber;
    private SimpleIntegerProperty txtNumberOfProducts;
    private SimpleDoubleProperty txtPrice;

    public Product(String name, String productNumber,int numberOfProducts ,String type, String brand, double price
    ){
        if (name != null && type != null && brand != null && numberOfProducts > 0 && price > 0 ){
            this.txtProductName = new SimpleStringProperty(name);
            this.txtType = new SimpleStringProperty(type);
            this.txtBrand = new SimpleStringProperty(brand);
            this.txtNumberOfProducts = new SimpleIntegerProperty(numberOfProducts);
            this.txtPrice = new SimpleDoubleProperty(price);
            this.txtProductNumber = new SimpleStringProperty(productNumber);
        }

    }

    public String getTxtProductName(){return txtProductName.getValue();}
    public void setTxtProductName(String txtProductName){this.txtProductName.set(txtProductName);}

    public String getTxtType(){return txtType.getValue();}
    public void setTxtType(String txtType){this.txtType.set(txtType);}

    public String getTxtBrand(){return txtBrand.getValue();}
    public void setTxtBrand(String txtBrand){this.txtBrand.set(txtBrand);}

    public int getTxtNumberOfProducts(){return txtNumberOfProducts.getValue();}
    public void setTxtNumberOfProducts(int txtNumberOfProducts){this.txtNumberOfProducts.set(txtNumberOfProducts);}

    public String getTxtProductNumber(){return txtProductNumber.getValue();}
    public void setTxtProductNumber(String txtProductNumber){this.txtProductNumber.set(txtProductNumber);}

    public double getTxtPrice(){return txtPrice.getValue();}
    public void setTxtPrice(double txtPrice){this.txtPrice.set(txtPrice);}

    public String toString(){
        return String.format("\b%s;%s;%s;%s;%s;%s\b",getTxtProductName(), getTxtProductNumber() ,getTxtType(), getTxtBrand(), getTxtPrice(), getTxtNumberOfProducts());
    }


}
