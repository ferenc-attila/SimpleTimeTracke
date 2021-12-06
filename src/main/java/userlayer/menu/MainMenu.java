package userlayer.menu;

import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;
import service.recording.StartRecording;
import service.recording.StopRecording;
import userlayer.functions.CreateActivity;
import userlayer.functions.Exit;
import userlayer.initialization.Initialization;

import java.util.Scanner;

public class MainMenu {

    private Scanner scanner = new Scanner(System.in);
    private Initialization init = new Initialization();
    private CreateActivity createActivity = new CreateActivity();
    private StartRecording startRecording = new StartRecording();
    private StopRecording stopRecording = new StopRecording();
    private Exit exit = new Exit();

    public void runMainMenu (ActivityList activityList) {
        printWelcomeScreen();
        init.initialization(activityList);
        System.out.println(activityList.printStatus());
        startFunctionSelector(activityList);
    }

    public void startFunctionSelector(ActivityList activityList) {
        printActiveRecording(activityList);
        printMenuItems();
        String selectedMenuItem = scanner.nextLine();

        switch (selectedMenuItem) {
            case "1":
                createActivity.runCreateActivity(activityList, this);
                break;
            case "2":
                startRecording.runStartRecording(activityList, this);
                break;
            case "3":
                stopRecording.runStopRecording(activityList, this);
                break;
            case "4":
                exit.runExit(activityList);
                break;
            default:
                System.out.println("\nYou entered an invalid value! Try again!\n");
                startFunctionSelector(activityList);
        }
    }

    private void printWelcomeScreen() {
        System.out.println("\n\n ________________________________________________________");
        System.out.println("|                                                        |");
        System.out.println("|                                                        |");
        System.out.println("|              *** Simple Time Tracker ***               |");
        System.out.println("|                                                        |");
        System.out.println("|                                                        |");
        System.out.println("|________________________________________________________|\n\n");
    }

    private void printActiveRecording(ActivityList activityList) {
        int numberOfActiveRecording = activityList.numberOfActiveRecording();
        if (numberOfActiveRecording == 0) {
            System.out.println("\nNo recording in progress.");
        } else if (numberOfActiveRecording > 1) {
            System.out.println("Warning! Invalid data in the database!\nThere are more than one recordings in progress!");
            System.out.println("Exiting ...");
            exit.runExit(activityList);
        } else {
            Recording activeRecording = activityList.findActiveRecording();
            System.out.println("\nThe details of the active recording are:\n\n" + activeRecording.toString());
        }
    }

    private void printMenuItems() {
        System.out.println("*                 1.: Create activity                   *");
        System.out.println("*                 2.: Start recording                   *");
        System.out.println("*                 3.: Stop recording                    *");
        System.out.println("*                 4.: Exit                              *");
        System.out.println("*                                                       *");
        System.out.println("*    Select one function above and enter its number!    *\n\n");
    }
}
