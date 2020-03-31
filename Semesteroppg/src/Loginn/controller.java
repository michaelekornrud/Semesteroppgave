package Loginn;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

class logInn {
    public String adminUser = "admin";
    public String adminPW = "admin";
    public String checkUser, checkPW;

    public logInn (String adminUser, String adminPW, String  checkUser, String checkPW){
        this.adminUser = adminUser;
        this.adminPW = adminPW;
        this.checkUser = checkUser;
        this.checkPW = checkPW;

    }
}

public class controller {

    @FXML
    private TextField txtBrukernavn;

    @FXML
    private TextField  txtPassord;

    @FXML
    private Button btnLogInn;

    @FXML
    private Button btnLukk;

    @FXML
    private Button btnRegistrer;

    @FXML
    void LogInn (ActionEvent event) throws IOException {
        String adminUser = "admin";
        String adminPW = "admin";
        String brukerUser = "user";
        String brukerPW = "user";
        String checkUser, checkPW;

        checkUser = txtBrukernavn.getText().toString();
        checkPW =  txtPassord.getText().toString();
        if(checkUser.equals(adminUser) && checkPW.equals(adminPW) || checkUser.equals(brukerUser) && checkPW.equals(brukerPW)){
            try {
                Parent PCByggingParent = FXMLLoader.load(getClass().getClassLoader().getResource("PCBygging/PCBygging.fxml"));
                Scene PCByggingScene = new Scene(PCByggingParent);

                //Denne linjen henter stage info
                Stage PCWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
                PCWindow.setScene(PCByggingScene);
                PCWindow.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        else{
            throw new IOException("Feil i brukernavn/passord");
        }



    }

    @FXML
    void LukkProgram (ActionEvent event){
        Stage lukkProgram = (Stage) btnLukk.getScene().getWindow();
        lukkProgram.close();
    }

    @FXML
    void Registrer (ActionEvent event){

    }

}
