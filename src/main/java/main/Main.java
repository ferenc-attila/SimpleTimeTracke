package main;

import datamanagement.data.activity.ActivityList;
import userlayer.menu.MainMenu;

public class Main {

    public static void main(String[] args) {
        ActivityList activityList = new ActivityList();
        MainMenu mainMenu = new MainMenu();
        mainMenu.runMainMenu(activityList);
    }
}
