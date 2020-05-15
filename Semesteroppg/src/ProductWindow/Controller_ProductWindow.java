package ProductWindow;

import Exceptions.ProductValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;



public class Controller_ProductWindow implements Initializable {

    public ObservableList<String> componentType = FXCollections.observableArrayList(ComponentType.CABINET, ComponentType.CASEMODS, ComponentType.PROSESSOR_FAN_NAMES, ComponentType.HDD
    , ComponentType.HARDDRIVE, ComponentType.HEADSET, ComponentType.MAINCARD, ComponentType.MEMORY, ComponentType.MOUSE, ComponentType.PROCESSOR, ComponentType.SCREEN
    , ComponentType.POWERSUPPLY, ComponentType.KEYBOARD, ComponentType.FANS, ComponentType.VIDEOCARD);
    //Liste med componenttypene slik at man kan bruke denne listen til å lage nye produktet samt sjekke om typen er riktig

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        choiceType.setItems(componentType);
    }

    Component_DataHandler cdh = new Component_DataHandler();

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtNumberOfProducts;

    @FXML
    private ChoiceBox<String> choiceType;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtPrice;


    @FXML
    void btnAdd(ActionEvent event) throws IOException { //Knapp for å legge til nytt produkt i både tableview og choiceboksene
        String name = txtProductName.getText();
        String brand = txtBrand.getText();
        String stringNumberOfProducts = txtNumberOfProducts.getText();
        int numberOfProducts = Integer.parseInt(stringNumberOfProducts);
        String stringPrice = txtPrice.getText();
        double price = Double.parseDouble(stringPrice);
        String value = choiceType.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nytt produkt!");
        alert.setHeaderText("Produktet " +name+", med verdiene:\nMerke: "+brand+"\nAntall: "+numberOfProducts+"\nPris: "+price +" kr,-\ner å lagt til i boksen med" +
                " typenavn: "+value);
        alert.showAndWait();


        Product newProduct = createProductObjectFromGUI();
        ProductRegister.addElement(newProduct);
        cdh.write(newProduct);
        resetTxtFields();



    }

    @FXML
    void back(ActionEvent event) { //Knapp for å gå tilbake til admin-vinduet
        try {
            Parent PCByggingParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Admin/PCBygging.fxml")));
            Scene PCByggingScene = new Scene(PCByggingParent);

             //Denne linjen henter stage info
             Stage PCWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
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
    private Product createProductObjectFromGUI(){ //Metode for å lage et produkt fra guiet.
        String uniqueID = UUID.randomUUID().toString();
        String name = txtProductName.getText();
        String brand = txtBrand.getText();
        String stringNumberOfProducts = txtNumberOfProducts.getText();
        int numberOfProducts = Integer.parseInt(stringNumberOfProducts);
        String stringPrice = txtPrice.getText();
        double price = Double.parseDouble(stringPrice);
        String value = choiceType.getSelectionModel().getSelectedItem();


            return new Product(uniqueID
                                ,ProductValidator.testProductName(name)
                                ,ProductValidator.testNumberOfProducts(numberOfProducts)
                                ,ProductValidator.testProductBrand(brand)
                                ,ProductValidator.testPrice(price)
                                ,ProductValidator.testProductType(value));
    }

    @FXML
    private void resetTxtFields() { //metode for å resette tekstfeltene
        txtProductName.setText("");
        txtNumberOfProducts.setText("");
        txtBrand.setText("");
        txtPrice.setText("");
    }

}

