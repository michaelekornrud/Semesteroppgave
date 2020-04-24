package FullførOrdre;

import javafx.event.ActionEvent;
import static javax.swing.JOptionPane.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

//import static javax.swing.JOptionPane.showMessageDialog;


public class controller {

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
    void Fullfør(ActionEvent event) {

        String navn = txtFornavn.getText() + " " + txtEtternavn.getText();
        String adresse = txtAdresse.getText();
        String post  = txtPostnummer.getText() + " " + txtPoststed.getText();

        String ut = "Ordren blir sendt til:" + "\n" + navn + "\n" + adresse + "\n" + post;

        AlertBox.display("Fullført", ut);



    }

    @FXML
    void Tilbake(ActionEvent event) {
        try {
            Parent PCByggingParent = FXMLLoader.load(java.util.Objects.requireNonNull(getClass().getClassLoader().getResource("Bruker/bruker.fxml")));
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



