package filemanagement;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadTextFileTest {

    ReadTextFile read = new ReadTextFile();

    @Test
    void readTextValidRecordingsFileTest() {
        List<String> fileContent = read.readTextFile(Paths.get("src/test/resources/recordings.csv"));
        assertEquals("identifier;description;activity;isActive;start time;end time;notes", fileContent.get(0));
        assertEquals("4;Northern Loon twitch;Hobbies;false;2021-08-12 19:30;2021-08-12 07:10;the bird was founded", fileContent.get(3));
        String[] row = fileContent.get(1).split(";", -1);
        assertEquals("0", row[0]);
        assertEquals("", row[5]);
        assertFalse(Boolean.parseBoolean(row[3]));
    }

    @Test
    void readTextValidActivityFileTest() {
        List<String> fileContent = read.readTextFile(Paths.get("src/test/resources/activities.csv"));
        assertEquals("identifier;name;description;notes", fileContent.get(0));
        assertEquals("1;Other;everything else;new activities will add later", fileContent.get(4));
        String[] row = fileContent.get(2).split(";", -1);
        assertEquals("0", row[0]);
        assertEquals("", row[3]);
        assertEquals("Birdwatching", (row[1]));
    }

    @Test
    void readTextInvalidFileTest() {
        Path invalidPath = Paths.get("src/test/recordings_.txt");
        IllegalStateException ise = assertThrows(IllegalStateException.class, () -> read.readTextFile(invalidPath));
        assertEquals("Unable to read file: src\\test\\recordings_.txt!", ise.getMessage());
        assertEquals(NoSuchFileException.class, ise.getCause().getClass());
    }
}