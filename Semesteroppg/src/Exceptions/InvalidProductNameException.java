package Exceptions;

import CompleteOrder.AlertBox;
import javafx.scene.control.Alert;

public class InvalidProductNameException extends IllegalArgumentException {
    public InvalidProductNameException(String msg){
        super(msg);
        AlertBox.display("Feil!", "Feil symbol i 'Navn-feltet'");

    }
}
