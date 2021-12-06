package userlayer.functions;

import datamanagement.csvdata.CsvData;
import datamanagement.data.activity.ActivityList;
import filemanagement.WriteTextFile;

import java.nio.file.Paths;
import java.util.List;

public class Exit {

    private CsvData csvData = new CsvData();
    private WriteTextFile writeTextFile = new WriteTextFile();

    public void runExit(ActivityList activityList) {
        System.out.println("\n\n*****                   Exit                       *****\n\n");
        if (getNumberOfRecordings(activityList) == 0) {
            System.out.println("No recordings to save!");
        } else {
            writeListOfRecordings(activityList);
        }
        if (getNumberOfActivities(activityList) == 0) {
            System.out.println("No activities to save!");
        } else {
            writeListOfActivities(activityList);
        }
        System.out.println("\n\n*****       Thanks for using the application!      *****\n\n");
    }

    private int getNumberOfRecordings(ActivityList activityList) {
        return activityList.getAllRecordings().size();
    }

    private int getNumberOfActivities(ActivityList activityList) {
        return activityList.getActivities().size();
    }

    private void writeListOfActivities(ActivityList activityList) {
        List<String> activityDataForSave = csvData.writeCsvStringFromActivities(activityList);
        try {
            System.out.println("Saving data ...");
            writeTextFile.writeTextFile(Paths.get(""), "activities.csv", activityDataForSave);
            System.out.println("Activities saved successfully.");
        } catch (IllegalStateException ise) {
            System.out.println(ise.getMessage());
            ise.getCause().printStackTrace();
        } finally {
            System.out.println("Continue...");
        }
    }

    private void writeListOfRecordings(ActivityList activityList) {
        List<String> recordingDataForSave = csvData.writeCsvStringFromRecordings(activityList);
        try {
            System.out.println("Saving data ...");
            writeTextFile.writeTextFile(Paths.get(""), "recordings.csv", recordingDataForSave);
            System.out.println("Recordings saved successfully.");
        } catch (IllegalStateException ise) {
            System.out.println(ise.getMessage());
            ise.getCause().printStackTrace();
        } finally {
            System.out.println("Continue...");
        }
    }
}
