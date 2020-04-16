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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.util.*;


public class Controller_Admin {

  ;
    Component_DataHandler cdh = new Component_DataHandler();
    private Map<String, List<Product>> data;


    @FXML
    private Button btnLeggTil;

    @FXML
    private Button btnHandlekurv;

    @FXML
    private ChoiceBox<String> choKabinett;

    @FXML
    private ChoiceBox<String> choMainCard;

    @FXML
    private ChoiceBox<String> choProcessor;

    @FXML
    private ChoiceBox<String> choScreenCard;

    @FXML
    private ChoiceBox<String> choMemory;

    @FXML
    private ChoiceBox<String> choEnergy;

    @FXML
    private ChoiceBox<String> choHarddrive;

    @FXML
    private ChoiceBox<String> choCPU;

    @FXML
    private ChoiceBox<String> choFan;

    @FXML
    private ChoiceBox<String> choCaseMods;

    @FXML
    private ChoiceBox<String> choScreen;

    @FXML
    private ChoiceBox<String> choKeyboard;

    @FXML
    private ChoiceBox<String> choMouse;

    @FXML
    public ChoiceBox<String> choHeadsett;

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
    public void initialize() throws IOException {
        LoadData();
        tableView.setEditable(true);
        resetchoiceBoxes();
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colBrand.setCellFactory(TextFieldTableCell.forTableColumn());
        colType.setCellFactory(TextFieldTableCell.forTableColumn());
        //colNumberOfProducts.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));


    }


    @FXML
    void LeggTilDataITablevieW(ActionEvent event) {
        String casemods = choCaseMods.getSelectionModel().getSelectedItem();
        String cpu = choCPU.getSelectionModel().getSelectedItem();
        String kabinett = choKabinett.getSelectionModel().getSelectedItem();
        String mc =choMainCard.getSelectionModel().getSelectedItem();
        String prosessor = choProcessor.getSelectionModel().getSelectedItem();
        String screencard = choScreenCard.getSelectionModel().getSelectedItem();
        String memory = choMemory.getSelectionModel().getSelectedItem();
        String energy = choEnergy.getSelectionModel().getSelectedItem();
        String harddrive = choHarddrive.getSelectionModel().getSelectedItem();
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

        tableView.setItems(observableList);

    }

    public Product getProductByName(String typeName) {

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
    public void editTableview_Name(TableColumn.CellEditEvent<Product, String> edit){
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String name = edit.getNewValue();
        ProductValidator.testProductName(name);
        prod.setTxtProductName(name);
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


    @FXML
    public void editTableview_Type(TableColumn.CellEditEvent<Product, String> edit){
        Product prod = tableView.getSelectionModel().getSelectedItem();
        String type = edit.getNewValue();
        ProductValidator.testProductType(type);
        prod.setTxtType(type);
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
    private void resetchoiceBoxes() {
        choCaseMods.setValue("");
        choHeadsett.setValue("");
        choKabinett.setValue("");
        choHarddrive.setValue("");
        choMouse.setValue("");
        choMemory.setValue("");
        choScreenCard.setValue("");
        choMainCard.setValue("");
        choKeyboard.setValue("");
        choFan.setValue("");
        choEnergy.setValue("");
        choCPU.setValue("");
        choProcessor.setValue("");
        choScreen.setValue("");
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
    void Handlekurv(ActionEvent event) throws IOException {
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
        //List<BaseComponent> kabinettComponents = newData.get(ComponentType.KABINETT);
        ObservableList<String> kabinettNames = getCoponentNames(ComponentType.KABINETT, data);
        ObservableList<String> ProsessorNames = getCoponentNames(ComponentType.PROCESSOR, data);
        ObservableList<String> HarddiskNames = getCoponentNames(ComponentType.HARDDISK, data);
        ObservableList<String> MainCardNames = getCoponentNames(ComponentType.MAINCARD, data);
        ObservableList<String> ScreenCardNames = getCoponentNames(ComponentType.SKJERMKORT, data);
        ObservableList<String> MemoryNames = getCoponentNames(ComponentType.MINNE, data);
        ObservableList<String> PowerSupplyNames = getCoponentNames(ComponentType.STRØMFORSKYVNING, data);
        ObservableList<String> ProsessorFanNames = getCoponentNames(ComponentType.PROSESSOR_FAN_NAMES, data);
        ObservableList<String> FanNames = getCoponentNames(ComponentType.VIFTER, data);
        ObservableList<String> CaseModkNames = getCoponentNames(ComponentType.CASEMODS, data);
        ObservableList<String> ScreenNames = getCoponentNames(ComponentType.SKJERM, data);
        ObservableList<String> KeyboadNames = getCoponentNames(ComponentType.TASTATUR, data);
        ObservableList<String> HeadSetNames = getCoponentNames(ComponentType.HODETELEFONER, data);
        ObservableList<String> MouseNames = getCoponentNames(ComponentType.MUS, data);

        choKabinett.setItems(kabinettNames);
        choHarddrive.setItems(HarddiskNames);
        choProcessor.setItems(ProsessorNames);
        choCaseMods.setItems(CaseModkNames);
        choCPU.setItems(ProsessorFanNames);
        choEnergy.setItems(PowerSupplyNames);
        choFan.setItems(FanNames);
        choHeadsett.setItems(HeadSetNames);
        choKeyboard.setItems(KeyboadNames);
        choMainCard.setItems(MainCardNames);
        choMemory.setItems(MemoryNames);
        choMouse.setItems(MouseNames);
        choScreen.setItems(ScreenNames);
        choScreenCard.setItems(ScreenCardNames);

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
