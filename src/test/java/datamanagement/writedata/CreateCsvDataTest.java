package datamanagement.writedata;

import datamanagement.data.recording.Recording;
import datamanagement.data.recording.RecordingList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateCsvDataTest {

    CreateCsvData write = new CreateCsvData();
    RecordingList recordingList = new RecordingList();
    Recording hiking = new Recording(0, "hiking in the mountains", LocalDateTime.parse("2021-06-12T15:22"));
    Recording housework = new Recording(1, "Cooking", LocalDateTime.parse("2021-05-10T10:11"));
    List<Recording> list = recordingList.getRecordings();

    @Test
    void writeCsvDataTest() {
        list.add(hiking);
        list.add(housework);
        List<String> csvData = write.writeCsvData(recordingList);
        assertEquals(3, csvData.size());
        assertEquals("identifier;description;in_progress;start_time;end_time;notes", csvData.get(0));
        assertEquals("0;hiking in the mountains;yes;2021-06-12 15:22;;null", csvData.get(1));
        assertEquals("1;Cooking;yes;2021-05-10 10:11;;null", csvData.get(2));
        list.get(1).finishRecording(LocalDateTime.parse("2021-05-10T12:38"));
        list.get(0).setNotes("It is fun!");
        csvData = write.writeCsvData(recordingList);
        assertEquals("1;Cooking;no;2021-05-10 10:11;2021-05-10 12:38;null", csvData.get(2));
        assertEquals("0;hiking in the mountains;yes;2021-06-12 15:22;;It is fun!", csvData.get(1));
    }
}