package datamanagement.readdata;

import datamanagement.data.activity.ActivityList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadRecordingCsvDataTest {

    ReadRecordingCsv readData = new ReadRecordingCsv();
    ActivityList activityList = new ActivityList();
    List<String> fileContent = Arrays.asList("identifier;description;activity;isActive;start time;end time;notes",
            "0;evening jogging;Sport;false;2021-06-04 18:25;;",
            "1;morning commute;Work;true;2021-11-25 05:40;2021-11-25 06:10;by bus",
            "4;Northern Loon twitch;Hobbies;false;2021-08-12 19:30;2021-08-12 07:10;the bird was founded",
            "5;Eagle photography;Hobbies;false;2021-10-12 05:10;2021-10-12 19:20;");

    @Test
    void readDataTest() {
        assertEquals(0, activityList.getActivities().size());
        readData.readData(fileContent, activityList);
        assertEquals(2, activityList.getActivities().get(2).getRecordings().size());
        assertEquals(5, activityList.getActivities().get(2).getRecordings().get(1).getIdentifier());
        assertEquals("morning commute", activityList.getActivities().get(1).getRecordings().get(0).getDescription());
        assertEquals(LocalDateTime.parse("2021-06-04T18:25"), activityList.getActivities().get(0).getRecordings().get(0).getStartTime());
        assertEquals(LocalDateTime.of(2021, 8, 12, 7, 10), activityList.getActivities().get(2).getRecordings().get(0).getEndTime());
        assertNull(activityList.getActivities().get(0).getRecordings().get(0).getEndTime());
        assertEquals("by bus", activityList.getActivities().get(1).getRecordings().get(0).getNotes());
        assertEquals("", activityList.getActivities().get(0).getRecordings().get(0).getNotes());
        assertFalse(activityList.getActivities().get(2).getRecordings().get(1).isActive());
        assertTrue(activityList.getActivities().get(0).getRecordings().get(0).isActive());
    }
}