package Exceptions;

import CompleteOrder.AlertBox;
import javafx.scene.control.Alert;

public class InvalidBrandException extends IllegalArgumentException {
    public InvalidBrandException(String msg){
        super(msg);
        AlertBox.display("Feil!", "Feil symbol i 'Merke-feltet'");
    }
}
