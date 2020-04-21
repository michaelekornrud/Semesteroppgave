package Admin;

import Exceptions.ProductValidator;
import ProductWindow.ComponentType;
import ProductWindow.Component_DataHandler;
import ProductWindow.Product;
import ProductWindow.ProductRegister;
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
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.util.*;


public class Controller_Admin {

    Component_DataHandler cdh = new Component_DataHandler();

    private Map<String, List<Product>> data;


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnShoppingCart;

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

    @FXML
    private TableColumn<Product, String> colID;

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

    @FXML
    private TableView<Product> tableView;

    @FXML
    private Button save;

    List<ChoiceBox> choiceBoxes;


    @FXML
    public void initialize() throws Exception {
        LoadData();
        tableView.setEditable(true);

        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colBrand.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colNumberOfProducts.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));


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
    }

   @FXML
    void btnSaveChanges(ActionEvent event) throws Exception { //Knapp som henter en metode som lagrer dataen som er endret i csv-filen
     cdh.changeDataFromTableviewToCsvAndSave(data);
    }


    @FXML
    void addDataToTableview() {
     ObservableList<Product> obsList = FXCollections.observableArrayList();

        for (ChoiceBox box : choiceBoxes) {
            String selectedName = (String) box.getSelectionModel().getSelectedItem();
            if (selectedName != null) {
                obsList.add(getProductByName(selectedName));
            }
        }

        tableView.setItems(obsList);

      /*  String casemods = choCaseMods.getSelectionModel().getSelectedItem();
        String cpu = choCPU.getSelectionModel().getSelectedItem();
        String kabinett = choKabinett.getSelectionModel().getSelectedItem();
        String mc =choMainCard.getSelectionModel().getSelectedItem();
        String prosessor = choProcessor.getSelectionModel().getSelectedItem();
        String screencard = choScreenCard.getSelectionModel().getSelectedItem();
        String memory = choMemory.getSelectionModel().getSelectedItem();
        String energy = choEnergy.getSelectionModel().getSelectedItem();
        String harddrive = choHarddrive.getSelectionModel().getSelectedItem();
        String hdd = choHarddrive1.getSelectionModel().getSelectedItem();
        String fan = choFan.getSelectionModel().getSelectedItem();
        String screen = choScreen.getSelectionModel().getSelectedItem();
        String keyboard = choKeyboard.getSelectionModel().getSelectedItem();
        String mouse = choMouse.getSelectionModel().getSelectedItem();
        String headsett = choHeadsett.getSelectionModel().getSelectedItem();

        ObservableList<Product> observableList = FXCollections.observableArrayList();


    observableList.add(getProductByName(casemods));
    observableList.add(getProductByName(cpu));
    observableList.add(getProductByName(kabinett));
    observableList.add(getProductByName(mc));
    observableList.add(getProductByName(prosessor));
    observableList.add(getProductByName(screencard));
    observableList.add(getProductByName(memory));
    observableList.add(getProductByName(energy));
    observableList.add(getProductByName(harddrive));
    observableList.add(getProductByName(fan));
    observableList.add(getProductByName(screen));
    observableList.add(getProductByName(keyboard));
    observableList.add(getProductByName(mouse));
    observableList.add(getProductByName(headsett));
    observableList.add(getProductByName(hdd));

    tableView.setItems(observableList);*/
}

    public Product getProductByName(String typeName) {  //Metode som henter produktet fra choiceboksene med navn

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
    private void resetchoiceBoxes() { //funker ikke //Metode som skal resette valgt element i choiceboksen
        choiceCaseMods.setValue("");
        choiceHeadset.setValue("");
        choiceCabinet.setValue("");
        choiceHarddrive.setValue("");
        choiceMouse.setValue("");
        choiceMemory.setValue("");
        choiceScreenCard.setValue("");
        choiceMainCard.setValue("");
        choiceKeyboard.setValue("");
        choiceFan.setValue("");
        choicePowerSupply.setValue("");
        choiceCPU.setValue("");
        choiceProcessor.setValue("");
        choiceScreen.setValue("");
        choiceHDD.setValue("");


    }


    @FXML
    void addComponent(ActionEvent event) throws IOException {
        try {
            Parent PCByggingParent = FXMLLoader.load(getClass().getClassLoader().getResource("ProductWindow/productWindow.fxml"));
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
    void shoppingCart(ActionEvent event) throws IOException {
        try {
            Parent PCByggingParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Handlekurv/kurven.fxml")));
            Scene PCByggingScene = new Scene(PCByggingParent);

            //Denne linjen henter stage info
            Stage PCWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            PCWindow.setTitle("Handlekurv");
            PCWindow.setScene(PCByggingScene);
            PCWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void updatedData() throws IOException { //Metode for å legge inn verdi i choicebox kabinett
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
    void LoadData() throws IOException {
        data = cdh.load();
        updatedData();
    }

    ObservableList<String> getCoponentNames(String componentType, Map<String, List<Product>> newData) {

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
