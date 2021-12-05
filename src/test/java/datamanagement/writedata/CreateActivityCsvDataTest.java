package datamanagement.writedata;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateActivityCsvDataTest {

    List<String> expected = Arrays.asList("identifier;name;description;notes",
            "2;Work;;",
            "0;Birdwatching;mainly rarities;",
            "5;Family;;",
            "1;Other;everything else;new activities will add later");

    ActivityList activityList = new ActivityList();
    CreateActivityCsvData create = new CreateActivityCsvData();


    @Test
    void writeActivityCsvDataTest() {
        activityList.addActivity(new Activity(2, "Work"));
        activityList.addActivity(new Activity(0, "Birdwatching", "mainly rarities"));
        activityList.addActivity(new Activity(5, "Family"));
        activityList.addActivity(new Activity(1, "Other", "everything else", "new activities will add later"));
        assertEquals(expected, create.writeActivityCsvData(activityList));
    }
}