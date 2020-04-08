package ProductWindow;


import Exceptions.ProductValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import java.io.IOException;


public class Controller_ProductWindow {

    ObservableList<String> componentType = FXCollections.observableArrayList("Kabinett", "Hovedkort" ,"Prosessor", "Skjermkort", "Minne","Strømforskyvning",
            "Harddisk", "CPU-Vifte", "Vifter", "Casemods","Skjerm", "Tastatur", "Hodetelefoner","Mus");

    public ObservableList<String> choiceConverter(ObservableList<String> componentType){
        return
        this.componentType = componentType;
    }

    Component_DataHandler dataHandler = new Component_DataHandler();

    //UUID id = UUID.randomUUID();

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductNumber;

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
        colProdNr.setCellFactory(TextFieldTableCell.forTableColumn());
        colBrand.setCellFactory(TextFieldTableCell.forTableColumn());
        colType.setCellFactory(TextFieldTableCell.forTableColumn());
        choType.setItems(componentType);
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

        String name = txtProductName.getText();
        String brand = txtBrand.getText();
        String productNumber =txtProductNumber.getText();
        String stringNumberOfProducts = txtNumberOfProducts.getText();
        int numberOfProducts = Integer.parseInt(stringNumberOfProducts);
        String stringPrice = txtPrice.getText();
        double price = Double.parseDouble(stringPrice);

        String value = (String)choType.getSelectionModel().getSelectedItem();

            return new Product(ProductValidator.testProductNumber(productNumber)
                                ,ProductValidator.testProductName(name)
                                , ProductValidator.testNumberOfProducts(numberOfProducts)
                                , ProductValidator.testProductBrand(brand)
                                , ProductValidator.testPrice(price)
                                , ProductValidator.testProductType(value));

        /*String test = "HK12345678"; //En test for å sjekke om produktnr er unikt. Denne stringen må slettes og endres til tidligere innhold i tableview*/
        /*String[] prodNr = new String[]{productNumber};
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


    @FXML
    public void editTableview_prodNr(TableColumn.CellEditEvent<Product, String> edit){
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String prodNr = edit.getNewValue();
        ProductValidator.testProductNumber(prodNr);
        prod.setTxtProductNumber(prodNr);
        tableView.refresh();

    }

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
        txtProductNumber.setText("");
        txtNumberOfProducts.setText("");
        txtBrand.setText("");
        txtPrice.setText("");
    }

}

