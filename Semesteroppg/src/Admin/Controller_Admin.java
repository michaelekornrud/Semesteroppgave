package Admin;

import Exceptions.ProductValidator;
import ProductWindow.ComponentType;
import ProductWindow.Component_DataHandler;
import ProductWindow.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.*;


public class Controller_Admin {



   private Component_DataHandler cdh = new Component_DataHandler();

    private Map<String, List<Product>> data;


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ChoiceBox<String> choiceCabinet;

    @FXML
    private ChoiceBox<String> choiceMainCard;

    @FXML
    private ChoiceBox<String> choiceProcessor;

    @FXML
    private ChoiceBox<String> choiceScreenCard;

    @FXML
    private ChoiceBox<String> choiceMemory;

    @FXML
    private ChoiceBox<String> choicePowerSupply;

    @FXML
    private ChoiceBox<String> choiceHDD;

    @FXML
    private ChoiceBox<String> choiceHarddrive;

    @FXML
    private ChoiceBox<String> choiceCPU;

    @FXML
    private ChoiceBox<String> choiceFan;

    @FXML
    private ChoiceBox<String> choiceCaseMods;

    @FXML
    private ChoiceBox<String> choiceScreen;

    @FXML
    private ChoiceBox<String> choiceKeyboard;

    @FXML
    private ChoiceBox<String> choiceMouse;

    @FXML
    public ChoiceBox<String> choiceHeadset;

   /* @FXML
    private TableColumn<Product, String> colID;*/

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, Integer> colNumberOfProducts;

    @FXML
    private TableColumn<Product, String> colBrand;

    @FXML
    private TableColumn<Product, Double> colPrice;

    @FXML
    private TableColumn<Product, String> colType;

    /*public Controller_Admin()
    {
        String projectDirectory = System.getProperty("user.dir");
        String csvFile = projectDirectory + "/Semesteroppg/src/Data/comptypes.csv";
        cdh.load();
    }*/


    @FXML
    private TableView<Product> tableView;

    private List<ChoiceBox> choiceBoxes;


    @FXML
    public void initialize(){
        LoadData();
        tableView.setEditable(true);

        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colBrand.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colNumberOfProducts.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colType.setCellFactory(TextFieldTableCell.forTableColumn());
        //Legge til en knapp delete som sletter objektet fra tableview og fra choiceboksen


        choiceBoxes = new ArrayList<>(); //Liste med alle choiceboksene
        choiceBoxes.add(choiceHarddrive);
        choiceBoxes.add(choiceCabinet);
        choiceBoxes.add(choiceMainCard);
        choiceBoxes.add(choiceProcessor);
        choiceBoxes.add(choiceScreenCard);
        choiceBoxes.add(choiceMemory);
        choiceBoxes.add(choicePowerSupply);
        choiceBoxes.add(choiceCPU);
        choiceBoxes.add(choiceFan);
        choiceBoxes.add(choiceCaseMods);
        choiceBoxes.add(choiceScreen);
        choiceBoxes.add(choiceKeyboard);
        choiceBoxes.add(choiceHeadset);
        choiceBoxes.add(choiceMouse);
        choiceBoxes.add(choiceHDD);


        tableView.refresh();
    }

   @FXML
    void btnSaveChanges() throws Exception { //Knapp som henter en metode som lagrer dataen som er endret i csv-filen
     cdh.changeDataFromTableviewToCsvAndSave(data);

    }


    @FXML
    void addDataToTableview(){ //Knapp som legger til data i tableview
     ObservableList<Product> obsList = FXCollections.observableArrayList();

        for (ChoiceBox box : choiceBoxes) {
            String selectedName = (String) box.getSelectionModel().getSelectedItem();
            if (selectedName != null) {
                obsList.add(getProductByName(selectedName));
            }
        }

        tableView.setItems(obsList);
        resetchoiceBoxes();
}





   private Product getProductByName(String typeName) {  //Metode som henter produktet fra choiceboksene med navn

        for (List<Product> productList : data.values()) {
            for (Product product : productList) {
                if (product.getTxtProductName().equals(typeName)) {
                    return product;
                }
            }
        }
        return null;
    }




    @FXML
    public void editTableview_Name(TableColumn.CellEditEvent<Product, String> edit){ //Metode som gjør det mulig å endre på dataen i navn-kolonnen
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String name = edit.getNewValue();
        ProductValidator.testProductName(name);
        prod.setTxtProductName(name);
        tableView.refresh();
    }

    @FXML
    public void editTableview_Brand(TableColumn.CellEditEvent<Product, String> edit){//Metode som gjør det mulig å endre på dataen i merke-kolonnen
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String brand = edit.getNewValue();
        ProductValidator.testProductBrand(brand);
        prod.setTxtBrand(brand);
        tableView.refresh();
    }


    @FXML
    public void editTableview_Type(TableColumn.CellEditEvent<Product, String> edit){//Metode som gjør det mulig å endre på dataen i tyoe-kolonnen
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String type = edit.getNewValue();
        ProductValidator.testProductType(type);
        prod.setTxtType(type);
        tableView.refresh();

    }

   @FXML
    public void editTableview_NumberOfProducts(TableColumn.CellEditEvent<Product, Integer> edit){//Metode som gjør det mulig å endre på dataen i nummer-kolonnen
        Product prod = tableView.getSelectionModel().getSelectedItem();
        int number = edit.getNewValue();
        ProductValidator.testNumberOfProducts(number);
        prod.setTxtNumberOfProducts(number);
        tableView.refresh();
    }

    @FXML
    public void editTableview_Price(TableColumn.CellEditEvent<Product, Double> edit){//Metode som gjør det mulig å endre på dataen i pris-kolonnen
        Product prod = tableView.getSelectionModel().getSelectedItem();
        double price = edit.getNewValue();
        ProductValidator.testPrice(price);
        prod.setTxtPrice(price);
        tableView.refresh();
    }


    @FXML
    void btnDelete() {  //Metode for å slette ett objekt ved å trykke på "delete"-knappen
        ObservableList<Product> productChosen, allProducts;
        allProducts = tableView.getItems();
        productChosen = tableView.getSelectionModel().getSelectedItems();
        allProducts.removeAll(productChosen);

    }




    @FXML
    void btnDeleteFromEveryWhere() throws Exception { //Knapp som sletter gitt produkt fra csv-fil (fra lageret)
        for (ChoiceBox box : choiceBoxes) {
            String selectedName = (String) box.getSelectionModel().getSelectedItem();
            if (selectedName != null) {
                cdh.removeObjectFromChoiceBoxAndCsvFile(selectedName);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Produkt slettet!");
                alert.setHeaderText("Produktet " +selectedName+ " er nå slette fra lageret.");
                alert.showAndWait();
            }
        }
        ObservableList<Product> productChosen, allProducts;
        allProducts = tableView.getItems();
        productChosen = tableView.getSelectionModel().getSelectedItems();
        allProducts.removeAll(productChosen);


        resetchoiceBoxes();
    }




    @FXML
    private void resetchoiceBoxes() { //Metode som skal resette valgt element i choiceboksen
        for (ChoiceBox box : choiceBoxes) {
            SingleSelectionModel selectedName =  box.getSelectionModel();
            if (!selectedName.isEmpty()){
            box.setValue(null);
            }
        }


    }


    @FXML
    void addComponent(ActionEvent event) {
        try {
            Parent PCByggingParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("ProductWindow/productWindow.fxml")));
            Scene PCByggingScene = new Scene(PCByggingParent);

            //Denne linjen henter stage info
            Stage PCWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            PCWindow.setTitle("Legg til komponent");
            PCWindow.setScene(PCByggingScene);
            PCWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void updatedData() { //Metode for å legge inn verdi i choicebox kabinett
        ObservableList<String> kabinettNames = getCoponentNames(ComponentType.CABINET, data);
        ObservableList<String> ProsessorNames = getCoponentNames(ComponentType.PROCESSOR, data);
        ObservableList<String> HarddiskNames = getCoponentNames(ComponentType.HARDDRIVE, data);
        ObservableList<String> hddNames = getCoponentNames(ComponentType.HDD, data);
        ObservableList<String> MainCardNames = getCoponentNames(ComponentType.MAINCARD, data);
        ObservableList<String> ScreenCardNames = getCoponentNames(ComponentType.VIDEOCARD, data);
        ObservableList<String> MemoryNames = getCoponentNames(ComponentType.MEMORY, data);
        ObservableList<String> PowerSupplyNames = getCoponentNames(ComponentType.POWERSUPPLY, data);
        ObservableList<String> ProsessorFanNames = getCoponentNames(ComponentType.PROSESSOR_FAN_NAMES, data);
        ObservableList<String> FanNames = getCoponentNames(ComponentType.FANS, data);
        ObservableList<String> CaseModkNames = getCoponentNames(ComponentType.CASEMODS, data);
        ObservableList<String> ScreenNames = getCoponentNames(ComponentType.SCREEN, data);
        ObservableList<String> KeyboadNames = getCoponentNames(ComponentType.KEYBOARD, data);
        ObservableList<String> HeadSetNames = getCoponentNames(ComponentType.HEADSET, data);
        ObservableList<String> MouseNames = getCoponentNames(ComponentType.MOUSE, data);

        choiceCabinet.setItems(kabinettNames);
        choiceHarddrive.setItems(HarddiskNames);
        choiceProcessor.setItems(ProsessorNames);
        choiceCaseMods.setItems(CaseModkNames);
        choiceCPU.setItems(ProsessorFanNames);
        choicePowerSupply.setItems(PowerSupplyNames);
        choiceFan.setItems(FanNames);
        choiceHeadset.setItems(HeadSetNames);
        choiceKeyboard.setItems(KeyboadNames);
        choiceMainCard.setItems(MainCardNames);
        choiceMemory.setItems(MemoryNames);
        choiceMouse.setItems(MouseNames);
        choiceScreen.setItems(ScreenNames);
        choiceScreenCard.setItems(ScreenCardNames);
        choiceHDD.setItems(hddNames);

    }


    @FXML
    private void LoadData(){
        data = cdh.load();
        updatedData();
    }

   private ObservableList<String> getCoponentNames(String componentType, Map<String, List<Product>> newData) { //Henter komponentnavnene

        List<Product> kabinettComponents = newData.get(componentType);
        ObservableList<String> kabinettNames = FXCollections.observableArrayList();
        if (kabinettComponents != null) {

            for (Product prod : kabinettComponents) {
                kabinettNames.add(prod.getTxtProductName());
            }
        }


        return kabinettNames;

    }










}
