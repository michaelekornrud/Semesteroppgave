package Exceptions;

import CompleteOrder.AlertBox;

public class InvalidTypeException extends IllegalArgumentException {
    public InvalidTypeException(String msg){
        super(msg);
        AlertBox.display("Feil!", "Feil symbol i 'Type-feltet'");
    }
}
