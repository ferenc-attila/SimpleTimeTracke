package datamanagement.data.activity;

import datamanagement.data.recording.Recording;

import java.util.ArrayList;
import java.util.List;

public class ActivityList {

    private List<Activity> activities = new ArrayList<>();

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public Activity findActivity (String activityName) {
        for (Activity activity : activities) {
            if (activityName.equals(activity.getName())) {
                return activity;
            }
        }
        throw new IllegalArgumentException("Unknown or empty activity: '" + activityName + "'!");
    }

    public Activity findActivity (int index) {
        for (Activity activity : activities) {
            if (index == activity.getIdentifier()) {
                return activity;
            }
        }
        throw new IllegalArgumentException("Unknown or empty activity at index: '" + index + "'!");
    }

    public Activity findActivity (Activity activity) {
        for (Activity actual : activities) {
            if (actual.equals(activity)) {
                return activity;
            }
        }
        throw new IllegalArgumentException("Activity '" + activity.toString() + "' is not in this list!");
    }

    public boolean isExistingActivity (String activityName) {
        for (Activity activity : activities) {
            if (activityName.equals(activity.getName())) {
                return true;
            }
        }
        return false;
    }

    public int getMaxIdentifier () {
        int max = -1;
        for (Activity actual : activities) {
            if (actual.getIdentifier() > max) {
                max = actual.getIdentifier();
            }
        }
        return max;
    }

    public Recording findActiveRecording() {
        List<Recording> list = getAllRecordings();
        Recording activeRecording = null;
        if (numberOfActiveRecording() == 0) {
            throw new IllegalArgumentException("No active recording in the list!");
        } else if (numberOfActiveRecording() > 1) {
            throw new IllegalArgumentException(("Invalid list, " + numberOfActiveRecording() + " active records in the list!"));
        } else {
            for (Recording recording : list) {
                if (recording.isActive()) {
                    activeRecording = recording;
                }
            }
        }
        return activeRecording;
    }

    public int numberOfActiveRecording() {
        List<Recording> list = getAllRecordings();
        int count = 0;
        for (Recording recording : list) {
            if (recording.isActive()) {
                count++;
            }
        }
        return count;
    }

    public List<Recording> getAllRecordings() {
        List<Recording> allRecordings = new ArrayList<>();
        for (Activity activity : this.activities) {
            allRecordings.addAll(activity.getRecordings());
        }
        return allRecordings;
    }

    public String printStatus() {
        int numberOfRecordings = getAllRecordings().size();
        int numberOfActivities = activities.size();
        return "\nThere are " + numberOfRecordings + " recordings, " + numberOfActivities + " activities in the database.\n";
    }

    public StringBuilder printExistingActivities() {
        StringBuilder stringOfActivities = new StringBuilder("\nExisting activities:\n");
        for (Activity actual : activities) {
            stringOfActivities.append(actual.printActivityToMenu());
        }
        return stringOfActivities;
    }
}
