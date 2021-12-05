package service.recording;

import datamanagement.data.activity.Activity;
import datamanagement.data.recording.Recording;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StopRecordingTest {

    Activity activity = new Activity(0,"Test", "");
    StopRecording stop = new StopRecording();
    List<Recording> list = activity.getRecordings();

    @Test
    void stopRecordingTest() {
        activity.getRecordings().add(new Recording(0, "morning commute", activity, LocalDateTime.parse("2021-06-12T06:15")));
        Recording commute = list.get(0);
        stop.stopRecording(commute);
        assertFalse(commute.isActive());
        assertEquals(LocalDateTime.now().getMinute(), commute.getEndTime().getMinute());
    }

    @Test
    void printStopMessageTest() {
        activity.getRecordings().add(new Recording(0, "morning commute", activity, LocalDateTime.parse("2021-06-12T06:15")));
        stop.stopRecording(activity.getRecordings().get(0));
        String testTimeNow = LocalDateTime.now().getYear() + "-" +
                getTwoDigitTimeValue(LocalDateTime.now().getMonthValue()) + "-" +
                getTwoDigitTimeValue(LocalDateTime.now().getDayOfMonth()) + " " +
                getTwoDigitTimeValue(LocalDateTime.now().getHour()) + ":" +
                getTwoDigitTimeValue(LocalDateTime.now().getMinute());
        String expectedString = "Recording ended with parameters below:" +
                "\nId.: 0" +
                "\nDescription: morning commute" +
                "\nActivity: Test" +
                "\nStart time: 2021-06-12 06:15" +
                "\nEnd time: " +
                testTimeNow +
                "\nIn progress: no" +
                "\nNotes: null";

        assertEquals(expectedString, stop.printStopMessage(activity.getRecordings().get(0)).toString());
    }

    private String getTwoDigitTimeValue(int timeValue) {
        if ( timeValue< 10) {
            return "0" + timeValue;
        } else {
            return String.valueOf(timeValue);
        }
    }
}