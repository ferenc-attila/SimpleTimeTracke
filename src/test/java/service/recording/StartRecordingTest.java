package service.recording;

import datahandling.data.Recording;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StartRecordingTest {

    StartRecording start = new StartRecording();
    private int identifier = 0;
    private String description = "morning commute";
    private LocalDateTime startTime = LocalDateTime.parse("2021-06-15T06:13");

    @Test
    void startRecordingTest() {
        Recording commute = start.startRecording(identifier, description, startTime);
        assertTrue(commute.isActive());
        assertEquals(0, commute.getIdentifier());
        assertEquals("morning commute", commute.getDescription());
        assertEquals(LocalDateTime.of(2021,6,15,6,13), commute.getStartTime());
        assertNull(commute.getNotes());
        assertNull(commute.getEndTime());
    }
}