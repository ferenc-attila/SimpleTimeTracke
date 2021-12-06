package datamanagement.csvdata;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityColumns;
import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;
import datamanagement.data.recording.RecordingColumns;

import java.util.ArrayList;
import java.util.List;

public class CsvData {

    private List<String> activityCsvStringList = new ArrayList<>();
    private List<String> recordingCsvStringList = new ArrayList<>();
    private ActivityColumns activityColumns = new ActivityColumns();
    private RecordingColumns recordingColumns = new RecordingColumns();

    public List<String> writeCsvStringFromActivities(ActivityList activityList) {
        activityCsvStringList.clear();
        activityCsvStringList.add(activityColumns.getActivityHeader());
        for (Activity activity : activityList.getActivities()) {
            activityCsvStringList.add(activity.toCsvRow());
        }
        return activityCsvStringList;
    }

    public List<String> writeCsvStringFromRecordings(ActivityList activityList) {
        recordingCsvStringList.clear();
        recordingCsvStringList.add(recordingColumns.getRecordingHeader());
        List<Recording> list = activityList.getAllRecordings();
        for (Recording recording : list) {
            recordingCsvStringList.add(recording.toCsvRow());
        }
        return recordingCsvStringList;
    }
}
