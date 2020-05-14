package ProductWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ProductWindow.fxml"));
        primaryStage.setTitle("Legg til komponent");
        primaryStage.setScene(new Scene(root, 250, 330));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        new Component_DataHandler();
    }
}