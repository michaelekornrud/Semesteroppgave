package Exceptions;

import CompleteOrder.AlertBox;
import java.io.IOException;


public class InvalidFormatExeption  extends IOException {
    public InvalidFormatExeption (String msg){
        super(msg);
        AlertBox.display("Feil!", "Noe er feil med formatet!",0);
    }
}
