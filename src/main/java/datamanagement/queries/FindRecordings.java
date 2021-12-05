package datamanagement.queries;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;

import java.util.ArrayList;
import java.util.List;

public class FindRecordings {

    public Recording findActiveRecording(ActivityList activityList) {
        List<Recording> list = getAllRecordings(activityList);
        Recording activeRecording = null;
        if (numberOfActiveRecording(activityList) == 0) {
            throw new IllegalArgumentException("No active recording in the list!");
        } else if (numberOfActiveRecording(activityList) > 1) {
            throw new IllegalArgumentException(("Invalid list, " + numberOfActiveRecording(activityList) + " active records in the list!"));
        } else {
            for (Recording recording : list) {
                if (recording.isActive()) {
                    activeRecording = recording;
                }
            }
        }
        return activeRecording;
    }

    public int numberOfActiveRecording(ActivityList activityList) {
        List<Recording> list = getAllRecordings(activityList);
        int count = 0;
        for (Recording recording : list) {
            if (recording.isActive()) {
                count++;
            }
        }
        return count;
    }

    public List<Recording> getAllRecordings(ActivityList activityList) {
        List<Recording> allRecordings = new ArrayList<>();
        for (Activity activity : activityList.getActivities()) {
            allRecordings.addAll(activity.getRecordings());
        }
        return allRecordings;
    }
}
