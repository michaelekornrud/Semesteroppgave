package Exceptions;


import CompleteOrder.AlertBox;

import java.io.IOException;

public class InvalidNameExeption extends IOException {
    public InvalidNameExeption(String msg){
        super (msg);
        AlertBox.display("Feil!", "Feil symbol i 'Navn-feltet'",0);
    }
}
