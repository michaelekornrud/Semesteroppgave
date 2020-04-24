package Bruker;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static List<Products> openFile (Path filePath) throws IOException{
        ArrayList<Products> list = new ArrayList<>();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(String.valueOf(filePath)))){
            String line;

            while((line = reader.readLine()) != null){
                CartRegister.addElement(FileParsing.parseProducts(line));
            }
        }
        catch (IOException e){
            System.err.println(e);
        }
        return list;
    }
}
