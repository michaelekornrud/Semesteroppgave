package Handlekurv;

import javafx.stage.FileChooser;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ÅpneHandlekurv {
    public static List<Objects>  openFile (Path filePath) throws IOException {
        ArrayList<Objects> liste = new ArrayList<>();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(String.valueOf(filePath)))){
            String line;

            while ((line  = reader.readLine()) != null){
                NewData.regNewObjects(CartParsing.parseObjects(line));
            }
        }
        catch (IOException e){
            System.err.println("Noe gikk galt!");
            e.printStackTrace();
        }
        return liste;
    }
}
