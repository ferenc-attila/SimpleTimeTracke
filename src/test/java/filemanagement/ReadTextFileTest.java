package filemanagement;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadTextFileTest {

    @Test
    void readTextFileTest() throws IOException {
        List<String> fileContent = Files.readAllLines(Paths.get("src/test/resources/recordings.csv"));
        assertEquals("identifier;description;isActive;start time;end time;notes;activityId", fileContent.get(0));
        assertEquals("9;;false;2021-05-15 09:40;2021-05-15 08:10;kitchen cleaning;4", fileContent.get(10));
        String[] row = fileContent.get(2).split(";", -1);
        assertEquals("1", row[0]);
        assertEquals("", row[3]);
        assertTrue(Boolean.parseBoolean(row[2]));
    }
}