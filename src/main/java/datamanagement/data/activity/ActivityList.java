package datamanagement.data.activity;

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
}
