package CompleteOrder;

import ProductWindow.Component_DataHandler;
import ProductWindow.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class controller_CO {


    Component_DataHandler cdh = new Component_DataHandler();
    private Map<String, List<Product>> data;
    private String csvFile;

    @FXML
    private TextField txtFornavn;

    @FXML
    private TextField txtEtternavn;

    @FXML
    private TextField txtAdresse;

    @FXML
    private TextField txtPostnummer;

    @FXML
    private TextField txtPoststed;


    @FXML
    void Complete(ActionEvent event) throws Exception {

        String firstname = txtFornavn.getText();
        String lastname = txtEtternavn.getText();
        String adress = txtAdresse.getText();
        String postNUmber  = txtPostnummer.getText();
        int post = Integer.parseInt(postNUmber);

        String city = txtPoststed.getText();

        Deviations.checkName(firstname);
        Deviations.checkName(lastname);
        Deviations.checkAdress(adress);
        Deviations.checkPostNumber(post);
        Deviations.checkCity(city);

        String ut = "Ordren blir sendt til:" + "\n" + firstname + " " + lastname + "\n" + adress + "\n" + post + " " + city;

        AlertBox.display("Fullført", ut);

        try {
            Parent PCByggingParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("User/user.fxml")));
            Scene PCByggingScene = new Scene(PCByggingParent);

            //Denne linjen henter stage info
            Stage PCWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
            PCWindow.setX(100);
            PCWindow.setY(100);
            PCWindow.setScene(PCByggingScene);
            PCWindow.setTitle("Build your own PC");
            PCWindow.show();


        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public controller_CO()
    {
        String projectDirectory = System.getProperty("user.dir");
        csvFile = projectDirectory + "/Semesteroppg/src/Data/comptypes.csv";
        cdh.load();
    }

    @FXML
    void Tilbake(ActionEvent event) {
        try {
            Parent PCByggingParent = FXMLLoader.load(java.util.Objects.requireNonNull(getClass().getClassLoader().getResource("User/user.fxml")));
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

}



