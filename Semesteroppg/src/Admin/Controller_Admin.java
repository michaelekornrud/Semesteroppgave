package Admin;

import CompleteOrder.AlertBox;
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
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import java.io.IOException;
import java.util.*;


public class Controller_Admin {



   private Component_DataHandler cdh = new Component_DataHandler();

    private Map<String, List<Product>> data;

    @FXML
    private Button btnClose;

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

    private List<ChoiceBox> choiceBoxes;


    @FXML
    public void initialize(){
        LoadData();
        tableView.setEditable(true);

        updatedData();


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
     AlertBox.display("Hør her!", "Din data har blitt lagret :) ");

       try {
           resetchoiceBoxes();
           updatedData();
           LoadData();

       }
       catch (Exception e){
           e.printStackTrace();

       }


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
        String selectedName;

        for (ChoiceBox box : choiceBoxes) {
             selectedName = (String) box.getSelectionModel().getSelectedItem();
            if (selectedName != null) {
                cdh.removeObjectFromChoiceBoxAndCsvFile(selectedName);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Produkt slettet!");
                alert.setHeaderText("Produktet " +selectedName+ " er nå slettet fra lageret.");
                alert.showAndWait();
            }
        }
        ObservableList<Product> productChosen, allProducts;
        allProducts = tableView.getItems();
        productChosen = tableView.getSelectionModel().getSelectedItems();
        allProducts.removeAll(productChosen);
        try {
             resetchoiceBoxes();
             updatedData();
             LoadData();

        }
        catch (Exception e){
            e.printStackTrace();

        }
    }

    @FXML
    private MenuBar menuBar;

    @FXML
    void closeFromMenu (ActionEvent event){
        btnClose.fire();

    }

    @FXML
    void switchUser (ActionEvent event){
        try {
            Parent PCByggingParent = FXMLLoader.load(java.util.Objects.requireNonNull(getClass().getClassLoader().getResource("Loginn/Loginn.fxml")));
            Scene PCByggingScene = new Scene(PCByggingParent);

            AlertBox.display("Advarsel!", "Du logger nå ut");

            //Denne linjen henter stage info
            Stage PCWindow = (Stage) menuBar.getScene().getWindow();
            PCWindow.setTitle("Build your own PC");
            PCWindow.setScene(PCByggingScene);
            PCWindow.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void programHelp (ActionEvent event){

        String ut = "Brukerveiledning: " +
                "\nLogin vindu: " +
                "\nBrukernavn 1: user Passord 1 : user " +
                "\nBrukernavn 2: adminPassord 2: admin "   +
                "\n-------------------------------------------------------------------------------------------------------------------------------------------------" +
                "\nLogin som user: " +
                "\nKnappenes funksjon:" +
                "\n“legg til i handlekurv”: Her velger brukeren hvilke komponenter den ønsker, og dette blir lagt til å handlekurven " +
                "\n(tableview)med antall = 1: Ønsker brukeren flere av samme komponent, kan den enten trykke på knappen en gang til " +
                "\nmed samme produkt valgt, eller redigere antall direkte i handlekurven." +
                "\n“Fjern produkt”: Her kan brukeren trykke på et produkt i handlekurven, og fjerne produktet derfra." +
                "\n“Oppdater priser”: Hvis ikke prisene automatisk oppdateres når brukeren endrer antall, kan brukeren trykke her for å få riktig pris." +
                "\n“Lagre som ønskeliste”: Her kan brukeren lagre handlelisten sin som en ønskeliste, og senere finne tilbake til den hvis de ønsker det."+
                "\n“Åpne ønskeliste”: Finne fram ønskelistene man har lagret."+
                "\n“Fullfør ordre”: Når brukeren føler at alt er på plass i handlekurven, kan den trykke på denne knappen, slik at ordren kan bli " +
                "\nfullført. Man blir sendt videre til et annet vindu der brukeren skriver inn hvor produkte(ne) skal bli sendt. " +
                "\n“Avslutt”: Avslutter programmet. " +
                "\n“Start på nytt”: Fjerner alle produktene fra handlekurven, og det blir blanke ark slik at brukeren kan legge til komponenter på nytt."+
                "\nFilter: Søke etter komponentene man har valgt til handlekurven. " +
                "\n-------------------------------------------------------------------------------------------------------------------------------------------------" +
                "\nLogin som admin"+
                "\nKnappenes funksjon: " +
                "\n“Legg til ny komponent”: Her blir man sendt videre til et nytt vindu der brukeren kan legge til nye komponenter med type," +
                "\nnavn, antall, merke, pris. Dette blir da lagt til i choiceboksene (lageret). " +
                "\n“Legg til data i tableview”: Legger til valgt(e) komponenter i en produktliste(tableview), der admin kan redigere innholdet" +
                "\n(med unntak av ID, siden IDen automatisk blir generert unikt pr produkt). " +
                "\n“Total sletting”: Her velger man komponent(er) fra choiceboksene, og sletter de helt fra lageret."+
                "\n“Lagre endringer”: Når admin har redigert innholdet i produktlisten, trykker h*n på knappen, og endringene blir deretter " +
                "\nlagret i choiceboksene." +
                "\n“Slett produkt fra tableview”: Sletter valgt produkt fra produktlisten. " +
                "\n“Avslutt”: Avslutter programmet";

        AlertBox.display("Hurtighjelp" ,  ut );
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
    void closeProgram(){
        Stage closeP = (Stage) btnClose.getScene().getWindow();
       closeP.close();

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
