package ProductWindow;

import Exceptions.ProductValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import java.io.IOException;
import java.net.URL;
import java.util.*;



public class Controller_ProductWindow implements Initializable {

    public ObservableList<String> componentType = FXCollections.observableArrayList(ComponentType.CABINET, ComponentType.CASEMODS, ComponentType.PROSESSOR_FAN_NAMES, ComponentType.HDD
    , ComponentType.HARDDRIVE, ComponentType.HEADSET, ComponentType.MAINCARD, ComponentType.MEMORY, ComponentType.MOUSE, ComponentType.PROCESSOR, ComponentType.SCREEN
    , ComponentType.POWERSUPPLY, ComponentType.KEYBOARD, ComponentType.FANS, ComponentType.VIDEOCARD);
    //Liste med componenttypene slik at man kan bruke denne listen til å lage nye produktet samt sjekke om typen er riktig

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ProductRegister.attachToTableView(tableView);
        tableView.setEditable(true);
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colBrand.setCellFactory(TextFieldTableCell.forTableColumn());
        colType.setCellFactory(TextFieldTableCell.forTableColumn());
        colNumberOfProduct.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        choiceType.setItems(componentType);
    }

    Component_DataHandler dataHandler = new Component_DataHandler();

    private Map<String, List<Product>> data;
    private ProductRegister newObjects = new ProductRegister();


    @FXML
    private TextField txtProductName;


    @FXML
    private TextField txtNumberOfProducts;

    @FXML
    private ChoiceBox<String> choiceType;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtPrice;

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, Integer> colNumberOfProduct;

    @FXML
    private TableColumn<Product, String> colBrand;

    @FXML
    private TableColumn<Product, Double> colPrice;

    @FXML
    private TableColumn<Product, String> colType;

    @FXML
    private TableView<Product> tableView;

    @FXML
    void btnAdd(ActionEvent event) throws IOException { //Knapp for å legge til nytt produkt i både tableview og choiceboksene
        Product newProduct = createProductObjectFromGUI();
        ProductRegister.addElement(newProduct);
        resetTxtFields();
        dataHandler.write(newProduct);

    }

    @FXML
    void btnDelete(ActionEvent event) {  //Knapp for å slette ett objekt ved å trykke på "delete"-knappen
        ObservableList<Product> productChosen, allProducts;
        allProducts = tableView.getItems();
        productChosen = tableView.getSelectionModel().getSelectedItems();
        allProducts.removeAll(productChosen);

    }

    @FXML
    void back(ActionEvent event) throws IOException { //Knapp for å gå tilbake til admin-vinduet
        try {
        Parent PCByggingParent = FXMLLoader.load(getClass().getClassLoader().getResource("Admin/PCBygging.fxml"));
        Scene PCByggingScene = new Scene(PCByggingParent);

        //Denne linjen henter stage info
        Stage PCWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        PCWindow.setTitle("Build your own PC");
        PCWindow.setScene(PCByggingScene);
        PCWindow.show();
    }
        catch (IOException e){
            e.printStackTrace();

        }
    }

    @FXML
    private Product createProductObjectFromGUI(){ //Metode for å lage et produkt fra guiet.
        String uniqueID = UUID.randomUUID().toString();
        String name = txtProductName.getText();
        String brand = txtBrand.getText();
        String stringNumberOfProducts = txtNumberOfProducts.getText();
        int numberOfProducts = Integer.parseInt(stringNumberOfProducts);
        String stringPrice = txtPrice.getText();
        double price = Double.parseDouble(stringPrice);
        String value = choiceType.getSelectionModel().getSelectedItem();


            return new Product(uniqueID
                                ,ProductValidator.testProductName(name)
                                ,ProductValidator.testNumberOfProducts(numberOfProducts)
                                ,ProductValidator.testProductBrand(brand)
                                ,ProductValidator.testPrice(price)
                                ,ProductValidator.testProductType(value));

    }

    @FXML
    public void editTableview_Name(TableColumn.CellEditEvent<Product, String> edit){ //Metode for å redigere navnet på produktet i tableview
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String name = edit.getNewValue();
        ProductValidator.testProductName(name);
        prod.setTxtProductName(name);
        tableView.refresh();
    }

    @FXML
    public void editTableview_NumberOfProducts(TableColumn.CellEditEvent<Product, String> edit){  //Metode for å redigere antallet av gitt produkt i tableview
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String number = edit.getNewValue();
        int intNumber = Integer.parseInt(number);
        ProductValidator.testNumberOfProducts(intNumber);
        prod.setTxtNumberOfProducts(intNumber);
        tableView.refresh();
    }

    @FXML
    public void editTableview_Type(TableColumn.CellEditEvent<Product, String> edit){  //Metode for å redigere typen på produktet i tableview
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String type = edit.getNewValue();
        ProductValidator.testProductType(type);
        prod.setTxtType(type);
        tableView.refresh();

    }


    @FXML
    public void editTableview_Brand(TableColumn.CellEditEvent<Product, String> edit){  //Metode for å redigere merket til produktet i tableview
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String brand = edit.getNewValue();
        ProductValidator.testProductBrand(brand);
        prod.setTxtBrand(brand);
        tableView.refresh();
    }

    @FXML  //Må fikses på!! Fungerer ikke
    public void editTableview_Price(TableColumn.CellEditEvent<Product, String> edit){  //Metode for å redigere prisen på produktet i tableview
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String price = edit.getNewValue();
        double doubPrice = Double.parseDouble(price);
        ProductValidator.testPrice(doubPrice);
        prod.setTxtPrice(doubPrice);
        tableView.refresh();
    }

    @FXML
    private void resetTxtFields() { //metode for å resette tekstfeltene
        txtProductName.setText("");
        txtNumberOfProducts.setText("");
        txtBrand.setText("");
        txtPrice.setText("");
    }

}

