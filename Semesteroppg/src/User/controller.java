package User;

import CompleteOrder.AlertBox;
import Exceptions.InvalidQuantityException;
import ProductWindow.*;
import SleeperThread.SleeperThread;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.text.BadLocationException;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class controller extends Controller_ProductWindow {


    CartRegister addNewProduct = new CartRegister();




    ComponentDataHandler cdh = new ComponentDataHandler();
    private Map<String, List<Products>> data;
    private SleeperThread task;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ChoiceBox<String> choCabinet;

    @FXML
    private ChoiceBox<String> choMotherboard;

    @FXML
    private ChoiceBox<String> choProcessor;

    @FXML
    private ChoiceBox<String> choGraphicscard;

    @FXML
    private ChoiceBox<String> choMemory;

    @FXML
    private ChoiceBox<String> choPowersupply;

    @FXML
    private ChoiceBox<String> choHDD;

    @FXML
    private ChoiceBox<String> choSSD;

    @FXML
    private ChoiceBox<String> choCPUfan;

    @FXML
    private ChoiceBox<String> choFans;

    @FXML
    private ChoiceBox<String> choCasemods;

    @FXML
    private ChoiceBox<String> choMonitor;

    @FXML
    private ChoiceBox<String> choKeyboard;

    @FXML
    private ChoiceBox<String> choMouse;

    @FXML
    private ChoiceBox<String> choHeadphones;

    @FXML
    private Button btnHandlekurv;


    @FXML
    private Button btnRefresh;

    @FXML
    public Button btnReset;

    @FXML
    private Button btnLukkProgram;

    @FXML
    private TableView<Products> TVcart;

    @FXML
    private TableColumn<Products, String> colNumber;

    @FXML
    private TableColumn<Products, String> colName;

    @FXML
    private TableColumn<Products, String> colType;

    @FXML
    private TableColumn<Products, Integer> colQuantity;

    @FXML
    private TableColumn<Products, Double> colPrice;

    @FXML
    private TableColumn<Products, Integer> colStorage;

    @FXML
    private TextField txtFiltered;

    @FXML
    private Label lblPris;

    @FXML
    private Label lblMva;

    @FXML
    private Label lblTotPris;
    int sum = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        addNewProduct.attachToTableView(TVcart);
        try {
            LoadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TVcart.setItems(observableList);
        TVcart.setEditable(true);
        colQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerToStringParse()));
        btnRefresh.fire();

        colQuantity.setOnEditCommit(event1 -> {
            int index = TVcart.getSelectionModel().getSelectedIndex();
            int number = Integer.parseInt(colNumber.getCellObservableValue(index).getValue());

            if(index == number -1){
                event1.getTableView().getItems().get(event1.getTablePosition().getRow()).setTxtQuantity(checkStorage(event1.getNewValue()));
                btnRefresh.fire();
            }
             else {
                System.out.println("Opppppps!");
             }
        });

        //Initialliserer colonnene
        colNumber.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        colQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        colStorage.setCellValueFactory((cellData -> cellData.getValue().storageProperty().asObject()));



    }


    @FXML
    private MenuBar menuBar;

    @FXML
    void closeFromMenu (ActionEvent event){
        btnLukkProgram.fire();

    }

    @FXML
    void switchUser (ActionEvent event){
         try {
            Parent PCByggingParent = FXMLLoader.load(java.util.Objects.requireNonNull(getClass().getClassLoader().getResource("Loginn/Loginn.fxml")));
             Scene PCByggingScene = new Scene(PCByggingParent);

             AlertBox.display("Advarsel!", "Du logger nå ut", 1000);

             //Denne linjen henter stage info
             Stage PCWindow = (Stage) menuBar.getScene().getWindow();
             PCWindow.setTitle("Build your own PC");                                                                                                              
             PCWindow.setScene(PCByggingScene);
             Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
             PCWindow.setX((screenSize.getWidth() - PCWindow.getWidth()) / 2);
             PCWindow.setY((screenSize.getHeight() - PCWindow.getHeight()) / 2);
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
                "\nBrukernavn 2: admin Passord 2: admin "   +
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
                "\n“Total sletting”: Her velger man komponent(er) fra choiceboksene, og sletter de helt fra lageret." +
                "\n“Lagre endringer”: Når admin har redigert innholdet i produktlisten, trykker h*n på knappen, og endringene blir deretter " +
                "\nlagret i choiceboksene." +
                "\n“Slett produkt fra tableview”: Sletter valgt produkt fra produktlisten. " +
                "\n“Avslutt”: Avslutter programmet";

        AlertBox.display("Hurtighjelp" ,  ut, 0 );
    }

    public int checkStorage (int inValue) throws InvalidQuantityException {

        int index = TVcart.getSelectionModel().getSelectedIndex();
        int number = colStorage.getCellObservableValue(index).getValue();
        for (int i = 0; i < TVcart.getItems().size(); i++){

            if(inValue > number){
                AlertBox.display("Oops!..." , "Antallet du oppga er større enn vår lagerbeholdning" + "\nVi har " + number + " av denne varen på lager.", 300);

                inValue = 1;
            }
            else if (inValue < 1){
                AlertBox.display("Oops!..." ,"Antallet kan ikke være mindre enn 1", 300);
                inValue = 1;
            }
            else{
                return inValue;
            }
        }
        return inValue;
    }


    public void totalPrice(TableView<Products> tp, Label lblTotPris) throws NullPointerException{
        double totalPrice = 0;
        double mva = 0;
        double price = 0;
        for(int i = 0; i <TVcart.getItems().size(); i++){
            try{
                totalPrice = totalPrice + Double.parseDouble(String.valueOf((TVcart.getColumns().get(4).getCellObservableValue(i).getValue())));
            }
            catch (NullPointerException e){
                e.getMessage();
            }
        }

        mva = (totalPrice * 25)/ 100;
        price = totalPrice - mva;

        lblTotPris.setText((totalPrice + "0")  + " kr,-");
        lblMva.setText((mva + "0") + " kr,-");
        lblPris.setText((price + "0") + " kr,-");



    }

    private void threadDone(WorkerStateEvent e){
        Integer result = task.getValue();
        btnHandlekurv.setDisable(false);
    }

    private void threadFailed(WorkerStateEvent event) {
        Object e = event.getSource().getException();
        btnHandlekurv.setDisable(false);
    }

    public controller() throws FileNotFoundException {
        super();
    }

    @FXML
    public void refreshPrice(ActionEvent event){ //Funker ikke helt som den skal..
        sum = 0;

        for(Products price : TVcart.getItems()){
            Double priceUpdated = Double.parseDouble(String.valueOf(colPrice.getCellObservableValue(price).getValue()));
            int quantity = Integer.parseInt(String.valueOf(colQuantity.getCellObservableValue(price).getValue()));
            Double newPrice = quantity * priceUpdated;
            sum += newPrice;
        }

        int mva = (sum * 25)/ 100;
        int pris = sum - mva;

        lblPris.setText((pris + ".00 kr,-"));
        lblMva.setText(mva + ".00 kr,-");
        lblTotPris.setText(sum + ".00 kr,-");
    }

    @FXML
    void quantityEdited(TableColumn.CellEditEvent<Products, Integer> event){
        TVcart.refresh();

    }

    @FXML
    void deleteRow(ActionEvent event) {
        ObservableList<Products> productChosen, allProducts;
        allProducts = TVcart.getItems();
        productChosen = TVcart.getSelectionModel().getSelectedItems();
        String  type = String.valueOf(productChosen.get(2));

        allProducts.removeAll(productChosen);
        btnRefresh.fire();
    }

    //Oppretter en liste hvor komponent-data blir lagret
    //Denne listen ligger som public utenfor Handlekurv-metoden for at jeg skal få tilgang til den til filtrening.
    public static ObservableList<Products> observableList = FXCollections.observableArrayList();

    @FXML
    void Handlekurv (ActionEvent event) throws NullPointerException {


        //AlertBox.display("Laster..." , "Vent mens produktene lastes til handlekurven", 500);


        //Henter valgt komponent fra choiceboxene
        String cabinett = choCabinet.getSelectionModel().getSelectedItem();
        String motherBoard = choMotherboard.getSelectionModel().getSelectedItem();
        String processor = choProcessor.getSelectionModel().getSelectedItem();
        String graphicsCard = choGraphicscard.getSelectionModel().getSelectedItem();
        String memory = choMemory.getSelectionModel().getSelectedItem();
        String powersupply = choPowersupply.getSelectionModel().getSelectedItem();
        String hdd = choHDD.getSelectionModel().getSelectedItem();
        String ssd = choSSD.getSelectionModel().getSelectedItem();
        String cpuFan = choCPUfan.getSelectionModel().getSelectedItem();
        String fans = choFans.getSelectionModel().getSelectedItem();
        String casemods = choCasemods.getSelectionModel().getSelectedItem();
        String monitor = choMonitor.getSelectionModel().getSelectedItem();
        String keyborad = choKeyboard.getSelectionModel().getSelectedItem();
        String mouse = choMouse.getSelectionModel().getSelectedItem();
        String headphones = choHeadphones.getSelectionModel().getSelectedItem();

        int counter = 1;

        //Sjekker om de forskjellige choiceBoxene har en verdi og henter produktnavn
        if(!choCabinet.getSelectionModel().isEmpty()) {
            if(observableList.contains(getProductNames(cabinett))){
                getProductNames(cabinett).setNewQuantity(counter);
            }
            else{
                observableList.add(getProductNames(cabinett));
            }
        }

        if(!choMotherboard.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(motherBoard))){
                getProductNames(motherBoard).setNewQuantity(counter);
            }
            else {
                observableList.add(getProductNames(motherBoard));
            }
        }

        if(!choProcessor.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(processor))){
                getProductNames(processor).setNewQuantity(counter);
            }
            else {
                observableList.add(getProductNames(processor));
            }
        }

        if(!choGraphicscard.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(graphicsCard))){
                getProductNames(graphicsCard).setNewQuantity(counter);
            }
            else{
                observableList.add(getProductNames(graphicsCard));
            }
        }

        if(!choMemory.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(memory))){
                getProductNames(memory).setNewQuantity(counter);
            }
            else {
                observableList.add(getProductNames(memory));
            }
        }

        if(!choPowersupply.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(powersupply))){
                getProductNames(powersupply).setNewQuantity(counter);
            }
            else{
                observableList.add(getProductNames(powersupply));
            }
        }

        if(!choHDD.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(hdd))){
                getProductNames(hdd).setNewQuantity(counter);
            }
            else {
                observableList.add(getProductNames(hdd));
            }
        }

        if(!choSSD.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(ssd))){
                getProductNames(ssd).setNewQuantity(counter);
            }
            else{
                observableList.add(getProductNames(ssd));
            }
        }

        if(!choCPUfan.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(cpuFan))){
                getProductNames(cpuFan).setNewQuantity(counter);
            }
            else{
                observableList.add(getProductNames(cpuFan));
            }
        }

        if(!choFans.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(fans))){
                getProductNames(fans).setNewQuantity(counter);
            }
            else {
                observableList.add(getProductNames(fans));
            }
        }

        if(!choCasemods.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(casemods))){
                getProductNames(casemods).setNewQuantity(counter);
            }
            else{
                observableList.add(getProductNames(casemods));
            }
        }

        if(!choMonitor.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(monitor))){
                getProductNames(monitor).setNewQuantity(counter);
            }
            else {
                observableList.add(getProductNames(monitor));
            }
        }

        if(!choKeyboard.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(keyborad))){
                getProductNames(keyborad).setNewQuantity(counter);
            }
            else{
                observableList.add(getProductNames(keyborad));
            }
        }

        if(!choMouse.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(mouse))){
                getProductNames(mouse).setNewQuantity(counter);
            }
            else {
                observableList.add(getProductNames(mouse));
            }
        }

        if(!choHeadphones.getSelectionModel().isEmpty()){
            if(observableList.contains(getProductNames(headphones))){
                getProductNames(headphones).setNewQuantity(counter);
            }
            else {
                observableList.add(getProductNames(headphones));
            }
        }

        //Sender komponentene til handlekurven
        TVcart.setItems(observableList);
        totalPrice(TVcart,lblTotPris);


        colNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Products, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Products, String> param) {
                return new ReadOnlyObjectWrapper<>(TVcart.getItems().indexOf(param.getValue()) + 1 +"");

            }
        });    

        for(int i = 0; i <=14; i++){
            if(!choCabinet.getItems().isEmpty() && !choMotherboard.getItems().isEmpty() && !choProcessor.getItems().isEmpty()
            && !choGraphicscard.getItems().isEmpty() && !choMemory.getItems().isEmpty() && !choPowersupply.getItems().isEmpty()
            && !choHDD.getItems().isEmpty() && !choSSD.getItems().isEmpty() && !choCPUfan.getItems().isEmpty() && !choFans.getItems().isEmpty()
            && !choCasemods.getItems().isEmpty() && !choMonitor.getItems().isEmpty() && !choKeyboard.getItems().isEmpty() && !choMouse.getItems().isEmpty()
            && !choHeadphones.getItems().isEmpty()){

                try{
                String number = String.valueOf(colNumber.getCellObservableValue(i).getValue());
                String name  = colName.getCellObservableValue(i).getValue();
                String type = colType.getCellObservableValue(i).getValue();
                int quantity = colQuantity.getCellObservableValue(i).getValue();
                int sotrage = colStorage.getCellObservableValue(i).getValue();
                String priceAsString = String.valueOf(colPrice.getCellObservableValue(i).getValue());


                Double price = Double.parseDouble(priceAsString);

                Products newProducts = new Products(number,name,type,quantity,price, sotrage);
                CartRegister.addElement(newProducts);
            }
                catch (NullPointerException e){
                    e.getSuppressed();
                }

                }
        }
        btnRefresh.fire();
        colNumber.setSortable(false);

    }

    public Products getProductNames(String typeName) {
        for (List<Products> cartList : data.values()){
            for (Products products : cartList){
                if(products.getTxtName().equals(typeName)){
                    return products;
                }
            }
        }
        return null;
    }

    ObservableList<String> getComponentNames(String componentType, Map<String, List<Products>> newData) {
        List<Products> kabinettComponents = newData.get(componentType);
        ObservableList<String> kabinettNames = FXCollections.observableArrayList();
        if (kabinettComponents != null) {
            for (Products prod : kabinettComponents) {
                kabinettNames.add(prod.getTxtName());
            }
        }
        return kabinettNames;
    }

    @FXML
    void LoadData() throws IOException {
        data = cdh.load();
        updatedData();
    }


    @FXML
    void resetChoiceBoxes(ActionEvent event) {
        int counter = 1;
        String cabinetCount = choCabinet.getSelectionModel().getSelectedItem();
        String motherboardCount = choMotherboard.getSelectionModel().getSelectedItem();
        String proessorCount = choProcessor.getSelectionModel().getSelectedItem();
        String graphicsCount = choGraphicscard.getSelectionModel().getSelectedItem();
        String memoryCount = choMemory.getSelectionModel().getSelectedItem();
        String powerCount = choPowersupply.getSelectionModel().getSelectedItem();
        String hddCount = choHDD.getSelectionModel().getSelectedItem();
        String ssdCount = choSSD.getSelectionModel().getSelectedItem();
        String cpuFanCount = choCPUfan.getSelectionModel().getSelectedItem();
        String fansCount = choFans.getSelectionModel().getSelectedItem();
        String casemodsCount = choCasemods.getSelectionModel().getSelectedItem();
        String monitorCount = choMonitor.getSelectionModel().getSelectedItem();
        String keyboardCount = choKeyboard.getSelectionModel().getSelectedItem();
        String mouseCount = choMouse.getSelectionModel().getSelectedItem();
        String headphonesCount = choHeadphones.getSelectionModel().getSelectedItem();

        if(!choCabinet.getSelectionModel().isEmpty()){getProductNames(cabinetCount).setTxtQuantity(counter); choCabinet.setValue(null);}
        if(!choMotherboard.getSelectionModel().isEmpty()){getProductNames(motherboardCount).setTxtQuantity(counter); choMotherboard.setValue(null);}
        if(!choProcessor.getSelectionModel().isEmpty()){getProductNames(proessorCount).setTxtQuantity(counter); choProcessor.setValue(null);}
        if(!choGraphicscard.getSelectionModel().isEmpty()){getProductNames(graphicsCount).setTxtQuantity(counter); choGraphicscard.setValue(null);}
        if(!choMemory.getSelectionModel().isEmpty()){getProductNames(memoryCount).setTxtQuantity(counter); choMemory.setValue(null);}
        if(!choPowersupply.getSelectionModel().isEmpty()){getProductNames(powerCount).setTxtQuantity(counter); choPowersupply.setValue(null);}
        if(!choHDD.getSelectionModel().isEmpty()){getProductNames(hddCount).setTxtQuantity(counter); choHDD.setValue(null);}
        if(!choSSD.getSelectionModel().isEmpty()){getProductNames(ssdCount).setTxtQuantity(counter); choSSD.setValue(null);}
        if(!choCPUfan.getSelectionModel().isEmpty()){getProductNames(cpuFanCount).setTxtQuantity(counter); choCPUfan.setValue(null);}
        if(!choFans.getSelectionModel().isEmpty()){getProductNames(fansCount).setTxtQuantity(counter); choFans.setValue(null);}
        if(!choCasemods.getSelectionModel().isEmpty()){getProductNames(casemodsCount).setTxtQuantity(counter); choCasemods.setValue(null);}
        if(!choMonitor.getSelectionModel().isEmpty()){getProductNames(monitorCount).setTxtQuantity(counter); choMonitor.setValue(null);}
        if(!choKeyboard.getSelectionModel().isEmpty()){getProductNames(keyboardCount).setTxtQuantity(counter); choKeyboard.setValue(null);}
        if(!choMouse.getSelectionModel().isEmpty()){getProductNames(mouseCount).setTxtQuantity(counter); choMouse.setValue(null);}
        if(!choHeadphones.getSelectionModel().isEmpty()){getProductNames(headphonesCount).setTxtQuantity(counter); choHeadphones.setValue(null);}

        for (int i = 0; i <TVcart.getItems().size(); i++){
            TVcart.getItems().clear();
        }
        btnRefresh.fire();
    }

    @FXML
    private void filterField(KeyEvent ke){

        //Henter samme som observableList fra tidligere.
        FilteredList<Products> filteredData = new FilteredList<>(observableList, p -> true);

        //Setter filter Predicate for hver gang filteret endres
        txtFiltered.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(products -> {
                //Hvis filteret er tomt --> viser alle produkter
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                //Sammenligner kolonnene med filteret
                String lowerCaseFilter = newValue.toLowerCase();

                if(products.getTxtNumber().toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matcher nummerKolonnen.
                }
                else if(products.getTxtName().toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matcher navnKolonnen.
                }
                else if(products.getTxtType().toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matcher typeKolonnen.
                }
                else if(String.valueOf(products.getTxtQuantity()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matcher antallKolonnen. //Trenger antageligvis ikke .toLowerCase() her..
                }
                else if(String.valueOf(products.getTxtPrice()).toLowerCase().contains(lowerCaseFilter)){
                    return true; //Filter matcher prisKolonnen. //Trenger antageligvis ikke .toLowerCase() her..
                }
                return false; //Filter matcher ingen av kolonnene.
            });
        });

        //Wrapper filteredList inn i en sortedList
        SortedList<Products> sortedList = new SortedList<>(filteredData);

        //Binder sortedList sammenligner til tableview
        sortedList.comparatorProperty().bind(TVcart.comparatorProperty());

        //Setter matchende produkter til tableViewet og viser dem.
        TVcart.setItems(sortedList);
    }

    @FXML
    void updatedData() {
        ObservableList<String> cabinetNames = getComponentNames(ComponentType.CABINET,data);
        ObservableList<String> motherBoardNames = getComponentNames(ComponentType.MAINCARD,data);
        ObservableList<String> processorNames = getComponentNames(ComponentType.PROCESSOR,data);
        ObservableList<String> graphicsCardNames = getComponentNames(ComponentType.VIDEOCARD,data);
        ObservableList<String> memoryNames = getComponentNames(ComponentType.MEMORY,data);
        ObservableList<String> powerSupplyNames = getComponentNames(ComponentType.POWERSUPPLY,data);
        ObservableList<String> hddNames = getComponentNames(ComponentType.HDD,data);
        ObservableList<String> ssdNames = getComponentNames(ComponentType.HARDDRIVE,data);
        ObservableList<String> cpuFanNames = getComponentNames(ComponentType.PROSESSOR_FAN_NAMES,data);
        ObservableList<String> fansNames = getComponentNames(ComponentType.FANS,data);
        ObservableList<String> casemodsNames = getComponentNames(ComponentType.CASEMODS,data);
        ObservableList<String> monitorNames = getComponentNames(ComponentType.SCREEN,data);
        ObservableList<String> keyBoardNames = getComponentNames(ComponentType.KEYBOARD,data);
        ObservableList<String> mouseNames = getComponentNames(ComponentType.MOUSE,data);
        ObservableList<String> headphonesNames = getComponentNames(ComponentType.HEADSET,data);

        choCabinet.setItems(cabinetNames);
        choMotherboard.setItems(motherBoardNames);
        choProcessor.setItems(processorNames);
        choGraphicscard.setItems(graphicsCardNames);
        choMemory.setItems(memoryNames);
        choPowersupply.setItems(powerSupplyNames);
        choHDD.setItems(hddNames);
        choSSD.setItems(ssdNames);
        choCPUfan.setItems(cpuFanNames);
        choFans.setItems(fansNames);
        choCasemods.setItems(casemodsNames);
        choMonitor.setItems(monitorNames);
        choKeyboard.setItems(keyBoardNames);
        choMouse.setItems(mouseNames);
        choHeadphones.setItems(headphonesNames);
    }


    @FXML
    void LagreOnskeliste (ActionEvent event)throws IOException, BadLocationException {
        FileChooser saveAs = new FileChooser();
        File saveFile = saveAs.showSaveDialog(null);
        String formated = ProductParsing.productsFormater(observableList);

        if(saveFile != null){
            Path filePath = Paths.get(saveFile.getAbsolutePath());
            try{
                WriteToFile.writeToString(filePath,formated);
            }
            catch (IOException e) {
                e.printStackTrace();
                System.err.println("Noe gikk galt");
            }
        }
    }

    @FXML
    void OpenWishList(ActionEvent event) throws IOException {
        FileChooser openFile = new FileChooser();
        File chosenFile = openFile.showOpenDialog(null);
        Path filePath = Paths.get(chosenFile.getAbsolutePath());
        List<Products> list = ReadFromFile.openFile(filePath);
        refreshPrice(event);


        colNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Products, String>, ObservableValue<String>>() { 
            @Override                                                                                                           
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Products, String> param) {
                return new ReadOnlyObjectWrapper<>(TVcart.getItems().indexOf(param.getValue()) + 1 +"");                        
                                                                                                                                
            }                                                                                                                   
        });                                                                                                                     
    }

    @FXML
    void CompleteOrder(ActionEvent event){
        btnReset.fire();

        try {
             Parent PCByggingParent = FXMLLoader.load(java.util.Objects.requireNonNull(getClass().getClassLoader().getResource("CompleteOrder/OrderDone.fxml")));
             Scene PCByggingScene = new Scene(PCByggingParent);

            //Denne linjen henter stage info
            Stage PCWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            PCWindow.setTitle("Fullfør ordre");
            PCWindow.setScene(PCByggingScene);
            Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
            PCWindow.setX((screenSize.getWidth() - PCWindow.getWidth()) / 2);
            PCWindow.setY((screenSize.getHeight() - PCWindow.getHeight()) / 2);
            PCWindow.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void CloseProgram(ActionEvent event){
        Stage lukkProgram = (Stage) btnLukkProgram.getScene().getWindow();
        lukkProgram.close();

    }
}


