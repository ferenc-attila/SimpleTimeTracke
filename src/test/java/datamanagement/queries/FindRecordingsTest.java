package datamanagement.queries;

import datamanagement.data.Recording;
import datamanagement.data.RecordingList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindRecordingsTest {

    FindRecordings find = new FindRecordings();
    RecordingList recordingList = new RecordingList();
    List<Recording> list = recordingList.getRecordings();

    @Test
    void findSoleActiveRecordingTest() {
        recordingList.addRecording(new Recording(4, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(1, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(2, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(3, "running", LocalDateTime.now()));
        list.get(0).setEndTime(LocalDateTime.now());
        list.get(1).setEndTime(LocalDateTime.now());
        list.get(2).setEndTime(LocalDateTime.now());
        assertEquals(3, find.findActiveRecording(list).getIdentifier());
        assertTrue(find.findActiveRecording(list).isActive());
    }

    @Test
    void findNullActiveRecordingTest() {
        recordingList.addRecording(new Recording(4, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(1, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(2, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(3, "running", LocalDateTime.now()));
        list.get(0).setEndTime(LocalDateTime.now());
        list.get(1).setEndTime(LocalDateTime.now());
        list.get(2).setEndTime(LocalDateTime.now());
        list.get(3).setEndTime(LocalDateTime.now());
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> find.findActiveRecording(list));
        assertEquals("No active recording in the list!", iae.getMessage());
    }

    @Test
    void findMoreThanOneActiveRecordingTest() {
        recordingList.addRecording(new Recording(1, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(2, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(3, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(4, "running", LocalDateTime.now()));
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> find.findActiveRecording(list));
        assertEquals("Invalid list, 4 active records in the list!", iae.getMessage());
    }

    @Test
    void numberOfActiveRecordingTest() {
        recordingList.addRecording(new Recording(1, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(2, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(3, "running", LocalDateTime.now()));
        recordingList.addRecording(new Recording(4, "running", LocalDateTime.now()));
        assertEquals(4, find.numberOfActiveRecording(list));
        list.get(0).setEndTime(LocalDateTime.now());
        assertEquals(3, find.numberOfActiveRecording(list));
        list.get(1).setEndTime(LocalDateTime.now());
        list.get(2).setEndTime(LocalDateTime.now());
        assertEquals(1, find.numberOfActiveRecording(list));
        list.get(3).setEndTime(LocalDateTime.now());
        assertEquals(0, find.numberOfActiveRecording(list));
    }
}