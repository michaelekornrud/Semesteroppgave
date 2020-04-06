package Handlekurv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LagreHandlekurv {

    public static void writeToFile(Path path, String str) throws IOException {
        Files.write(path,str.getBytes());
    }

}
