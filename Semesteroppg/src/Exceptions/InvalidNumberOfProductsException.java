package Exceptions;

import CompleteOrder.AlertBox;

public class InvalidNumberOfProductsException extends IllegalArgumentException {
    public InvalidNumberOfProductsException(String msg){
        super(msg);
        AlertBox.display("Feil!", "Feil symbol i 'Antall-feltet'");
    }
}
