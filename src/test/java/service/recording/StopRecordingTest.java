package service.recording;

import datahandling.data.Recording;
import datahandling.data.RecordingList;
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
        String minuteNow = getMinuteNow();
        String testTimeNow = LocalDateTime.now().getYear() + "-" +
                LocalDateTime.now().getMonthValue() + "-" +
                LocalDateTime.now().getDayOfMonth() + " " +
                LocalDateTime.now().getHour() + ":" +
                minuteNow;
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

    private String getMinuteNow() {
        if (LocalDateTime.now().getMinute() < 10) {
            return "0" + LocalDateTime.now().getMinute();
        } else {
            return ((Integer) (LocalDateTime.now().getMinute())).toString();
        }
    }
}