package service.recording;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;
import userlayer.menu.MainMenu;

import java.time.LocalDateTime;
import java.util.Scanner;

public class StartRecording {

    private Scanner scanner = new Scanner(System.in);

    public void runStartRecording(ActivityList activityList, MainMenu mainMenu) {
        System.out.println("\n\n*****             Start recording                  *****\n\n");
        int activeRecordings = activityList.numberOfActiveRecording();
        if (activeRecordings > 0) {
            System.out.println("\n\nActive recording running! Try to stop it before start another!");
            System.out.println("Going back to main menu\n\n");
            mainMenu.startFunctionSelector(activityList);
        } else {
            System.out.println(activityList.printExistingActivities());
            System.out.println("Select an activity!");
            Activity activity = getActualActivity(activityList, mainMenu);
            System.out.println("Enter the description of the new recording!");
            String description = scanner.nextLine();
            startRecording(description, activityList, activity);
            mainMenu.startFunctionSelector(activityList);
        }
    }

    private Activity getActualActivity(ActivityList activityList, MainMenu mainMenu) {
        int activityId;
        Activity activity = null;
        try {
            activityId = scanner.nextInt();
            scanner.nextLine();
            activity = activityList.findActivity(activityId);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid number! Try again!");
            runStartRecording(activityList, mainMenu);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
            System.out.println("Try again!");
            runStartRecording(activityList, mainMenu);
        }
        return activity;
    }

    public void startRecording(String description, ActivityList activityList, Activity activity) {
        int identifier = createIdentifier(activityList);
        Recording recording = new Recording(identifier, description, activity, LocalDateTime.now());
        activity.addRecording(recording);
    }

    private int createIdentifier(ActivityList activityList) {
        int maxIdentifier = -1;
        for (Activity actual : activityList.getActivities()) {
            if (actual.getMaxRecordingIdentifier() > maxIdentifier) {
                maxIdentifier = actual.getMaxRecordingIdentifier();
            }
        }
        return maxIdentifier + 1;
    }
}
