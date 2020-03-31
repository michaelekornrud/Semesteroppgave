package Exceptions;

import javafx.scene.control.Alert;

public class InvalidBrandException extends IllegalArgumentException {
    public InvalidBrandException(String msg){
        super(msg);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Invalid symbol(s) in brand-field.");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
