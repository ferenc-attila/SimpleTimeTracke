package service.recording;

import datahandling.data.Recording;
import datahandling.data.RecordingList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StartRecordingTest {

    RecordingList recordingList = new RecordingList();
    StartRecording start = new StartRecording();

    @Test
    void startRecordingTest() {
        start.startRecording("morning commute", recordingList);
        start.startRecording("jogging", recordingList);
        Recording commute = recordingList.getRecordings().get(0);
        Recording jogging = recordingList.getRecordings().get(1);
        assertEquals(2, recordingList.getRecordings().size());
        assertTrue(commute.isActive());
        assertEquals(0, commute.getIdentifier());
        assertEquals(1, jogging.getIdentifier());
        assertEquals("morning commute", commute.getDescription());
        assertEquals(LocalDateTime.now().getMinute(), commute.getStartTime().getMinute());
        assertNull(commute.getNotes());
        assertNull(commute.getEndTime());
    }
}