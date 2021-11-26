package filemanagement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadTextFile {

    public List<String> readTextFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalStateException("Unable to read file: " + path + ";", ioe);
        }
    }
}
