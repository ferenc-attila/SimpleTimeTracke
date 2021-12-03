package datamanagement.data.activity;

import datamanagement.data.recording.Recording;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @Test
    void createTest() {
        Activity activity = new Activity("Housework", "cooking, cleaning, gardening, etc.");
        assertEquals("Housework", activity.getName());
        assertEquals("cooking, cleaning, gardening, etc.", activity.getDescription());
        assertNull(activity.getNotes());
        assertEquals(0, activity.getRecordings().size());
    }

    @Test
    void addRecordingTest() {
        Activity activity = new Activity("Sport", "jogging, hiking, swimming, etc");
        activity.addRecording(new Recording(1, "hiking in local the mountains", LocalDateTime.now()));
        assertEquals(1, activity.getRecordings().size());
    }
}