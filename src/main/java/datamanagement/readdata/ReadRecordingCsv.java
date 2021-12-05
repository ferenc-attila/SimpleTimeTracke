package datamanagement.readdata;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;

import java.time.LocalDateTime;
import java.util.List;

public class ReadRecordingCsv {

    public void readData(List<String> fileContent, ActivityList activityList) {
        for (int i = 1; i < fileContent.size(); i++) {
            String[] cells = fileContent.get(i).split(";", 7);
            Recording recording = parseRecording(cells, activityList);
            activityList.findActivity(recording.getActivity()).addRecording(recording);
        }
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
}
