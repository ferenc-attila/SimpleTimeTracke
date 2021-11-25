package service.recording;

import datahandling.data.Recording;
import datahandling.data.RecordingList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StartRecordingTest {

    RecordingList recordingList = new RecordingList();
    StartRecording start = new StartRecording();

    @Test
    void startRecordingTest() {
        start.startRecording("morning commute", recordingList);
        start.startRecording("jogging", recordingList);
        Recording commute = recordingList.getRecordings().get(0);
        Recording jogging = recordingList.getRecordings().get(1);
        assertEquals(2, recordingList.getRecordings().size());
        assertTrue(commute.isActive());
        assertEquals(0, commute.getIdentifier());
        assertEquals(1, jogging.getIdentifier());
        assertEquals("morning commute", commute.getDescription());
        assertEquals(LocalDateTime.now().getMinute(), commute.getStartTime().getMinute());
        assertNull(commute.getNotes());
        assertNull(commute.getEndTime());
    }

    @Test
    void createIdentifierMaxIdNotInTheEndOfListTest() {
        recordingList.addRecording(new Recording(3, "housework", LocalDateTime.now()));
        recordingList.addRecording(new Recording(4, "project planning", LocalDateTime.now().minusDays(5)));
        recordingList.addRecording(new Recording(2, "meeting", LocalDateTime.now().minusMonths(2)));
        start.startRecording("hiking", recordingList);
        assertEquals("hiking", recordingList.getRecordings().get(3).getDescription());
        assertEquals(5, recordingList.getRecordings().get(3).getIdentifier());
        recordingList.addRecording(new Recording(0, "go out with the dog", LocalDateTime.now().minusWeeks(2)));
        start.startRecording("travel to Grandparents", recordingList);
        assertEquals("travel to Grandparents", recordingList.getRecordings().get(5).getDescription());
        assertEquals(6, recordingList.getRecordings().get(5).getIdentifier());
    }

    @Test
    void printStartMessageTest() {
        start.startRecording("morning commute", recordingList);
        String minuteNow = getMinuteNow();
        String testStartTime = LocalDateTime.now().getYear() + "-" +
                LocalDateTime.now().getMonthValue() + "-" +
                LocalDateTime.now().getDayOfMonth() + " " +
                LocalDateTime.now().getHour() + ":" +
                minuteNow;
        String expectedString = "Recording started with parameters below:" +
                "\nId.: 0" +
                "\nDescription: morning commute" +
                "\nStart time: " +
                testStartTime +
                "\nEnd time: null" +
                "\nIn progress: yes" +
                "\nNotes: null";
        assertEquals(expectedString, start.printStartMessage(recordingList.getRecordings().get(0)).toString());
    }

    private String getMinuteNow() {
        if (LocalDateTime.now().getMinute() < 10) {
            return "0" + LocalDateTime.now().getMinute();
        } else {
            return ((Integer) (LocalDateTime.now().getMinute())).toString();
        }
    }
}