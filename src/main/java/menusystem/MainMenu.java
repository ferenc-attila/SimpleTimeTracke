package menusystem;

import java.util.Scanner;

public class MainMenu {

    private final String[] mainMenu = {"\n*** Simple Time Tracker ***\n",
            "Háttéradatok karbantartása",
            "Aktivitás indítása",
            "Aktivitás leállítása",
            "Adatok exportálása",
            "Adatok importálása",
            "Kilépés"};

    Scanner scanner = new Scanner(System.in);

    public void runMainMenu() {
        printMenu();

        System.out.println("Válassz egyet a fenti lehetőségek közül, és add meg a sorszámát:");
        String selectedMenuItem = scanner.nextLine();

        switch (selectedMenuItem) {
            case "1":

        }
    }

    private void printMenu() {
    }
}
