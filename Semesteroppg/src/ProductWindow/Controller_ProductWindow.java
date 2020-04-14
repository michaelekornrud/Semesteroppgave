package ProductWindow;


import Exceptions.InvalidProductNumberException;
import Exceptions.ProductValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.*;


public class Controller_ProductWindow {

    ObservableList<String> componentType = FXCollections.observableArrayList(ComponentType.KABINETT, ComponentType.CASEMODS, ComponentType.PROSESSOR_FAN_NAMES
    , ComponentType.HARDDISK, ComponentType.HODETELEFONER, ComponentType.MAINCARD, ComponentType.MINNE, ComponentType.MUS, ComponentType.PROCESSOR, ComponentType.SKJERM
    , ComponentType.STRØMFORSKYVNING, ComponentType.TASTATUR, ComponentType.VIFTER, ComponentType.SKJERMKORT);


    Map<String, List<Product>> mappedComponents = new HashMap<>(); //Legger til en mapped components
    

    public ObservableList<String> choiceConverter(ObservableList<String> componentType){
        return
        this.componentType = componentType;
    }

    Component_DataHandler dataHandler = new Component_DataHandler();



    @FXML
    private TextField txtProductName;


    @FXML
    private TextField txtNumberOfProducts;

    @FXML
    private ChoiceBox<String> choType;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtPrice;

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, String> colProdNr;

    @FXML
    private TableColumn<Product, String> colNumberOfProduct;

    @FXML
    private TableColumn<Product, String> colBrand;

    @FXML
    private TableColumn<Product, String> colPrice;

    @FXML
    private TableColumn<Product, String> colType;

    @FXML
    private TableView<Product> tableView;
    private ProductRegister newObjects = new ProductRegister();


    @FXML
    public void initialize(){
        newObjects.attachToTableView(tableView);
        tableView.setEditable(true);
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colBrand.setCellFactory(TextFieldTableCell.forTableColumn());
        colType.setCellFactory(TextFieldTableCell.forTableColumn());
        choType.setItems(componentType);
        /*colProdNr.setCellFactory(TextFieldTableCell.forTableColumn());*/
        //colNumberOfProduct.setCellFactory(TextFieldTableCell.forTableColumn());
        //colPrice.setCellFactory(TextFieldTableCell.forTableColumn());


    }


    @FXML
    void btnAdd(ActionEvent event) throws IOException {
        initialize();
        Product newProduct = createProductObjectFromGUI();
        ProductRegister.addElement(newProduct);
        resetTxtFields();
        dataHandler.write(newProduct);


       /* String choiceb = choType.getSelectionModel().getSelectedItem();

        if (choiceb == "Mus"){
            tableView.getItems();
        }*/


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
    void Tilbake (ActionEvent event) throws IOException {
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
        String value = choType.getSelectionModel().getSelectedItem();
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

        System.out.println("Contains id: " + productNumber +": " + containsId);*

            /* String test = "HK12345678"; //En test for å sjekke om produktnr er unikt. Denne stringen må slettes og endres til tidligere innhold i tableview
        String[] prodNr = new String[]{productNumber};
        List<String> list = Arrays.asList(prodNr);
        if (list.contains(productNumber)){
            throw new InvalidProductNumberException("The number already exists!");
        }*/
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

    @FXML //Må fikses på!! Fungerer ikke
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
        //ProductValidator.testProductType(type);
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
}

