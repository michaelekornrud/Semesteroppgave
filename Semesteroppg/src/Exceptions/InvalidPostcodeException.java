package Exceptions;

import CompleteOrder.AlertBox;
import javafx.scene.control.Alert;

import java.io.IOException;

public class InvalidPostcodeException extends IOException {
    public InvalidPostcodeException(String msg){
        super (msg);
        AlertBox.display("Feil!", "Feil symbol i 'Postnummer-feltet'");
    }
}
