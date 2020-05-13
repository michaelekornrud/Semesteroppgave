package CompleteOrder;

import ProductWindow.Component_DataHandler;
import ProductWindow.Product;
import User.Products;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.Map;
import User.controller_User;

//import static javax.swing.JOptionPane.showMessageDialog;


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
    private Button btnFullfør;

    @FXML
    private Button btnTilbake;



    @FXML
    void Fullfør(ActionEvent event) throws Exception {

        String navn = txtFornavn.getText() + " " + txtEtternavn.getText();
        String adresse = txtAdresse.getText();
        String post  = txtPostnummer.getText() + " " + txtPoststed.getText();

        String ut = "Ordren blir sendt til:" + "\n" + navn + "\n" + adresse + "\n" + post;

        AlertBox.display("Fullført", ut);

        //cdh.removeAmount(data);


        ObservableList<Products>  temp = controller_User.observableList;






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



