package CompleteOrder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {


    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMinHeight(250);

        Label label = new  Label();
        label.setAlignment(Pos.CENTER);
        label.setText(message);
        label.setPadding(new Insets(10));
        label.setFont(new Font("Times New Roman", 16));


        Button lukkBox = new Button("Lukk");
        lukkBox.setOnAction(e -> window.close());




        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,lukkBox);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));



        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();



    }
}
