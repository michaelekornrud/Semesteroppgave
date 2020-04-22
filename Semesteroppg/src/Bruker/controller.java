package Bruker;

import Handlekurv.CartFormater;
import Handlekurv.LagreHandlekurv;
import Handlekurv.NewData;
import Handlekurv.ÅpneHandlekurv;
import ProductWindow.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.text.BadLocationException;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class controller extends Controller_ProductWindow {

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
    private Button btnTilbake;

    @FXML
    private Button btnLukkProgram;

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
    private TableColumn<Products, String> colQuantity;

    @FXML
    private TableColumn<Products, String> colPrice;

    @FXML
    private TableColumn<Products, String> colTotPrice;

    public controller() throws FileNotFoundException {
        super();
    }

    @FXML
    void Handlekurv (ActionEvent event) throws IOException {

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

        ObservableList<Products> observableList = FXCollections.observableArrayList();

        observableList.add(getProductNames(cabinett));
        observableList.add(getProductNames(motherBoard));
        observableList.add(getProductNames(processor));
        observableList.add(getProductNames(graphicsCard));
        observableList.add(getProductNames(memory));
        observableList.add(getProductNames(powersupply));
        observableList.add(getProductNames(memory));
        observableList.add(getProductNames(hdd));
        observableList.add(getProductNames(ssd));
        observableList.add(getProductNames(cpuFan));
        observableList.add(getProductNames(fans));
        observableList.add(getProductNames(casemods));
        observableList.add(getProductNames(monitor));
        observableList.add(getProductNames(keyborad));
        observableList.add(getProductNames(mouse));
        observableList.add(getProductNames(headphones));

        TVcart.setItems(observableList);


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
        resetchoiceBoxes();



    }

    @FXML
    private void resetchoiceBoxes() {
        choCabinet.setValue("");
        choMotherboard.setValue("");
        choProcessor.setValue("");
        choGraphicscard.setValue("");
        choMemory.setValue("");
        choPowersupply.setValue("");
        choHDD.setValue("");
        choSSD.setValue("");
        choCPUfan.setValue("");
        choFans.setValue("");
        choCasemods.setValue("");
        choMonitor.setValue("");
        choKeyboard.setValue("");
        choMouse.setValue("");
        choHeadphones.setValue("");

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
        String formated = CartFormater.cartFormat(NewData.liste);

        if (saveFile != null){
            Path filePath = Paths.get(saveFile.getAbsolutePath());

            try {
                LagreHandlekurv.writeToFile(filePath,formated);
            }
            catch (IOException e){
                System.err.println("Noe gikk galt");
                e.printStackTrace();
            }
        }

    }

    @FXML
    void OpenØnskeliste (ActionEvent event) throws IOException {
        FileChooser openFile = new FileChooser();
        File chosenFile = openFile.showOpenDialog(null);

        Path filePath = Paths.get(chosenFile.getAbsolutePath());
        List<Handlekurv.Objects> list = ÅpneHandlekurv.openFile(filePath);
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


