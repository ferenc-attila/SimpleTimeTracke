package datamanagement.writedata;

import datamanagement.data.recording.Recording;
import datamanagement.data.recording.RecordingColumns;
import datamanagement.data.recording.RecordingList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateCsvData {

    RecordingColumns columns = new RecordingColumns();

    public List<String> writeCsvData(RecordingList recordingList) {
        String csvHeader = columns.getHeader();
        List<String> fileContent = new ArrayList<>(Arrays.asList(csvHeader));
        List<Recording> list = recordingList.getRecordings();
        for (Recording recording : list) {
            fileContent.add(recording.toCsvRow());
        }
        return fileContent;
    }
}
