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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        ProductRegister.attachToTableView(tableView);
    }

    Component_DataHandler dataHandler = new Component_DataHandler();

    private Map<String, List<Product>> data;


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
    private TableColumn<Product, String> colProdNr;

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

    private ProductRegister newObjects = new ProductRegister();

   /* public Controller_ProductWindow(Map<String, List<Product>> data) {
        this.data = data;
    }*/

    public Controller_ProductWindow() {
    }


    @FXML
    public void initialize() throws IOException {
        newObjects.attachToTableView(tableView);
        tableView.setEditable(true);
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colBrand.setCellFactory(TextFieldTableCell.forTableColumn());
        colType.setCellFactory(TextFieldTableCell.forTableColumn());
        colNumberOfProduct.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        choiceType.setItems(componentType);

    }

    @FXML
    void btnAdd(ActionEvent event) throws IOException {
        initialize();
        Product newProduct = createProductObjectFromGUI();
        ProductRegister.addElement(newProduct);
        resetTxtFields();
        dataHandler.write(newProduct);

    }

    @FXML
    void btnDelete(ActionEvent event) {  //Metode for å slette ett objekt ved å trykke på "delete"-knappen
        ObservableList<Product> productChosen, allProducts;
        allProducts = tableView.getItems();
        productChosen = tableView.getSelectionModel().getSelectedItems();
        allProducts.removeAll(productChosen);

    }

    @FXML
    private Button btnTilbake;

    @FXML
    void back(ActionEvent event) throws IOException {
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
        //String productNumber =txtProductNumber.getText();


            return new Product(uniqueID
                                ,ProductValidator.testProductName(name)
                                ,ProductValidator.testNumberOfProducts(numberOfProducts)
                                ,ProductValidator.testProductBrand(brand)
                                ,ProductValidator.testPrice(price)
                                ,ProductValidator.testProductType(value));


       /* boolean containsId = false;

        for (String key : mappedComponents.keySet()){  //Metode for å sjekke om en id eksistere fra før av
            List<Product> Proditems = mappedComponents.get(key);
            containsId = Proditems.stream().anyMatch(prodItems -> prodItems.getTxtProductNumber().equals(productNumber));
            if(containsId){
                break;
            }
        }

        System.out.println("Contains id: " + productNumber +": " + containsId);*/
    }

    @FXML
    public void editTableview_Name(TableColumn.CellEditEvent<Product, String> edit){
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String name = edit.getNewValue();
        ProductValidator.testProductName(name);
        prod.setTxtProductName(name);
        tableView.refresh();
    }

    /*@FXML
    public void editTableview_prodNr(TableColumn.CellEditEvent<Product, String> edit){
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String prodNr = edit.getNewValue();
        ProductValidator.testProductNumber(prodNr);
        prod.setTxtProductNumber(prodNr);
        tableView.refresh();

    }*/

    @FXML
    public void editTableview_NumberOfProducts(TableColumn.CellEditEvent<Product, String> edit){
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String number = edit.getNewValue();
        int intNumber = Integer.parseInt(number);
        ProductValidator.testNumberOfProducts(intNumber);
        prod.setTxtNumberOfProducts(intNumber);
        tableView.refresh();
    }

    @FXML
    public void editTableview_Type(TableColumn.CellEditEvent<Product, String> edit){
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String type = edit.getNewValue();
        ProductValidator.testProductType(type);
        prod.setTxtType(type);
        tableView.refresh();

    }


    @FXML
    public void editTableview_Brand(TableColumn.CellEditEvent<Product, String> edit){
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String brand = edit.getNewValue();
        ProductValidator.testProductBrand(brand);
        prod.setTxtBrand(brand);
        tableView.refresh();
    }

    @FXML  //Må fikses på!! Fungerer ikke
    public void editTableview_Price(TableColumn.CellEditEvent<Product, String> edit){
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String price = edit.getNewValue();
        double doubPrice = Double.parseDouble(price);
        ProductValidator.testPrice(doubPrice);
        prod.setTxtPrice(doubPrice);
        tableView.refresh();
    }

    @FXML
    private void resetTxtFields() {
        txtProductName.setText("");
        txtNumberOfProducts.setText("");
        txtBrand.setText("");
        txtPrice.setText("");
    }

    /*@FXML
    void btnSaveChanges(ActionEvent event) throws Exception { //Knapp som henter en metode som lagrer dataen som er endret i csv-filen
        dataHandler.changeDataFromTableviewToCsvAndSave(data);
    }*/
}

