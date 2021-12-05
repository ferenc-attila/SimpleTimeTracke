package datamanagement.readdata;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;

import java.util.List;

public class ReadActivityCsv {

    public void readActivityData(List<String> fileContent, ActivityList activityList) {
        for (int i = 1; i < fileContent.size(); i++) {
            String[] cells = fileContent.get(i).split(";", 6);
            Activity activity = parseActivity(cells);
            activityList.getActivities().add(activity);
        }
    }

    private Activity parseActivity(String[] cells) {
        int identifier = Integer.parseInt(cells[0]);
        String name = cells[1];
        String description = cells[2];
        String notes = cells[3];
        return new Activity(identifier, name, description, notes);
    }
}
