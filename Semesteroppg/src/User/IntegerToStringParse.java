package User;

import javafx.scene.control.Alert;

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil");
            alert.setHeaderText("Ugyldig data!");
            alert.setContentText("Du må skrive inn et gydig tall!");
            alert.showAndWait();

            conversionSuccessful = false;
            return 0;
        }
    }
}
