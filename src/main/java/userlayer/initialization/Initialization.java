package userlayer.initialization;

import datamanagement.data.activity.ActivityList;
import datamanagement.stringdata.StringData;

public class Initialization {

    private StringData stringData = new StringData("activities.csv", "recordings.csv");

    public void initialization(ActivityList activityList) {
        System.out.println("Scanning database ...");
        stringData.buildLists(activityList);
        activityList.printStatus();
    }
}
