package service.recording;

import datahandling.data.Recording;
import datahandling.data.RecordingList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StartRecordingTest {

    RecordingList recordingList = new RecordingList();
    StartRecording start = new StartRecording();

    @Test
    void startRecordingTest() {
        start.startRecording("morning commute", recordingList);
        Recording commute = recordingList.getRecordings().get(0);
        assertTrue(commute.isActive());
        assertEquals(0, commute.getIdentifier());
        assertEquals("morning commute", commute.getDescription());
//        assertEquals(LocalDateTime.now(), commute.getStartTime());
        assertNull(commute.getNotes());
        assertNull(commute.getEndTime());
    }
}