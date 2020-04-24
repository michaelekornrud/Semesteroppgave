package Bruker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriter { //Skriv til .txt fil
    public static void writeToString(Path path, String str) throws IOException {
        Files.write(path,str.getBytes());

    }
}
