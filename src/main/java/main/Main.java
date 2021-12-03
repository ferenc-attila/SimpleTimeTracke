package main;

import userlayer.menu.MainMenu;

public class Main {

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialization();
        mainMenu.runMainMenu();
    }
}
