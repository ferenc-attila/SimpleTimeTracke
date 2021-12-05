package datamanagement.queries;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindRecordingsTest {

    ActivityList activityList = new ActivityList();
    FindRecordings find = new FindRecordings();
    Activity test = new Activity(0, "test", "", "");
    List<Recording> list = test.getRecordings();

    @Test
    void findSoleActiveRecordingTest() {
        activityList.addActivity(test);
        test.addRecording(new Recording(4, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(1, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(2, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(3, "running", test, LocalDateTime.now()));
        list.get(0).finishRecording(LocalDateTime.now());
        list.get(1).finishRecording(LocalDateTime.now());
        list.get(2).finishRecording(LocalDateTime.now());
        assertEquals(3, find.findActiveRecording(activityList).getIdentifier());
        assertTrue(find.findActiveRecording(activityList).isActive());
    }

    @Test
    void findNullActiveRecordingTest() {
        test.addRecording(new Recording(4, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(1, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(2, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(3, "running", test, LocalDateTime.now()));
        list.get(0).finishRecording(LocalDateTime.now());
        list.get(1).finishRecording(LocalDateTime.now());
        list.get(2).finishRecording(LocalDateTime.now());
        list.get(3).finishRecording(LocalDateTime.now());
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> find.findActiveRecording(activityList));
        assertEquals("No active recording in the list!", iae.getMessage());
    }

    @Test
    void findMoreThanOneActiveRecordingTest() {
        activityList.addActivity(test);
        test.addRecording(new Recording(1, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(2, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(3, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(4, "running", test, LocalDateTime.now()));
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> find.findActiveRecording(activityList));
        assertEquals("Invalid list, 4 active records in the list!", iae.getMessage());
    }

    @Test
    void numberOfActiveRecordingTest() {
        activityList.addActivity(test);
        test.addRecording(new Recording(1, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(2, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(3, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(4, "running", test, LocalDateTime.now()));
        assertEquals(4, find.numberOfActiveRecording(activityList));
        list.get(0).finishRecording(LocalDateTime.now());
        assertEquals(3, find.numberOfActiveRecording(activityList));
        list.get(1).finishRecording(LocalDateTime.now());
        list.get(2).finishRecording(LocalDateTime.now());
        assertEquals(1, find.numberOfActiveRecording(activityList));
        list.get(3).finishRecording(LocalDateTime.now());
        assertEquals(0, find.numberOfActiveRecording(activityList));
    }
}