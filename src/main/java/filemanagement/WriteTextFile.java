package filemanagement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WriteTextFile {

    public void writeTextFile(Path relativePath, String filename, List<String> stringList) {
        try {
            Files.write(relativePath.resolve(filename), stringList);
        } catch (IOException ioe) {
            throw new IllegalStateException("Unable to write file: " + relativePath + filename + ";", ioe);
        }
    }
}
