package Loginn;

import CompleteOrder.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
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
            AlertBox.display("Velkommen!", "Velkommen!\nI denne delen av programmet kan du laste inn og endre/slette komponenter fra sortementet\nNår lukknappen vises er programmet ferdig lastet inn og klar til bruk", 3000);
            try {
                Parent PCByggingParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Admin/PCBygging.fxml")));
                Scene PCByggingScene = new Scene(PCByggingParent);

                //Denne linjen henter stage info
                Stage PCWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                PCWindow.setScene(PCByggingScene);
                PCWindow.centerOnScreen();
                PCWindow.setTitle("Build your own PC");

                Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
                PCWindow.setX((screenSize.getWidth() - PCWindow.getWidth()) / 2);
                PCWindow.setY((screenSize.getHeight() - PCWindow.getHeight()) / 2);
                PCWindow.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else if (checkUser.equals(brukerUser) && checkPW.equals(brukerPW)){
            AlertBox.display("Velkommen!", "Velkommen\nI dette programmet kan du konfigurere din egen PC etter dine ønsker.\nNår lukknappen vises er programmet ferdig lastet inn og klar til bruk", 3000);
            try {
                Parent PCByggingParent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("User/user.fxml")));
                Scene PCByggingScene = new Scene(PCByggingParent);

                //Denne linjen henter stage info
                Stage PCWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                PCWindow.centerOnScreen();
                PCWindow.setScene(PCByggingScene);
                PCWindow.setTitle("Build your own PC");

                Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
                PCWindow.setX((screenSize.getWidth() - PCWindow.getWidth()) / 2);
                PCWindow.setY((screenSize.getHeight() - PCWindow.getHeight()) / 2);
                PCWindow.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            AlertBox.display("Oops!..." , "Feil i brukernavn / passord",500);
        }

    }

    public void lblProbLogIn(MouseEvent event){
        AlertBox.display("For å logge inn:" , "Det er to brukere registrert:\nFor å logge inn som admin, bruk brukernavn og passord: 'admin'.\nFor å logge inn som bruker, bruk brukernavn og passord: 'user'.", 0);
    }

    @FXML
    void LukkProgram(){
        Stage lukkProgram = (Stage) btnLukk.getScene().getWindow();
        lukkProgram.close();
    }



}
