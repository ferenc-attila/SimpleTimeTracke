package service.recording;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StartRecordingTest {

    ActivityList activityList = new ActivityList();
    StartRecording start = new StartRecording();

    @BeforeEach
    void init() {
        activityList.getActivities().add(new Activity(0, "Work", "", ""));
        activityList.getActivities().add(new Activity(1, "Sport", "", ""));
        activityList.getActivities().add(new Activity(2, "Family", "", ""));
        activityList.getActivities().add(new Activity(3, "Housekeeping", "", ""));
    }

    @Test
    void startRecordingTest() {
        start.startRecording("morning commute", activityList, activityList.findActivity("Work"));
        Recording commute = activityList.getActivities().get(0).getRecordings().get(0);
        commute.finishRecording(LocalDateTime.now());
        start.startRecording("jogging", activityList, activityList.findActivity(1));
        Recording jogging = activityList.getActivities().get(1).getRecordings().get(0);
        assertEquals(1, activityList.getActivities().get(0).getRecordings().size());
        assertTrue(jogging.isActive());
        assertEquals(1, jogging.getIdentifier());
        assertEquals("jogging", jogging.getDescription());
        assertEquals(LocalDateTime.now().getMinute(), jogging.getStartTime().getMinute());
        assertNull(jogging.getNotes());
        assertNull(jogging.getEndTime());
    }

    @Test
    void startRecordingInProgressTest() {
        start.startRecording("morning commute", activityList, activityList.findActivity("Work"));
        IllegalStateException ise = assertThrows(IllegalStateException.class, () -> start.startRecording("jogging", activityList, activityList.findActivity("Sport")));
        assertEquals("Active recording running! Try to stop it before start another!", ise.getMessage());
    }

    @Test
    void createIdentifierMaxIdNotInTheEndOfListTest() {
        activityList.getActivities().get(0).addRecording(new Recording(3, "evening commute", activityList.getActivities().get(3), LocalDateTime.now()));
        activityList.getActivities().get(0).addRecording(new Recording(4, "project planning", activityList.getActivities().get(0), LocalDateTime.now().minusDays(5)));
        activityList.getActivities().get(0).addRecording(new Recording(2, "meeting", activityList.getActivities().get(0), LocalDateTime.now().minusMonths(2)));
        activityList.getActivities().get(0).getRecordings().get(0).finishRecording(LocalDateTime.now());
        activityList.getActivities().get(0).getRecordings().get(1).finishRecording(LocalDateTime.now().minusDays(5));
        activityList.getActivities().get(0).getRecordings().get(2).finishRecording(LocalDateTime.now().minusMonths(2));
        start.startRecording("morning commute", activityList, activityList.findActivity("Work"));

        assertEquals("project planning", activityList.getActivities().get(0).getRecordings().get(1).getDescription());
        assertEquals(5, activityList.getActivities().get(0).getRecordings().get(3).getIdentifier());

        activityList.getActivities().get(0).getRecordings().get(3).finishRecording(LocalDateTime.now());
        activityList.getActivities().get(0).addRecording(new Recording(0, "project closing event", activityList.findActivity("Work"), LocalDateTime.now().minusWeeks(2)));
        activityList.getActivities().get(0).getRecordings().get(4).finishRecording(LocalDateTime.now().minusWeeks(2));
        start.startRecording("travel to conference", activityList, activityList.findActivity("Work"));

        assertEquals("travel to conference", activityList.getActivities().get(0).getRecordings().get(5).getDescription());
        assertEquals(6, activityList.getActivities().get(0).getRecordings().get(5).getIdentifier());
    }

    @Test
    void printStartMessageTest() {
        start.startRecording("morning commute", activityList, activityList.getActivities().get(0));
        String testStartTime = LocalDateTime.now().getYear() + "-" +
                getTwoDigitTimeValue(LocalDateTime.now().getMonthValue()) + "-" +
                getTwoDigitTimeValue(LocalDateTime.now().getDayOfMonth()) + " " +
                getTwoDigitTimeValue(LocalDateTime.now().getHour()) + ":" +
                getTwoDigitTimeValue(LocalDateTime.now().getMinute());
        String expectedString = "Recording started with parameters below:" +
                "\nId.: 0" +
                "\nDescription: morning commute" +
                "\nActivity: Work" +
                "\nStart time: " +
                testStartTime +
                "\nEnd time: " +
                "\nIn progress: yes" +
                "\nNotes: null";

        assertEquals(expectedString, start.printStartMessage(activityList.getActivities().get(0).getRecordings().get(0)).toString());
    }

    private String getTwoDigitTimeValue(int timeValue) {
        if (timeValue < 10) {
            return "0" + timeValue;
        } else {
            return String.valueOf(timeValue);
        }
    }
}