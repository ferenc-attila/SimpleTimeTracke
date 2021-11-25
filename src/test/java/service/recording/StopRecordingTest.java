package service.recording;

import datahandling.data.Recording;
import datahandling.data.RecordingList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StopRecordingTest {

    RecordingList recordingList = new RecordingList();
    StopRecording stop = new StopRecording();

    @Test
    void stopRecordingTest() {
        recordingList.getRecordings().add(new Recording(0, "morning commute", LocalDateTime.parse("2021-06-12T06:15")));
        Recording commute = recordingList.getRecordings().get(0);
        stop.stopRecording(recordingList.getRecordings().get(0));
        assertFalse(commute.isActive());
        assertEquals(LocalDateTime.now().getMinute(), commute.getEndTime().getMinute());
    }
}