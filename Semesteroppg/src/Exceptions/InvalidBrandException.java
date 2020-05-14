package Exceptions;

import CompleteOrder.AlertBox;

public class InvalidBrandException extends IllegalArgumentException {
    public InvalidBrandException(String msg){
        super(msg);
        AlertBox.display("Feil!", "Feil symbol i 'Merke-feltet'",0);
    }
}
