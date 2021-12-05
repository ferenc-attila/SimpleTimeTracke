package datamanagement.data.activity;

import datamanagement.data.recording.Recording;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @Test
    void createTest() {
        Activity activity = new Activity(0, "Housework", "cooking, cleaning, gardening, etc.");
        assertEquals("Housework", activity.getName());
        assertEquals("cooking, cleaning, gardening, etc.", activity.getDescription());
        assertNull(activity.getNotes());
        assertEquals(0, activity.getRecordings().size());
    }

    @Test
    void addRecordingTest() {
        Activity activity = new Activity(1, "Sport", "jogging, hiking, swimming, etc");
        activity.addRecording(new Recording(1, "hiking in local the mountains", activity, LocalDateTime.now()));
        assertEquals(1, activity.getRecordings().size());
    }

    @Test
    void toCsvRowTest() {
        Activity activity = new Activity(1, "Sport", "jogging, hiking, swimming, etc", "test");
        String expectedString = "1;Sport;jogging, hiking, swimming, etc;test";
        assertEquals(expectedString, activity.toCsvRow());
    }

    @Test
    void toCsvRowEmptyFieldsTest() {
        Activity activity = new Activity(1, "Sport", "jogging, hiking, swimming, etc");
        String expectedString = "1;Sport;jogging, hiking, swimming, etc;";
        assertEquals(expectedString, activity.toCsvRow());
    }

    @Test
    void printActivityToMenuTest() {
        Activity activity = new Activity(1, "Sport", "jogging, hiking, swimming, etc", "test");
        String expectedString = "1: Sport, desc.: jogging, hiking, swimming, etc\nnotes: test";
        assertEquals(expectedString, activity.printActivityToMenu());
    }

    @Test
    void printActivityToMenuEmptyFieldsTest() {
        Activity activity = new Activity(1, "Sport", "", "");
        String expectedString = "1: Sport, desc.: \nnotes: ";
        assertEquals(expectedString, activity.printActivityToMenu());
    }

    @Test
    void printActivityToMenuNullFieldsTest() {
        Activity activity = new Activity(1, "Sport");
        String expectedString = "1: Sport, desc.: null\nnotes: null";
        assertEquals(expectedString, activity.printActivityToMenu());
    }

    @Test
    void getMaxRecordingIdentifierTest() {
        Activity activity = new Activity(1, "Sport");
        Recording jogging = new Recording(0, "jogging", activity,LocalDateTime.now());
        jogging.setActive(false);
        Recording hiking = new Recording(3, "hiking", activity,LocalDateTime.now());
        jogging.setActive(false);
        Recording swimming = new Recording(2, "swimming", activity,LocalDateTime.now());
        activity.addRecording(jogging);
        activity.addRecording(hiking);
        activity.addRecording(swimming);
        assertEquals(3, activity.getMaxRecordingIdentifier());
    }
}