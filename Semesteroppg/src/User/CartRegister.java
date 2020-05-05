package User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class CartRegister {
    public static ObservableList<Products> list = FXCollections.observableArrayList();

    public static void attachToTableView(TableView tableView) {tableView.setItems(list);}

    public ObservableList<Products> getList(){
        return this.list;
    }

    public static void addElement(Products element){list.add(element);}
}
