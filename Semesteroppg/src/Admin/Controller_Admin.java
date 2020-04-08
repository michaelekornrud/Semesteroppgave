package Admin;

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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;



public class Controller_Admin {

    Component_DataHandler cdh = new Component_DataHandler();

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
    private ChoiceBox<String> choHeadsett;


    @FXML
    public void initialize() throws IOException {
        LoadData();
    }


    @FXML
    void addComponent (ActionEvent event) throws IOException {
        try {
            Parent PCByggingParent = FXMLLoader.load(getClass().getClassLoader().getResource("ProductWindow/productWindow.fxml"));
            Scene PCByggingScene = new Scene(PCByggingParent);

            //Denne linjen henter stage info
            Stage PCWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            PCWindow.setTitle("Legg til komponent");
            PCWindow.setScene(PCByggingScene);
            PCWindow.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void Handlekurv (ActionEvent event) throws IOException {
        try {
            Parent PCByggingParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Handlekurv/kurven.fxml")));
            Scene PCByggingScene = new Scene(PCByggingParent);

            //Denne linjen henter stage info
            Stage PCWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            PCWindow.setTitle("Handlekurv");
            PCWindow.setScene(PCByggingScene);
            PCWindow.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void updatedData(Map<String, List<Product>> newData) throws IOException{ //Metode for å legge inn verdi i choicebox kabinett
        //List<BaseComponent> kabinettComponents = newData.get(ComponentType.KABINETT);
        ObservableList<String> kabinettNames = getCoponentNames(ComponentType.KABINETT, newData);
        ObservableList<String> ProsessorNames = getCoponentNames(ComponentType.PROCESSOR, newData);
        ObservableList<String> HarddiskNames = getCoponentNames(ComponentType.HARDDISK, newData);
        ObservableList<String> MainCardNames = getCoponentNames(ComponentType.MAINCARD, newData);
        ObservableList<String> ScreenCardNames = getCoponentNames(ComponentType.SKJERMKORT, newData);
        ObservableList<String> MemoryNames = getCoponentNames(ComponentType.MINNE, newData);
        ObservableList<String> PowerSupplyNames = getCoponentNames(ComponentType.STRØMFORSKYVNING, newData);
        ObservableList<String> ProsessorFanNames = getCoponentNames(ComponentType.PROSESSOR_FAN_NAMES, newData);
        ObservableList<String> FanNames = getCoponentNames(ComponentType.VIFTER, newData);
        ObservableList<String> CaseModkNames = getCoponentNames(ComponentType.CASEMODS, newData);
        ObservableList<String> ScreenNames = getCoponentNames(ComponentType.SKJERM , newData);
        ObservableList<String> KeyboadNames = getCoponentNames(ComponentType.TASTATUR, newData);
        ObservableList<String> HeadSetNames = getCoponentNames(ComponentType.HODETELEFONER, newData);
        ObservableList<String> MouseNames = getCoponentNames(ComponentType.MUS , newData);

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

        /*FXCollections.observableArrayList();;*/
        /*for (BaseComponent comp : kabinettComponents){
            kabinettNames.add(comp.getName());
        }*/



    }

    @FXML
    void LoadData() throws IOException {
        Map<String, List<Product>> newData = cdh.load();
        updatedData(newData);
    }



    ObservableList<String> getCoponentNames(String componentType, Map<String, List<Product>> newData ){

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
