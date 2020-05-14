package CompleteOrder;

import Exceptions.InvalidNameExeption;
import Exceptions.InvalidPostcodeException;

public class Deviations extends AlertBox {

    public static String checkName (String inName) throws InvalidNameExeption {
        if (!inName.matches("^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$")) {
            //regex hentet fra: https://stackoverflow.com/questions/2385701/regular-expression-for-first-and-last-name

            throw new InvalidNameExeption("Det har skjedd en feil i navnet");

        }
        else {
            return inName;
        }

    }

    public static String checkAdress (String inAdress) throws InvalidNameExeption {
        if (!inAdress.matches("^(.+) (\\d{1,3}[a-z]?)$")) {
            //regex hentet fra: https://stackoverflow.com/questions/2385701/regular-expression-for-first-and-last-name

            throw new InvalidNameExeption("Det har skjedd en feil i Adressen");

        }
        else {
            return inAdress;
        }
    }

    public static String checkCity(String inCity) throws InvalidNameExeption {
        if (!inCity.matches("^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$")) {
            //regex hentet fra: https://stackoverflow.com/questions/2385701/regular-expression-for-first-and-last-name

            throw new InvalidNameExeption("Det har skjedd en feil i poststed");

        }
        else {
            return inCity;
        }

    }

    public static int checkPostNumber (int inNumber) throws InvalidPostcodeException {
        String zipToString = String.valueOf(inNumber);
        if (!zipToString.matches("^\\d{4}([ \\-]\\d{4})?$")) {
            //regex hentet fra: https://stackoverflow.com/questions/2385701/regular-expression-for-first-and-last-name

            throw new InvalidPostcodeException("Det har skjedd en feil i postnummer");

        }
        else {
            return inNumber;
        }

    }
}
