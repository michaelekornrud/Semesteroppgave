package Admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {


    @Override
    public void start(Stage BuildPCStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("PCBygging.fxml"));
        BuildPCStage.setTitle("Lag din egen PC");
        BuildPCStage.setScene(new Scene(root, 640, 690));
        BuildPCStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}

