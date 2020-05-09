package User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteToFile { //Skriv til .txt fil
    public static void writeToString(Path path, String str) throws IOException {
        //Endre navn fra filewriter og filereader (de er klasser i java fra før)
        Files.write(path,str.getBytes());
    }
}


