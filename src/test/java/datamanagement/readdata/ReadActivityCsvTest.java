package datamanagement.readdata;

import datamanagement.data.activity.ActivityList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadActivityCsvTest {

    List<String> fileContent = Arrays.asList("identifier;name;description;notes",
            "2;Work;;;",
            "0;Birdwatching;mainly rarities;",
            "5;Family;;;",
            "1;Other;everything else;new activities will add later");

    ActivityList activityList = new ActivityList();
    ReadActivityCsv read = new ReadActivityCsv();

    @Test
    void readActivityDataTest() {
        read.readActivityData(fileContent, activityList);
        assertEquals(4, activityList.getActivities().size());
        assertEquals(2, activityList.getActivities().get(0).getIdentifier());
        assertEquals("mainly rarities", activityList.getActivities().get(1).getDescription());
        assertEquals("Family", activityList.getActivities().get(2).getName());
        assertEquals("new activities will add later", activityList.getActivities().get(3).getNotes());
        assertTrue(activityList.getActivities().get(0).getDescription().isBlank());
    }
}