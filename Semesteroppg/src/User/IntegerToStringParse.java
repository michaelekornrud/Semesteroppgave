package User;

import CompleteOrder.AlertBox;

public class IntegerToStringParse extends javafx.util.converter.IntegerStringConverter {
    public static boolean conversionSuccessful = true;

    @Override
    public Integer fromString(String s){
        try{
            Integer result = super.fromString(s);
            conversionSuccessful = true;
            return result;
        }
        catch (NumberFormatException e){
            e.printStackTrace();

            AlertBox.display("Ugyldig data!", "Du må skrive inn et gyldig tall!", 0);

            conversionSuccessful = false;
            return 0;
        }
    }
}
