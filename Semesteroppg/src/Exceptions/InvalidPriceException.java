package Exceptions;

import CompleteOrder.AlertBox;

public class InvalidPriceException extends IllegalArgumentException {
    public InvalidPriceException(String msg){
        super(msg);
        AlertBox.display("Feil!", "Feil symbol i 'Pris-feltet'");
    }
}
