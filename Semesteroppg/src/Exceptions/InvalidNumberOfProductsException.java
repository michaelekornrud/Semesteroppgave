package Exceptions;

import javafx.scene.control.Alert;

public class InvalidNumberOfProductsException extends IllegalArgumentException {
    public InvalidNumberOfProductsException(String msg){
        super(msg);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Invalid symbol(s) in number-of-products-filed.");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
