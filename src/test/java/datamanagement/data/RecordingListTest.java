package datamanagement.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RecordingListTest {

    RecordingList recordingList = new RecordingList();
    Recording hiking = new Recording(0, "hiking in the mountains", LocalDateTime.parse("2021-06-12T15:22"));
    Recording housework = new Recording(1, "Cooking", LocalDateTime.parse("2021-05-10T10:11"));

    @Test
    void addRecording() {
        recordingList.addRecording(hiking);
        recordingList.addRecording(housework);
        assertEquals(2, recordingList.getRecordings().size());
        assertEquals("Cooking", recordingList.getRecordings().get(1).getDescription());
        assertTrue(recordingList.getRecordings().get(0).isActive());
    }
}