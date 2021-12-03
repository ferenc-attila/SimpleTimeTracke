package service.recording;

import datamanagement.data.recording.Recording;
import datamanagement.data.recording.RecordingList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StopRecordingTest {

    RecordingList recordingList = new RecordingList();
    StopRecording stop = new StopRecording();
    List<Recording> list = recordingList.getRecordings();

    @Test
    void stopRecordingTest() {
        recordingList.getRecordings().add(new Recording(0, "morning commute", LocalDateTime.parse("2021-06-12T06:15")));
        Recording commute = list.get(0);
        stop.stopRecording(commute);
        assertFalse(commute.isActive());
        assertEquals(LocalDateTime.now().getMinute(), commute.getEndTime().getMinute());
    }

    @Test
    void printStopMessageTest() {
        recordingList.getRecordings().add(new Recording(0, "morning commute", LocalDateTime.parse("2021-06-12T06:15")));
        stop.stopRecording(recordingList.getRecordings().get(0));
        String testTimeNow = LocalDateTime.now().getYear() + "-" +
                getTwoDigitTimeValue(LocalDateTime.now().getMonthValue()) + "-" +
                getTwoDigitTimeValue(LocalDateTime.now().getDayOfMonth()) + " " +
                getTwoDigitTimeValue(LocalDateTime.now().getHour()) + ":" +
                getTwoDigitTimeValue(LocalDateTime.now().getMinute());
        String expectedString = "Recording ended with parameters below:" +
                "\nId.: 0" +
                "\nDescription: morning commute" +
                "\nStart time: 2021-06-12 06:15" +
                "\nEnd time: " +
                testTimeNow +
                "\nIn progress: no" +
                "\nNotes: null";

        assertEquals(expectedString, stop.printStopMessage(recordingList.getRecordings().get(0)).toString());
    }

    private String getTwoDigitTimeValue(int timeValue) {
        if ( timeValue< 10) {
            return "0" + timeValue;
        } else {
            return String.valueOf(timeValue);
        }
    }
}