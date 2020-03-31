package ProductWindow;

import Exceptions.ProductValidator;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller{


    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductNumber;

    @FXML
    private TextField txtNumberOfProducts;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtPrice;

    @FXML
    private TableView<Product> tableView;
    private ProductRegister newObjects = new ProductRegister();

    @FXML
    void btnAdd(ActionEvent event) {
        Product newProduct = createProductObjectFromGUI();
        ProductRegister.addElement(newProduct);
        newObjects.attachToTableView(tableView);

    }

    @FXML
    void btnDelete(ActionEvent event) {  //Metode for å slette ett objekt ved å trykke på "delete"-knappen
        ObservableList<Product> productChosen, allProducts;
        allProducts = tableView.getItems();
        productChosen = tableView.getSelectionModel().getSelectedItems();
        allProducts.removeAll(productChosen);

    }

    @FXML
    private Product createProductObjectFromGUI(){ //Metode for å lage et produkt fra guiet.
        String name = txtProductName.getText();
        String type = txtType.getText();
        String brand = txtBrand.getText();
        String productNumber = txtProductNumber.getText();
        String stringNumberOfProducts = txtNumberOfProducts.getText();
        int numberOfProducts = Integer.parseInt(stringNumberOfProducts);
        String stringPrice = txtPrice.getText();
        double price = Double.parseDouble(stringPrice);

        /*return new Product(name, productNumber, numberOfProducts, type, brand, price);*/


        return new Product(ProductValidator.testProductName(name), ProductValidator.testProductNumber(productNumber),
                ProductValidator.testNumberOfProducts(numberOfProducts),ProductValidator.testProductType(type),
                ProductValidator.testProductBrand(brand),ProductValidator.testPrice(price));

    }
}

