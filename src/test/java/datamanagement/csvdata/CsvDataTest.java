package datamanagement.csvdata;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CsvDataTest {

    CsvData csvData = new CsvData();

    @Test
    void writeRecordingCsvStringListTest() {
        ActivityList activityList = new ActivityList();
        Activity test = new Activity(0, "Test", "for testing");
        Recording hiking = new Recording(0, "hiking in the mountains", test, LocalDateTime.parse("2021-06-12T15:22"));
        Recording housework = new Recording(1, "Cooking", test, LocalDateTime.parse("2021-05-10T10:11"));
        activityList.addActivity(test);
        assertSame(test, activityList.getActivities().get(0));
        test.addRecording(hiking);
        assertSame(hiking, activityList.getActivities().get(0).getRecordings().get(0));
        test.addRecording(housework);
        List<String> csvString = csvData.writeCsvStringFromRecordings(activityList);
        assertEquals(3, csvString.size());
        assertEquals("identifier;description;activity;in_progress;start_time;end_time;notes", csvString.get(0));
        assertEquals("0;hiking in the mountains;Test;yes;2021-06-12 15:22;;", csvString.get(1));
        assertEquals("1;Cooking;Test;yes;2021-05-10 10:11;;", csvString.get(2));
        housework.finishRecording(LocalDateTime.parse("2021-05-10T12:38"));
        hiking.setNotes("It was fun!");
        List<String> csvOtherString = csvData.writeCsvStringFromRecordings(activityList);
        assertEquals("0;hiking in the mountains;Test;yes;2021-06-12 15:22;;It was fun!", csvOtherString.get(1));
        assertEquals("1;Cooking;Test;no;2021-05-10 10:11;2021-05-10 12:38;", csvOtherString.get(2));
    }

    @Test
    void writeActivityCsvStringListTest() {
        ActivityList activityList = new ActivityList();
        List<String> expected = Arrays.asList("identifier;name;description;notes",
                "2;Work;;",
                "0;Birdwatching;mainly rarities;",
                "5;Family;;",
                "1;Other;everything else;new activities will add later");


        activityList.addActivity(new Activity(2, "Work"));
        activityList.addActivity(new Activity(0, "Birdwatching", "mainly rarities"));
        activityList.addActivity(new Activity(5, "Family"));
        activityList.addActivity(new Activity(1, "Other", "everything else", "new activities will add later"));
        assertEquals(expected, csvData.writeCsvStringFromActivities(activityList));
    }
}