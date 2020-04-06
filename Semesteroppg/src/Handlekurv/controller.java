package Handlekurv;

import javafx.fxml.FXML;

import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.soap.Node;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class controller implements Initializable {

    NewData addNewComponents = new NewData();

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        addNewComponents.attachListView(LWHandlekurv);
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
    private ListView LWHandlekurv;

    @FXML
    void LagreOnskeliste (ActionEvent event){

    }

    @FXML
    void OpenØnskeliste (ActionEvent event) throws IOException {
        FileChooser openFile = new FileChooser();
        File chosenFile = openFile.showOpenDialog(null);

        Path filePath = Paths.get(chosenFile.getAbsolutePath());
        List<Objects> list = ÅpneHandlekurv.openFile(filePath);
    }

    @FXML
    void Betal (ActionEvent event){

    }

    @FXML
    void Tilbake (ActionEvent event){

    }

    @FXML
    void LukkProgram (ActionEvent event){

    }
}
