package LanguageChooser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public class LanguageChooser_Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("languageChooser.fxml"));
            primaryStage.setTitle("Choose language");
            primaryStage.setScene(new Scene(root, 650, 350));
            primaryStage.show();
        }


        public static void main(String[] args) {
            launch(args);

    }
}
