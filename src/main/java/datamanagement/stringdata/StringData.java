package datamanagement.stringdata;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;
import filemanagement.ReadTextFile;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class StringData {

    private List<String> activityStringData;
    private List<String> recordingStringData;
    private String activityFilename;
    private String recordingFilename;
    private ReadTextFile readTextFile = new ReadTextFile();

    public StringData(String activityFilename, String recordingFilename) {
        this.activityFilename = activityFilename;
        this.recordingFilename = recordingFilename;
    }

    public void buildLists(ActivityList activityList) {
        try {
            this.activityStringData = readFileToString(activityFilename);
            createActivitiesFromString(activityList);
            System.out.println("Activity data created from file.");
        } catch (IllegalStateException ise) {
            System.out.println("Unable to read activities from file!");
        }
        try {
            this.recordingStringData = readFileToString(recordingFilename);
            createRecordingsFromString(activityList);
            System.out.println("Recording data created from file.");
        } catch (IllegalStateException ise) {
            System.out.println("Unable to read recordings from file!");
        }
    }

    public void createActivitiesFromString(ActivityList activityList) {
        for (int i = 1; i < activityStringData.size(); i++) {
            String[] cells = activityStringData.get(i).split(";", 6);
            Activity activity = parseActivity(cells);
            activityList.getActivities().add(activity);
        }
    }

    public void createRecordingsFromString(ActivityList activityList) {
        for (int i = 1; i < recordingStringData.size(); i++) {
            String[] cells = recordingStringData.get(i).split(";", 7);
            Recording recording = parseRecording(cells, activityList);
            activityList.findActivity(recording.getActivity()).addRecording(recording);
        }
    }

    private List<String> readFileToString(String fileName) {
        return readTextFile.readTextFile(Paths.get(fileName));
    }

    private Activity parseActivity(String[] cells) {
        int identifier = Integer.parseInt(cells[0]);
        String name = cells[1];
        String description = cells[2];
        String notes = cells[3];
        return new Activity(identifier, name, description, notes);
    }

    private Recording parseRecording(String[] cells, ActivityList activityList) {
        Activity activity;
        int identifier = Integer.parseInt(cells[0]);
        String description = cells[1];
        if (activityList.isExistingActivity(cells[2])) {
            activity = activityList.findActivity(cells[2]);
        } else {
            int activityIdentifier = activityList.getMaxIdentifier() + 1;
            activity = new Activity(activityIdentifier, cells[2], "", "");
            activityList.addActivity(activity);
        }
        LocalDateTime startTime = parseTime(cells, 4);
        LocalDateTime endTime = createEndTimeValue(cells);
        String notes = cells[6];
        return createRecording(identifier, description, activity, startTime, endTime, notes);
    }

    private Recording createRecording(int identifier, String description, Activity activity, LocalDateTime startTime, LocalDateTime endTime, String notes) {
        Recording recording = new Recording(identifier, description, activity, startTime);
        if (endTime != null) {
            recording.finishRecording(endTime);
        }
        recording.setNotes(notes);
        return recording;
    }

    private LocalDateTime createEndTimeValue(String[] cells) {
        if ((cells[4] == null) || (cells[5].isBlank())) {
            return null;
        } else {
            return parseTime(cells, 5);
        }
    }

    private LocalDateTime parseTime(String[] cells, int i) {
        return LocalDateTime.parse(cells[i].replace(" ", "T"));
    }

    public List<String> getActivityStringData() {
        return activityStringData;
    }

    public List<String> getRecordingStringData() {
        return recordingStringData;
    }
}
