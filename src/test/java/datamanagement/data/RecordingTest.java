package datamanagement.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RecordingTest {

    Recording recording = new Recording(0, "jogging", LocalDateTime.parse("2021-05-14T17:17"));

    @BeforeEach
    void init() {
        recording.setNotes("my best time");
    }

    @Test
    void createTest() {
        Recording anotherRecording = new Recording(1, "cooking", LocalDateTime.parse("2021-05-15T18:10"));
        anotherRecording.setNotes("garden party");
        assertEquals(1, anotherRecording.getIdentifier());
        assertEquals("cooking", anotherRecording.getDescription());
        assertTrue(anotherRecording.isActive());
        anotherRecording.finishRecording(LocalDateTime.parse("2021-05-15T21:40"));
        assertFalse(anotherRecording.isActive());
        assertEquals("2021-05-15T21:40", anotherRecording.getEndTime().toString());
        assertEquals("garden party", anotherRecording.getNotes());
    }

    @Test
    void ToStringTest() {
        String expected = "Id.: 0\nDescription: jogging\nStart time: 2021-05-14 17:17\nEnd time: null\nIn progress: yes\nNotes: my best time";
        assertEquals(expected, recording.toString());
    }

    @Test
    void toCsvRowTest() {
        recording.finishRecording(LocalDateTime.parse("2021-05-14T18:21"));
        String expected = "0;jogging;no;2021-05-14 17:17;2021-05-14 18:21;my best time";
        assertEquals(expected, recording.toCsvRow());
    }

    @Test
    void printRecordingToMenu() {
        String expectedString = "0: jogging, In progress: yes";
        assertEquals(expectedString, recording.printRecordingToMenu());
    }

    @Test
    void getIdentifierTest() {
        assertEquals(0, recording.getIdentifier());
    }

    @Test
    void isActiveTest() {
        assertTrue(recording.isActive());
        recording.finishRecording(LocalDateTime.parse("2021-05-14T18:21"));
        assertFalse(recording.isActive());
    }

    @Test
    void setActiveTest() {
        recording.setActive(false);
        assertFalse(recording.isActive());
    }

    @Test
    void setStartTimeTest() {
        recording.setStartTime(LocalDateTime.parse("2021-05-14T17:18"));
        assertEquals(LocalDateTime.of(2021, 5, 14, 17, 18), recording.getStartTime());
    }

    @Test
    void finishRecordingTest() {
        recording.finishRecording(LocalDateTime.parse("2021-05-14T17:18"));
        assertEquals(LocalDateTime.of(2021, 5, 14, 17, 18), recording.getEndTime());
        assertFalse(recording.isActive());
    }

    @Test
    void setDescriptionTest() {
        recording.setDescription("jogging in the forest");
        assertEquals("jogging in the forest", recording.getDescription());
    }

    @Test
    void setNotesTest() {
        assertEquals("my best time", recording.getNotes());
        recording.setNotes("medium time");
        assertEquals("medium time", recording.getNotes());
    }
}