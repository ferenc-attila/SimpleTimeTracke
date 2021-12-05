package userlayer.menu;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;
import datamanagement.queries.FindRecordings;
import datamanagement.readdata.ReadActivityCsv;
import datamanagement.readdata.ReadRecordingCsv;
import datamanagement.writedata.CreateActivityCsvData;
import datamanagement.writedata.CreateRecordingCsvData;
import filemanagement.ReadTextFile;
import filemanagement.WriteTextFile;
import service.recording.StartRecording;
import service.recording.StopRecording;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private final List<String> mainMenuItems = Arrays.asList("Create activity",
            "Start recording",
            "Stop recording",
            "Exit");
    private ActivityList activityList = new ActivityList();
    private Scanner scanner = new Scanner(System.in);
    private StartRecording start = new StartRecording();
    private StopRecording stop = new StopRecording();
    private ReadTextFile readFile = new ReadTextFile();
    private WriteTextFile writeFile = new WriteTextFile();
    private ReadActivityCsv createActivityData = new ReadActivityCsv();
    private ReadRecordingCsv createRecordingData = new ReadRecordingCsv();
    private CreateRecordingCsvData createRecordingCsvData = new CreateRecordingCsvData();
    private CreateActivityCsvData createActivityCsvData = new CreateActivityCsvData();
    private FindRecordings find = new FindRecordings();
    private List<Activity> listOfActivities = activityList.getActivities();

    public void runMainMenu() {
        printActiveRecording();
        printMenu();

        System.out.println("Select one function above and enter its number:");
        String selectedMenuItem = scanner.nextLine();

        switch (selectedMenuItem) {
            case "1":
                runCreateActivity();
                break;
            case "2":
                runStartRecording();
                break;
            case "3":
                runStopRecording();
                break;
            case "4":
                runExit();
                break;
            default:
                System.out.println("You entered an invalid value! Try again!");
                runMainMenu();
        }
    }

    private void runCreateActivity() {
        System.out.println("** Create new Activity **\n");
        System.out.println("\nExisting activities:");
        for (Activity actual : activityList.getActivities()) {
            System.out.println(actual.printActivityToMenu());
        }
        System.out.println("\nEnter the name of the new activity!");
        String name = scanner.nextLine();
        if (activityList.isExistingActivity(name) || name.isBlank()) {
            System.out.println("\nThe entered name is in the list or blank! Try again!\n");
            runCreateActivity();
        } else {
            System.out.println("\nEnter the description of the new activity, or press enter!");
            String description = scanner.nextLine();
            System.out.println("\nEnter some notes for the new activity, or press enter!");
            String notes = scanner.nextLine();
            int identifier = activityList.getMaxIdentifier() + 1;
            Activity createdActivity = new Activity(identifier, name, description, notes);
            activityList.addActivity(createdActivity);
            System.out.println("What do you want to do next? Enter a number!\n1.: Create another activity\n2.: Go back to main menu");
            String choice = scanner.nextLine();
            if ("1".equals(choice)) {
                runCreateActivity();
            } else if ("2".equals(choice)) {
                runMainMenu();
            } else {
                System.out.println("\nInvalid value! I'll go back to main menu!\n");
                runMainMenu();
            }
        }
    }

    public void initialization() {
        System.out.println("\n*** Simple Time Tracker ***\n");
        System.out.println("Initialization ...");
        try {
            List<String> activityFileContent = readFile.readTextFile(Paths.get("activities.csv"));
            List<String> recordingFileContent = readFile.readTextFile(Paths.get("recordings.csv"));
            System.out.println("Scanning database ...");
            createActivityData.readActivityData(activityFileContent, activityList);
            createRecordingData.readData(recordingFileContent, activityList);
            System.out.println("Database scanned successfully.");
            int numberOfRecordings = getNumberOfRecordings();
            int numberOfActivities = listOfActivities.size();
            System.out.println("There are " + numberOfRecordings + " recordings inside " + numberOfActivities + " activities in the database.");
        } catch (IllegalStateException ise) {
            System.out.println("Unable to read database file! Starting with empty database.");
        }
    }

    private int getNumberOfRecordings() {
        int count = 0;
        for (Activity activity : listOfActivities) {
            count += activity.getRecordings().size();
        }
        return count;
    }

    private int getNumberOfActivities() {
        return listOfActivities.size();
    }

    private void printActiveRecording() {
        int numberOfActiveRecords = find.numberOfActiveRecording(activityList);
        if (numberOfActiveRecords == 0) {
            System.out.println("\nNo recording in progress.");
        } else if (numberOfActiveRecords > 1) {
            System.out.println("Warning! Invalid data in the database! There are more than one recordings in progress!");
        } else {
            Recording activeRecording = find.findActiveRecording(activityList);
            System.out.println("\nThe details of the active recording are:\n\n" + activeRecording.toString());
        }
    }

    private void runExit() {
        System.out.println("\n** Exit **");
        if (getNumberOfRecordings() == 0) {
            System.out.println("No recordings to save!");
        } else {
            writeListOfRecordings();
        }
        if (getNumberOfActivities() == 0) {
            System.out.println("No activities to save!");
        } else {
            writeListOfActivities();
        }
        System.out.println("Thanks for using the application!");
    }

    private void writeListOfActivities() {
        List<String> activityDataForSave = createActivityCsvData.writeActivityCsvData(activityList);
        try {
            System.out.println("Saving data ...");
            writeFile.writeTextFile(Paths.get(""), "activities.csv", activityDataForSave);
            System.out.println("Activities saved successfully.");
        } catch (IllegalStateException ise) {
            System.out.println(ise.getMessage());
            ise.getCause().printStackTrace();
        } finally {
            System.out.println("Continue...");
        }
    }

    private void writeListOfRecordings() {
        List<String> recordingDataForSave = createRecordingCsvData.writeRecordingCsvData(activityList);
        try {
            System.out.println("Saving data ...");
            writeFile.writeTextFile(Paths.get(""), "recordings.csv", recordingDataForSave);
            System.out.println("Recordings saved successfully.");
        } catch (IllegalStateException ise) {
            System.out.println(ise.getMessage());
            ise.getCause().printStackTrace();
        } finally {
            System.out.println("Continue...");
        }
    }

    private void printMenu() {
        System.out.println();
        for (int i = 0; i < mainMenuItems.size(); i++) {
            System.out.println((i + 1) + ".: " + mainMenuItems.get(i));
        }
    }

    private void runStartRecording() {
        System.out.println("\n** Start recording **\n");
        printActivities();
        System.out.println("Select an activity!");
        int activityId;
        Activity activity = null;
        try {
            activityId = scanner.nextInt();
            scanner.nextLine();
            activity = activityList.findActivity(activityId);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid number! Try again!");
            runStartRecording();
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            System.out.println("Try again!");
            runStartRecording();
        }
        System.out.println("Enter the name of the new recording!");
        String description = scanner.nextLine();
        try {
            start.startRecording(description, activityList, activity);
        } catch (IllegalStateException ise) {
            System.out.println(ise.getMessage());
        } finally {
            runMainMenu();
        }
    }

    private void printActivities() {
        for (Activity activity : listOfActivities) {
            System.out.println(activity.printActivityToMenu());
        }
    }

    private void runStopRecording() {
        System.out.println("\n** Stop recording **\n");
        try {
            Recording finishedRecord = find.findActiveRecording(activityList);
            stop.stopRecording(finishedRecord);
            System.out.println(stop.printStopMessage(finishedRecord));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        } finally {
            runMainMenu();
        }
    }
}
