package Exceptions;

import CompleteOrder.AlertBox;

public class InvalidProductNameException extends IllegalArgumentException {
    public InvalidProductNameException(String msg){
        super(msg);
        AlertBox.display("Feil!", "Feil symbol i 'Navn-feltet'");

    }
}
