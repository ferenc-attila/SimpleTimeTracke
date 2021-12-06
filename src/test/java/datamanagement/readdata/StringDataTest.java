package datamanagement.readdata;

import datamanagement.data.activity.ActivityList;
import datamanagement.stringdata.StringData;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StringDataTest {

    StringData stringData = new StringData("src/test/resources/activities.csv", "src/test/resources/recordings.csv");

    @Test
    void readRecordingDataTest() {
        ActivityList activityList = new ActivityList();
        assertEquals(0, activityList.getActivities().size());
        stringData.buildLists(activityList);
        assertEquals(1, activityList.getActivities().get(0).getRecordings().size());
        assertEquals(5, activityList.getActivities().get(5).getRecordings().get(1).getIdentifier());
        assertEquals("morning commute", activityList.getActivities().get(0).getRecordings().get(0).getDescription());
        assertEquals(LocalDateTime.parse("2021-06-04T18:25"), activityList.getActivities().get(4).getRecordings().get(0).getStartTime());
        assertEquals(LocalDateTime.of(2021, 8, 12, 7, 10), activityList.getActivities().get(5).getRecordings().get(0).getEndTime());
        assertNull(activityList.getActivities().get(4).getRecordings().get(0).getEndTime());
        assertEquals("by bus", activityList.getActivities().get(0).getRecordings().get(0).getNotes());
        assertEquals("", activityList.getActivities().get(5).getRecordings().get(1).getNotes());
        assertFalse(activityList.getActivities().get(5).getRecordings().get(0).isActive());
        assertTrue(activityList.getActivities().get(4).getRecordings().get(0).isActive());
    }

    @Test
    void readActivityDataTest() {
        ActivityList activityList = new ActivityList();
        assertEquals(0, activityList.getActivities().size());
        stringData.buildLists(activityList);
        assertEquals(6, activityList.getActivities().size());
        assertEquals(2, activityList.getActivities().get(0).getIdentifier());
        assertEquals("mainly rarities", activityList.getActivities().get(1).getDescription());
        assertEquals("Family", activityList.getActivities().get(2).getName());
        assertEquals("new activities will add later", activityList.getActivities().get(3).getNotes());
        assertEquals("Sport", activityList.getActivities().get(4).getName());
        assertEquals(2, activityList.getActivities().get(5).getRecordings().size());
        assertTrue(activityList.getActivities().get(0).getDescription().isBlank());
    }
}