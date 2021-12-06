package userlayer.functions;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import userlayer.menu.MainMenu;

import java.util.Scanner;

public class CreateActivity {

    private Scanner scanner = new Scanner(System.in);

    public void runCreateActivity(ActivityList activityList, MainMenu mainMenu) {
        System.out.println("\n\n*****              Create activity                 *****\n\n");
        System.out.println(activityList.printExistingActivities());
        System.out.println("\nEnter the name of the new activity!");
        String name = scanner.nextLine();
        if (activityList.isExistingActivity(name) || name.isBlank()) {
            System.out.println("\nThe entered name is in the list or blank! Try again!\n");
            runCreateActivity(activityList, mainMenu);
        } else {
            createUserDefinedActivity(activityList, name);
            System.out.println(activityList.printExistingActivities());
            runInnerMenu(activityList, mainMenu);
        }
    }

    private void runInnerMenu(ActivityList activityList, MainMenu mainMenu) {
        System.out.println("What do you want to do next? Enter a number!\n1.: Create another activity\n2.: Go back to main menu");
        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            runCreateActivity(activityList, mainMenu);
        } else if ("2".equals(choice)) {
            mainMenu.startFunctionSelector(activityList);
        } else {
            System.out.println("\nInvalid value! I'll go back to main menu!\n");
            mainMenu.startFunctionSelector(activityList);
        }
    }

    private void createUserDefinedActivity(ActivityList activityList, String name) {
        System.out.println("\nEnter the description of the new activity, or press enter!");
        String description = scanner.nextLine();
        System.out.println("\nEnter some notes for the new activity, or press enter!");
        String notes = scanner.nextLine();
        int identifier = activityList.getMaxIdentifier() + 1;
        activityList.addActivity(new Activity(identifier, name, description, notes));
    }
}
