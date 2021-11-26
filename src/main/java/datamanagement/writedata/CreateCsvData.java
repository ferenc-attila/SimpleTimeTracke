package datamanagement.writedata;

import datamanagement.data.Recording;
import datamanagement.data.RecordingColumns;
import datamanagement.data.RecordingList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateCsvData {

    public List<String> writeCsvData(RecordingList recordingList) {
        String csvHeader = RecordingColumns.getHeader();
        List<String> fileContent = new ArrayList<>(Arrays.asList(csvHeader));
        List<Recording> list = recordingList.getRecordings();
        for (Recording recording : list) {
            fileContent.add(recording.toCsvRow());
        }
        return fileContent;
    }
}
