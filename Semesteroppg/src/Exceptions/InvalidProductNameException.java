package Exceptions;

import javafx.scene.control.Alert;

public class InvalidProductNameException extends IllegalArgumentException {
    public InvalidProductNameException(String msg){
        super(msg);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Invalid symbol(s) in product-name.");
        alert.setContentText(msg);
        alert.showAndWait();

    }
}
