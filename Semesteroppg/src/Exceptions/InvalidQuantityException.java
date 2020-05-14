package Exceptions;

import CompleteOrder.AlertBox;

public class InvalidQuantityException extends IllegalAccessError {
    public InvalidQuantityException (String title, String msg ){
        super(msg);
        AlertBox.display(title, msg);
    }


}
