package Exceptions;

import javafx.scene.control.Alert;

public class InvalidPriceException extends IllegalArgumentException {
    public InvalidPriceException(String msg){
        super(msg);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Invalid symbol(s) price-field.");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
