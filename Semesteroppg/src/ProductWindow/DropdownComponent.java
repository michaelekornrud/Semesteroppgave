package ProductWindow;

import javafx.scene.control.ChoiceBox;

import javax.swing.*;
import java.util.List;

public class DropdownComponent {
    private static final String KABINETT = "kabinett";

    private String title;
    private ChoiceBox choiceBox;
    private JLabel field;

    public  DropdownComponent(String title, List<String> components){
        this.title = title;
        //createChoiceBox(components);
    }

    private void createChoiceBox(List<String> components){
        choiceBox = new ChoiceBox();
    }
}
