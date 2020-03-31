package ProductWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class ProductRegister {

    public static ObservableList<Product> list = FXCollections.observableArrayList();

    public static void attachToTableView(TableView tableView) {tableView.setItems(list);}

    public static void addElement(Product element){list.add(element);}

}