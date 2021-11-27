package service.recording;

import datamanagement.data.Recording;
import datamanagement.data.RecordingList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StartRecordingTest {

    RecordingList recordingList = new RecordingList();
    StartRecording start = new StartRecording();
    List<Recording> list = recordingList.getRecordings();

    @Test
    void startRecordingTest() {
        start.startRecording("morning commute", recordingList);
        Recording commute = list.get(0);
        commute.setEndTime(LocalDateTime.now());
        start.startRecording("jogging", recordingList);
        Recording jogging = list.get(1);
        assertEquals(2, list.size());
        assertTrue(jogging.isActive());
        assertEquals(1, jogging.getIdentifier());
        assertEquals("jogging", jogging.getDescription());
        assertEquals(LocalDateTime.now().getMinute(), jogging.getStartTime().getMinute());
        assertNull(jogging.getNotes());
        assertNull(jogging.getEndTime());
    }

    @Test
    void startRecordingInProgressTest() {
        start.startRecording("morning commute", recordingList);
        IllegalStateException ise = assertThrows(IllegalStateException.class, () -> start.startRecording("jogging", recordingList));
        assertEquals("Active recording running! Try to stop it before start another!", ise.getMessage());
    }

    @Test
    void createIdentifierMaxIdNotInTheEndOfListTest() {
        recordingList.addRecording(new Recording(3, "housework", LocalDateTime.now()));
        recordingList.addRecording(new Recording(4, "project planning", LocalDateTime.now().minusDays(5)));
        recordingList.addRecording(new Recording(2, "meeting", LocalDateTime.now().minusMonths(2)));
        list.get(0).setEndTime(LocalDateTime.now());
        list.get(1).setEndTime(LocalDateTime.now().minusDays(5));
        list.get(2).setEndTime(LocalDateTime.now().minusMonths(2));
        start.startRecording("hiking", recordingList);
        assertEquals("hiking", list.get(3).getDescription());
        assertEquals(5, list.get(3).getIdentifier());
        list.get(3).setEndTime(LocalDateTime.now());
        recordingList.addRecording(new Recording(0, "go out with the dog", LocalDateTime.now().minusWeeks(2)));
        list.get(4).setEndTime(LocalDateTime.now().minusWeeks(2));
        start.startRecording("travel to Grandparents", recordingList);
        assertEquals("travel to Grandparents", list.get(5).getDescription());
        assertEquals(6, list.get(5).getIdentifier());
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
        assertEquals(expectedString, start.printStartMessage(list.get(0)).toString());
    }

    private String getMinuteNow() {
        if (LocalDateTime.now().getMinute() < 10) {
            return "0" + LocalDateTime.now().getMinute();
        } else {
            return ((Integer) (LocalDateTime.now().getMinute())).toString();
        }
    }
}