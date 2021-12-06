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
}