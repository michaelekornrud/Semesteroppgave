package LanguageChooser;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//import java.awt.event.MouseEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Language  {
//implements Initializable


    @FXML
    private ImageView imgNor;

    @FXML
    private ImageView imgGB;

   /* @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        File norsk = new File("../LanguageChooser/norflagg.jpeg");
        File engelsk = new File("../LanguageChooser/unionJack.jpeg");

        Image norge = new Image(norsk.toURI().toString());
        imgNor.setImage(norge);

        Image gb = new Image(engelsk.toURI().toString());
        imgGB.setImage(gb);
    }*/


    @FXML
    void chooseNor (MouseEvent event){

    }

    @FXML
    void chooseGB (MouseEvent event){

    }
}
