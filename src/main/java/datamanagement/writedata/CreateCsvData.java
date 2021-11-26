package datamanagement.writedata;

import datamanagement.data.Recording;
import datamanagement.data.RecordingList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateCsvData {

    private final String csvHeader = "identifier;description;isActive;start time;end time;notes";

    public List<String> writeCsvData(RecordingList recordingList) {
        List<String> fileContent = new ArrayList<>(Arrays.asList(csvHeader));
        List<Recording> list = recordingList.getRecordings();
        for (Recording recording : list) {
            fileContent.add(recording.toCsvRow());
        }
        return fileContent;
    }

    public String getCsvHeader() {
        return csvHeader;
    }
}
