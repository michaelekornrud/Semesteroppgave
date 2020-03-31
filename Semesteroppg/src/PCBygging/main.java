package PCBygging;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PCBygging.fxml"));
        primaryStage.setTitle("Lag din egen PC");
        primaryStage.setScene(new Scene(root, 350, 250));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

