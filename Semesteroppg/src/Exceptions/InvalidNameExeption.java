package Exceptions;

import javafx.scene.control.Alert;

import java.io.IOException;

public class InvalidNameExeption extends IOException {
    public InvalidNameExeption(String msg){
        super (msg);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Something went wring with formating");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
