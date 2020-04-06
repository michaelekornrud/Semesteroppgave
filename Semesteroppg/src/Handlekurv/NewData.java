package Handlekurv;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class NewData {

    public static ObservableList<Objects> liste = FXCollections.observableArrayList();
    private ListView<Objects> items = new ListView<Objects>(liste);

    public void attachListView (ListView LV){
        LV.setItems(liste);
    }

    public ObservableList<Objects> getListe(){
        return this.liste;
    }

    public void addElement (Objects obj){
        liste.add(obj);
    }

    public static void regNewObjects  (Objects  newObjects){
        liste.add(newObjects);
    }
}
