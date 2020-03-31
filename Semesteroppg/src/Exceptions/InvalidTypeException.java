package Exceptions;

import javafx.scene.control.Alert;

public class InvalidTypeException extends IllegalArgumentException {
    public InvalidTypeException(String msg){
        super(msg);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Invalid symbol(s) in type-field");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
