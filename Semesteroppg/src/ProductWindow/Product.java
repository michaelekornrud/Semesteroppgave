package ProductWindow;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {

    private SimpleStringProperty txtProductName, txtBrand, txtType,txtProductNumber;
    private SimpleIntegerProperty txtNumberOfProducts;
    private SimpleDoubleProperty txtPrice;




    public Product(String productNumber, String name, int numberOfProducts , String brand, double price, String type){
        if (name != null && brand != null && numberOfProducts > 0 && price > 0 ){
            this.txtProductNumber = new SimpleStringProperty(productNumber);
            this.txtProductName = new SimpleStringProperty(name);
            this.txtNumberOfProducts = new SimpleIntegerProperty(numberOfProducts);
            this.txtBrand = new SimpleStringProperty(brand);
            this.txtPrice = new SimpleDoubleProperty(price);
            this.txtType = new SimpleStringProperty(type);


        }

    }


    public String getTxtProductName(){return txtProductName.getValue();}
    public void setTxtProductName(String txtProductName){this.txtProductName = new SimpleStringProperty(txtProductName);}

    public String getTxtBrand(){return txtBrand.getValue();}
    public void setTxtBrand(String txtBrand){this.txtBrand.set(txtBrand);}

    public int getTxtNumberOfProducts(){return txtNumberOfProducts.getValue();}
    public void setTxtNumberOfProducts(int txtNumberOfProducts){this.txtNumberOfProducts = new SimpleIntegerProperty(txtNumberOfProducts);}

    public String getTxtProductNumber(){return txtProductNumber.getValue();}

    public double getTxtPrice(){return txtPrice.getValue();}
    public void setTxtPrice(double txtPrice){this.txtPrice.set(txtPrice);}

    public String getTxtType(){return txtType.getValue();}
    public void setTxtType(String txtType){this.txtType.set(txtType);}

    public String toString(){
        return String.format("%s;%s;%s;%s;%s;%s"+"\n", getTxtProductNumber(),getTxtProductName() ,getTxtNumberOfProducts() ,getTxtBrand() ,getTxtPrice(), getTxtType());
    }

}
