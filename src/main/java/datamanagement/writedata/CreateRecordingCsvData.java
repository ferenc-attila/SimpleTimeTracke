package datamanagement.writedata;

import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;
import datamanagement.data.recording.RecordingColumns;

import java.util.ArrayList;
import java.util.List;

public class CreateRecordingCsvData {

    RecordingColumns columns = new RecordingColumns();

    public List<String> writeRecordingCsvData(ActivityList activityList) {
        List<String> fileContent = new ArrayList<>();
        fileContent.add(columns.getRecordingHeader());
        List<Recording> list = activityList.getAllRecordings();
        for (Recording recording : list) {
            fileContent.add(recording.toCsvRow());
        }
        return fileContent;
    }
}
