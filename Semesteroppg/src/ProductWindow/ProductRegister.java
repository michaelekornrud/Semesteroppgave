package ProductWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductRegister {

    public static ObservableList<Product> list = FXCollections.observableArrayList();

    public static void addElement(Product element){list.add(element);}

}