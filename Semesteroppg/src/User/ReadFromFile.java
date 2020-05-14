package User;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {
    public static List<Products> openFile (Path filePath){
        ArrayList<Products> list = new ArrayList<>();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(String.valueOf(filePath)))){
            String line;

            while((line = reader.readLine()) != null){
                controller.observableList.add(ParseFromFile.parseProducts(line));
            }
        }
        catch (IOException e){
            System.err.println(e);
        }
        return list;
    }
}
