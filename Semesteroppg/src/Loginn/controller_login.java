package Loginn;

import CompleteOrder.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class controller_login {

    @FXML
    private TextField txtBrukernavn;

    @FXML
    private PasswordField txtPassord;

    @FXML
    private Button btnLukk;


    @FXML
    void LogInn (ActionEvent event) {
        String adminUser = "admin";
        String adminPW = "admin";
        String brukerUser = "user";
        String brukerPW = "user";
        String checkUser, checkPW;

        checkUser = txtBrukernavn.getText();
        checkPW = txtPassord.getText();
        if(checkUser.equals(adminUser) && checkPW.equals(adminPW)){

            try {
                Parent PCByggingParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Admin/PCBygging.fxml")));
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
        else if (checkUser.equals(brukerUser) && checkPW.equals(brukerPW)){
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
        else {
            AlertBox.display("Oops!..." , "Feil i brukernavn / passord");
        }

    }

    @FXML
    void LukkProgram(){
        Stage lukkProgram = (Stage) btnLukk.getScene().getWindow();
        lukkProgram.close();
    }
}
