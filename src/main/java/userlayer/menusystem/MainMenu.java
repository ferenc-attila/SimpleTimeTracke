package userlayer.menusystem;

import java.util.Scanner;

public class MainMenu {

    private final String[] mainMenu = {"\n*** Simple Time Tracker ***\n",
            "Start recording",
            "Stop recording",
            "Exit"};

    Scanner scanner = new Scanner(System.in);

    public void runMainMenu() {
        printMenu();

        System.out.println("Select one function above and enter its number:");
        String selectedMenuItem = scanner.nextLine();

        switch (selectedMenuItem) {
            case "1":

        }
    }

    private void printMenu() {
    }
}
