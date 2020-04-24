package Bruker;

import Exceptions.ProductValidator;

import ProductWindow.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.text.BadLocationException;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class controller extends Controller_ProductWindow {


    CartRegister addNewProduct = new CartRegister();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        addNewProduct.attachToTableView(TVcart);
    }

    ComponentDataHandler cdh = new ComponentDataHandler();
    private Map<String, List<Products>> data;

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
    private Button btnLagre;

    @FXML
    private Button btnBetaling;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnLukkProgram;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnOpenØnskeliste;

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
    private TableColumn<Products, String> colPrice;

    @FXML
    private Label lblPris;

    @FXML
    private Label lblMva;

    @FXML
    private Label lblTotPris;
    int sum = 0;

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

        lblTotPris.setText((totalPrice + "0"));
        lblMva.setText((mva + "0"));
        lblPris.setText((price + "0"));



    }

    /*Lag en metode som reseter choicebox når et produkt blir fjernet fra handlekurven*/



    public controller() throws FileNotFoundException {
    }

    @FXML
    public void refreshPrice(ActionEvent event){ //Funker ikke helt som den skal..
        sum = 0;

        for(Products price : TVcart.getItems()){
            Double priceUpdated = Double.parseDouble(String.valueOf(colPrice.getCellObservableValue(price).getValue()));
            int quantity = Integer.parseInt(String.valueOf(colQuantity.getCellObservableValue(price).getValue()));
            Double newPrice = quantity * priceUpdated;
            sum += newPrice;
            System.out.println("Quantity" + quantity);
            System.out.println("PriceUpdated: " + priceUpdated);
        }

        int mva = (sum * 25)/ 100;
        int pris = sum - mva;

        //String newPrice = String.valueOf(sum);
        System.out.println(sum);

        lblPris.setText((pris + ".00"));
        lblMva.setText(mva + ".00");
        lblTotPris.setText(sum + ".00");
    }

    @FXML
    public void quantityEdited(TableColumn.CellEditEvent<Products, Integer> event){
       Products products = TVcart.getSelectionModel().getSelectedItem();

       int number = event.getNewValue();
       ProductValidator.testNumberOfProducts(number);
       products.setTxtQuantity(number);
       System.out.println("Fra quantityEdited: " + number);


       TVcart.refresh();

    }

    @FXML
    void deleteRow(ActionEvent event) {
        ObservableList<Products> productChosen, allProducts;
        allProducts = TVcart.getItems();
        productChosen = TVcart.getSelectionModel().getSelectedItems();
        System.out.println(productChosen);
        String  type = String.valueOf(productChosen.get(2));
        System.out.println(type);
       /* for(type : productChosen){

        }*/
        allProducts.removeAll(productChosen);
        /*Mulig denne trengs for å resete choiceboks når et produkt blir fjernet
        String str = TVcart.getColumns().get(2).getCellObservableValue().getValue();
         */

        btnRefresh.fire();



    }







    @FXML
    void Handlekurv (ActionEvent event) throws IOException {

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

        //Oppretter en liste hvor komponent-data blir lagret
        ObservableList<Products> observableList = FXCollections.observableArrayList();


        //Sjekker om de forskjellige choiceBoxene har en verdi og henter produktnavn
        if(!choCabinet.getSelectionModel().isEmpty()) { observableList.add(getProductNames(cabinett)); }
        else{ System.out.println("Ingen verdi valgt i Kabinett"); }
        if(!choMotherboard.getSelectionModel().isEmpty()){ observableList.add(getProductNames(motherBoard));}
        else {System.out.println("Ingen verdi valgt i hovedkort");}
        if(!choProcessor.getSelectionModel().isEmpty()){ observableList.add(getProductNames(processor));}
        else{System.out.println("Ingen verdi er valgt i prosessor");}
        if(!choGraphicscard.getSelectionModel().isEmpty()){ observableList.add(getProductNames(graphicsCard));}
        else{System.out.println("Ingen verdi valgt i grafikkort");}
        if(!choMemory.getSelectionModel().isEmpty()){observableList.add(getProductNames(memory)); }
        else{System.out.println("Ingen verdi valgt i minne");}
        if(!choPowersupply.getSelectionModel().isEmpty()){observableList.add(getProductNames(powersupply)); }
        else{System.out.println("Ingen verdi valgt i størmforskyning");}
        if(!choHDD.getSelectionModel().isEmpty()){observableList.add(getProductNames(hdd)); }
        else{System.out.println("Ingen verdi valgt i harddisk");}
        if(!choSSD.getSelectionModel().isEmpty()){observableList.add(getProductNames(ssd)); }
        else{System.out.println("Ingen verdi valgt i harddisk1");}
        if(!choCPUfan.getSelectionModel().isEmpty()){observableList.add(getProductNames(cpuFan)); }
        else{System.out.println("Ingen verdi valgt i CPU-Vifte");}
        if(!choFans.getSelectionModel().isEmpty()){observableList.add(getProductNames(fans)); }
        else{System.out.println("Ingen verdi valgt i Vifter");}
        if(!choCasemods.getSelectionModel().isEmpty()){observableList.add(getProductNames(casemods)); }
        else{System.out.println("Ingen verdi valgt i Casemods");}
        if(!choMonitor.getSelectionModel().isEmpty()){observableList.add(getProductNames(monitor)); }
        else{System.out.println("Ingen verdi valgt i Skjerm");}
        if(!choKeyboard.getSelectionModel().isEmpty()){observableList.add(getProductNames(keyborad)); }
        else{System.out.println("Ingen verdi valgt i tastatur");}
        if(!choMouse.getSelectionModel().isEmpty()){observableList.add(getProductNames(mouse)); }
        else{System.out.println("Ingen verdi valgt i mus");}
        if(!choHeadphones.getSelectionModel().isEmpty()){observableList.add(getProductNames(headphones)); }
        else{System.out.println("Ingen verdi valgt i hodetelefoner");}

        //Sender komponentene til handlekurven
        TVcart.setItems(observableList);
        totalPrice(TVcart,lblTotPris);

        colNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Products, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Products, String> param) {
                return new ReadOnlyObjectWrapper<>(TVcart.getItems().indexOf(param.getValue()) + 1 +"");
            }
        });


        for(int i = 0; i <=15; i++){
            String number = colNumber.getCellObservableValue(i).getValue();
            String name  = colName.getCellObservableValue(i).getValue();
            String type = colType.getCellObservableValue(i).getValue();
            int quantity = colQuantity.getCellObservableValue(i).getValue();
            String priceAsString = String.valueOf(colPrice.getCellObservableValue(i).getValue());
            Double price = Double.parseDouble(priceAsString);

            Products newProducts = new Products(number,name,type,quantity,price);

            CartRegister.addElement(newProducts);
            System.out.println(newProducts);
        }






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
    public void initialize() throws IOException {
        LoadData();
        CartRegister.attachToTableView(TVcart);
        TVcart.setEditable(true);
        colQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerToString()));
        colQuantity.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow()).setTxtQuantity(Integer.parseInt(String.valueOf(event.getNewValue()))));





    }

    @FXML
    void resetChoiceBoxes(ActionEvent event) {
        if(!choCabinet.getSelectionModel().isEmpty()){choCabinet.setValue(null);}
        if(!choMotherboard.getSelectionModel().isEmpty()){choMotherboard.setValue(null);}
        if(!choProcessor.getSelectionModel().isEmpty()){choProcessor.setValue(null);}
        if(!choGraphicscard.getSelectionModel().isEmpty()){choGraphicscard.setValue(null);}
        if(!choMemory.getSelectionModel().isEmpty()){choMemory.setValue(null);}
        if(!choPowersupply.getSelectionModel().isEmpty()){choPowersupply.setValue(null);}
        if(!choHDD.getSelectionModel().isEmpty()){choHDD.setValue(null);}
        if(!choSSD.getSelectionModel().isEmpty()){choSSD.setValue(null);}
        if(!choCPUfan.getSelectionModel().isEmpty()){choCPUfan.setValue(null);}
        if(!choFans.getSelectionModel().isEmpty()){choFans.setValue(null);}
        if(!choCasemods.getSelectionModel().isEmpty()){choCasemods.setValue(null);}
        if(!choMonitor.getSelectionModel().isEmpty()){choMonitor.setValue(null);}
        if(!choKeyboard.getSelectionModel().isEmpty()){choKeyboard.setValue(null);}
        if(!choMouse.getSelectionModel().isEmpty()){choMouse.setValue(null);}
        if(!choHeadphones.getSelectionModel().isEmpty()){choHeadphones.setValue(null);}

        for (int i = 0; i <TVcart.getItems().size(); i++){
            TVcart.getItems().clear();
        }
        btnRefresh.fire();


    }

    @FXML
    void updatedData() {
        ObservableList<String> cabinetNames = getComponentNames(ComponentType.KABINETT,data);
        ObservableList<String> motherBoardNames = getComponentNames(ComponentType.MAINCARD,data);
        ObservableList<String> processorNames = getComponentNames(ComponentType.PROCESSOR,data);
        ObservableList<String> graphicsCardNames = getComponentNames(ComponentType.SKJERMKORT,data);
        ObservableList<String> memoryNames = getComponentNames(ComponentType.MINNE,data);
        ObservableList<String> powerSupplyNames = getComponentNames(ComponentType.STRØMFORSKYVNING,data);
        ObservableList<String> hddNames = getComponentNames(ComponentType.HDD,data);
        ObservableList<String> ssdNames = getComponentNames(ComponentType.HARDDISK,data);
        ObservableList<String> cpuFanNames = getComponentNames(ComponentType.PROSESSOR_FAN_NAMES,data);
        ObservableList<String> fansNames = getComponentNames(ComponentType.VIFTER,data);
        ObservableList<String> casemodsNames = getComponentNames(ComponentType.CASEMODS,data);
        ObservableList<String> monitorNames = getComponentNames(ComponentType.SKJERM,data);
        ObservableList<String> keyBoardNames = getComponentNames(ComponentType.TASTATUR,data);
        ObservableList<String> mouseNames = getComponentNames(ComponentType.MUS,data);
        ObservableList<String> headphonesNames = getComponentNames(ComponentType.HODETELEFONER,data);

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
        String formated = ProductParsing.productsFormater(CartRegister.list);

        if(saveFile != null){
            Path filePath = Paths.get(saveFile.getAbsolutePath());
            try{
                FileWriter.writeToString(filePath,formated);
            }
            catch (IOException e) {
                e.printStackTrace();
                System.err.println("Noe gikk galt");
            }
        }


    }

    @FXML
    void OpenØnskeliste (ActionEvent event) throws IOException {
        FileChooser openFile = new FileChooser();
        File chosenFile = openFile.showOpenDialog(null);
        Path filePath = Paths.get(chosenFile.getAbsolutePath());
        List<Products> list = FileReader.openFile(filePath);
        refreshPrice(event);

    }

    @FXML
    void FullførOrdre (ActionEvent event){
        try {
            Parent PCByggingParent = FXMLLoader.load(java.util.Objects.requireNonNull(getClass().getClassLoader().getResource("FullførOrdre/Fulfør.fxml")));
            Scene PCByggingScene = new Scene(PCByggingParent);

            //Denne linjen henter stage info
            Stage PCWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            PCWindow.setTitle("Fullfør ordre");
            PCWindow.setScene(PCByggingScene);
            PCWindow.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void Tilbake (ActionEvent event){
        try {
            Parent PCByggingParent = FXMLLoader.load(java.util.Objects.requireNonNull(getClass().getClassLoader().getResource("Bruker/bruker.fxml")));
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
    void LukkProgram (ActionEvent event){
        Stage lukkProgram = (Stage) btnLukkProgram.getScene().getWindow();
        lukkProgram.close();

    }
}


