package Handlekurv;

import Admin.Controller_Admin;
import ProductWindow.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.text.BadLocationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class controller implements Initializable  {

    NewData addNewComponents = new NewData();

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        try {
            NewData.attachListView(LWHandlekurv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    public static ListView LWHandlekurv;

    public static void addtoLv(ObservableList<Product> o){
        ObservableList liste = FXCollections.observableArrayList();
        liste.add(o);

        LWHandlekurv.setItems(liste);

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
        List<Objects> list = ÅpneHandlekurv.openFile(filePath);
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
            Parent PCByggingParent = FXMLLoader.load(java.util.Objects.requireNonNull(getClass().getClassLoader().getResource("Admin/PCBygging.fxml")));
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
