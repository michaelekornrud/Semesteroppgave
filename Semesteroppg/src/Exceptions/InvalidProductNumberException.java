package Exceptions;

import javafx.scene.control.Alert;

public class InvalidProductNumberException extends IllegalArgumentException {
    public InvalidProductNumberException(String msg){
        super(msg);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Invalid symbol(s) in product-number-field.");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
