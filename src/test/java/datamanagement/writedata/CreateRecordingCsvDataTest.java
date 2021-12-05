package datamanagement.writedata;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateRecordingCsvDataTest {

    CreateRecordingCsvData write = new CreateRecordingCsvData();
    ActivityList activityList = new ActivityList();
    Activity test = new Activity(0, "Test", "for testing");
    Recording hiking = new Recording(0, "hiking in the mountains", test, LocalDateTime.parse("2021-06-12T15:22"));
    Recording housework = new Recording(1, "Cooking", test, LocalDateTime.parse("2021-05-10T10:11"));
    List<Recording> list = test.getRecordings();

    @Test
    void writeCsvDataTest() {
        activityList.addActivity(test);
        test.addRecording(hiking);
        test.addRecording(housework);
        List<String> csvData = write.writeRecordingCsvData(activityList);
        assertEquals(3, csvData.size());
        assertEquals("identifier;description;activity;in_progress;start_time;end_time;notes", csvData.get(0));
        assertEquals("0;hiking in the mountains;Test;yes;2021-06-12 15:22;;", csvData.get(1));
        assertEquals("1;Cooking;Test;yes;2021-05-10 10:11;;", csvData.get(2));
        list.get(1).finishRecording(LocalDateTime.parse("2021-05-10T12:38"));
        list.get(0).setNotes("It is fun!");
        csvData = write.writeRecordingCsvData(activityList);
        assertEquals("1;Cooking;Test;no;2021-05-10 10:11;2021-05-10 12:38;", csvData.get(2));
        assertEquals("0;hiking in the mountains;Test;yes;2021-06-12 15:22;;It is fun!", csvData.get(1));
    }
}