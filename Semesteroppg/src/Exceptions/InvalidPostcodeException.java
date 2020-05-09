package Exceptions;

import javafx.scene.control.Alert;

import java.io.IOException;

public class InvalidPostcodeException extends IOException {
    public InvalidPostcodeException(String msg){
        super (msg);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Noe er feil med postnummeret");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
