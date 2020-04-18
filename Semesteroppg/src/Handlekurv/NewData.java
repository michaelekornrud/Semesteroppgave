package Handlekurv;

import com.sun.istack.internal.NotNull;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.IOException;

public class NewData {

    public static ObservableList<Objects> liste = FXCollections.observableArrayList();
    private ListView<Objects> items = new ListView<Objects>(liste);

    public static void attachListView (ListView LV) throws IOException {
        if (liste != null){
            try{
                LV.setItems(liste);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        else {
            throw new IOException("Noe gikk galt");
        }


    }

    public  ObservableList<Objects> getListe(){
        return this.liste;
    }

    public void addElement (Objects obj){
        liste.add(obj);
    }

    public static void regNewObjects  (Objects  newObjects){
        liste.add(newObjects);
    }
}
