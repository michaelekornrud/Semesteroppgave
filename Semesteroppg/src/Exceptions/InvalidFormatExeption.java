package Exceptions;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.IllegalFormatException;

public class InvalidFormatExeption  extends IOException {
    public InvalidFormatExeption (String msg){
        super(msg);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Something went wring with formating");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
