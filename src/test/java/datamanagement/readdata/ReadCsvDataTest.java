package datamanagement.readdata;

import datamanagement.data.Recording;
import datamanagement.data.RecordingList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadCsvDataTest {

    ReadCsvData readData = new ReadCsvData();
    RecordingList recordingList = new RecordingList();
    List<String> fileContent = Arrays.asList("identifier;description;isActive;start time;end time;notes",
            "0;evening jogging;false;2021-06-04 18:25;;",
            "1;morning commute;true;2021-11-25 05:40;2021-11-25 06:10;by bus",
            "4;Northern Loon twitch;false;2021-08-12 19:30;2021-08-12 07:10;the bird was founded");
    List<Recording> list = recordingList.getRecordings();

    @Test
    void readDataTest() {
        assertEquals(0, list.size());
        readData.readData(fileContent, recordingList);
        assertEquals(3, list.size());
        assertEquals(4, list.get(2).getIdentifier());
        assertEquals("morning commute", list.get(1).getDescription());
        assertEquals(LocalDateTime.parse("2021-08-12T19:30"), list.get(2).getStartTime());
        assertEquals(LocalDateTime.of(2021, 8, 12, 7, 10), list.get(2).getEndTime());
        assertNull(list.get(0).getEndTime());
        assertEquals("by bus", list.get(1).getNotes());
        assertEquals("", list.get(0).getNotes());
        assertFalse(list.get(1).isActive());
        assertTrue(list.get(0).isActive());
    }
}