package datamanagement.writedata;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityColumns;
import datamanagement.data.activity.ActivityList;

import java.util.ArrayList;
import java.util.List;

public class CreateActivityCsvData {

    ActivityColumns columns = new ActivityColumns();

    public List<String> writeActivityCsvData(ActivityList activityList) {
        List<String> fileContent = new ArrayList<>();
        fileContent.add(columns.getActivityHeader());
        for (Activity activity : activityList.getActivities()) {
            fileContent.add(activity.toCsvRow());
        }
        return fileContent;
    }
}
