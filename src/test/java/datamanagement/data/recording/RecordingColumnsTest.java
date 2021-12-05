package datamanagement.data.recording;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordingColumnsTest {

    @Test
    void getRecordingHeaderTest() {
        RecordingColumns columns = new RecordingColumns();
        String expectedString = "identifier;description;activity;in_progress;start_time;end_time;notes";
        assertEquals(expectedString, columns.getRecordingHeader());
    }
}