package Exceptions;

import CompleteOrder.AlertBox;
import javafx.scene.control.Alert;

public class InvalidPriceException extends IllegalArgumentException {
    public InvalidPriceException(String msg){
        super(msg);
        AlertBox.display("Feil!", "Feil symbol i 'Pris-feltet'");
    }
}
