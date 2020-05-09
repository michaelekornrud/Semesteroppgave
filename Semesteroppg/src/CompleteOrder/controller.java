package CompleteOrder;


import ProductWindow.Component_DataHandler;
import ProductWindow.Product;
import User.ComponentDataHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.Map;

//import static javax.swing.JOptionPane.showMessageDialog;


public class controller  {

    ComponentDataHandler cdh = new ComponentDataHandler();
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

        String firstName = txtFornavn.getText();
        String lastName = txtEtternavn.getText();
        String adress = txtAdresse.getText();
        int postNumber = Integer.parseInt(txtPostnummer.getText());
        String city = txtPoststed.getText();

        if (!txtFornavn.getText().isEmpty() && !txtEtternavn.getText().isEmpty() && !txtAdresse.getText().isEmpty()
        && !txtPostnummer.getText().isEmpty() && !txtPoststed.getText().isEmpty()){

            String navn = Deviations.checkName(firstName) + " " + Deviations.checkName(lastName);
            String adresse = Deviations.checkAdress(adress);
            int post = Deviations.checkPostNumber(postNumber);
            String by = Deviations.checkCity(city);

        String ut = "Ordren blir sendt til:" + "\n" + navn + "\n" + adresse + "\n" + post + " " + by;

        AlertBox.display("Fullført", ut);

            try {
                Parent PCByggingParent = FXMLLoader.load(java.util.Objects.requireNonNull(getClass().getClassLoader().getResource("User/user.fxml")));
                Scene PCByggingScene = new Scene(PCByggingParent);

                //Denne linjen henter stage info
                Stage PCWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                PCWindow.setTitle("Bygg din egen PC");
                PCWindow.setScene(PCByggingScene);
                PCWindow.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }


        //cdh.removeAmount(data);

    }





    public controller() throws FileNotFoundException {
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



