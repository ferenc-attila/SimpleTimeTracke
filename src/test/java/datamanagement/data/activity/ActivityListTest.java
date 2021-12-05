package datamanagement.data.activity;

import datamanagement.data.recording.Recording;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ActivityListTest {

    ActivityList activityList = new ActivityList();

    @BeforeEach
    void init () {
        activityList.addActivity(new Activity(0, "Test"));
    }

    @Test
    void createTest() {
        ActivityList anotherActivityList = new ActivityList();
        assertEquals(0, anotherActivityList.getActivities().size());
    }

    @Test
    void addActivityTest() {
        activityList.addActivity(new Activity(1, "Work"));
        assertEquals("Work", activityList.getActivities().get(1).getName());
    }

    @Test
    void findActivityByIndexTest() {
        Activity work = new Activity(1, "Work");
        activityList.addActivity(work);
        assertEquals(work, activityList.findActivity(1));
    }

    @Test
    void findActivityByInvalidIndexTest() {
        Activity work = new Activity(1, "Work");
        activityList.addActivity(work);
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> activityList.findActivity(2));
        assertEquals("Unknown or empty activity at index: '2'!", iae.getMessage());
    }

    @Test
    void findActivityByNameTest() {
        Activity work = new Activity(1, "Work");
        activityList.addActivity(work);
        assertEquals(work, activityList.findActivity("Work"));
    }

    @Test
    void findActivityByInvalidNameTest() {
        Activity work = new Activity(1, "Work");
        activityList.addActivity(work);
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> activityList.findActivity("sport"));
        assertEquals("Unknown or empty activity: 'sport'!", iae.getMessage());
    }

    @Test
    void findActivityByActivityTest() {
        Activity work = new Activity(1, "Work");
        activityList.addActivity(work);
        assertEquals(work, activityList.findActivity(work));
    }

    @Test
    void findActivityNotInListByAnotherActivityTest() {
        Activity work = new Activity(1, "Work");
        activityList.addActivity(work);
        Activity sport = new Activity(2, "sport");
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> activityList.findActivity(sport));
        assertEquals("Activity '2: sport, desc.: null' is not in this list!", iae.getMessage());
    }

    @Test
    void isExistingActivityTest() {
        Activity work = new Activity(1, "Work");
        assertFalse(activityList.isExistingActivity("Work"));
        activityList.addActivity(work);
        assertTrue(activityList.isExistingActivity("Work"));
    }

    @Test
    void getMaxIdentifierTest() {
        activityList.addActivity(new Activity(1, "Work"));
        activityList.addActivity(new Activity(2, "Sport"));
        activityList.addActivity(new Activity(15, "Hobbies"));
        assertEquals(15, activityList.getMaxIdentifier());
    }

    @Test
    void findSoleActiveRecordingTest() {
        Activity test = new Activity(1, "test");
        activityList.addActivity(test);
        test.addRecording(new Recording(4, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(1, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(2, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(3, "running", test, LocalDateTime.now()));
        test.getRecordings().get(0).finishRecording(LocalDateTime.now());
        test.getRecordings().get(1).finishRecording(LocalDateTime.now());
        test.getRecordings().get(2).finishRecording(LocalDateTime.now());
        assertEquals(3, activityList.findActiveRecording().getIdentifier());
        assertTrue(activityList.findActiveRecording().isActive());
    }

    @Test
    void findNullActiveRecordingTest() {
        Activity test = new Activity(1, "test");
        activityList.addActivity(test);
        test.addRecording(new Recording(4, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(1, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(2, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(3, "running", test, LocalDateTime.now()));
        test.getRecordings().get(0).finishRecording(LocalDateTime.now());
        test.getRecordings().get(1).finishRecording(LocalDateTime.now());
        test.getRecordings().get(2).finishRecording(LocalDateTime.now());
        test.getRecordings().get(3).finishRecording(LocalDateTime.now());
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> activityList.findActiveRecording());
        assertEquals("No active recording in the list!", iae.getMessage());
    }

    @Test
    void findMoreThanOneActiveRecordingTest() {
        Activity test = new Activity(1, "test");
        activityList.addActivity(test);
        test.addRecording(new Recording(1, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(2, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(3, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(4, "running", test, LocalDateTime.now()));
        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class, () -> activityList.findActiveRecording());
        assertEquals("Invalid list, 4 active records in the list!", iae.getMessage());
    }

    @Test
    void numberOfActiveRecordingTest() {
        Activity test = new Activity(1, "test");
        activityList.addActivity(test);
        test.addRecording(new Recording(1, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(2, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(3, "running", test, LocalDateTime.now()));
        test.addRecording(new Recording(4, "running", test, LocalDateTime.now()));
        assertEquals(4, activityList.numberOfActiveRecording());
        test.getRecordings().get(0).finishRecording(LocalDateTime.now());
        assertEquals(3, activityList.numberOfActiveRecording());
        test.getRecordings().get(1).finishRecording(LocalDateTime.now());
        test.getRecordings().get(2).finishRecording(LocalDateTime.now());
        assertEquals(1, activityList.numberOfActiveRecording());
        test.getRecordings().get(3).finishRecording(LocalDateTime.now());
        assertEquals(0, activityList.numberOfActiveRecording());
    }
}