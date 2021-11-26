package userlayer.menusystem;

import datamanagement.data.Recording;
import datamanagement.data.RecordingList;
import datamanagement.queries.FindRecordings;
import datamanagement.readdata.ImportCsvData;
import datamanagement.writedata.CreateCsvData;
import filemanagement.ReadTextFile;
import filemanagement.WriteTextFile;
import service.recording.StartRecording;
import service.recording.StopRecording;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private final List<String> mainMenuItems = Arrays.asList("Start recording",
            "Stop recording",
            "Exit\n");

    Scanner scanner = new Scanner(System.in);
    RecordingList recordingList = new RecordingList();
    StartRecording start = new StartRecording();
    StopRecording stop = new StopRecording();
    ReadTextFile readFile = new ReadTextFile();
    WriteTextFile writeFile = new WriteTextFile();
    ImportCsvData createData = new ImportCsvData();
    CreateCsvData createDataStrings = new CreateCsvData();
    FindRecordings find = new FindRecordings();
    List<Recording> listOfRecords = recordingList.getRecordings();

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.runMainMenu();
    }

    private void runMainMenu() {
        initialization();
        printMenu();

        System.out.println("Select one function above and enter its number:");
        String selectedMenuItem = scanner.nextLine();

        switch (selectedMenuItem) {
            case "1" -> {
                runStartRecording();
                runMainMenu();
            }
            case "2" -> {
                runStopRecording();
                runMainMenu();
            }
            case "3" -> runExit();
            default -> {
                System.out.println("You entered an invalid value! Try again!");
                runMainMenu();
            }
        }
    }

    private void initialization() {
        System.out.println("\n*** Simple Time Tracker ***\n");
        System.out.println("Initialization ...");
        try {
            List<String> fileContent = readFile.readTextFile(Path.of("src/main/resources/recordings.csv"));
            System.out.println("Scanning database ...");
            createData.readData(fileContent, recordingList);
            System.out.println("Database scanned successfully.");
        } catch (IllegalStateException ise) {
            System.out.println("Unable to read database file! Starting with empty database.");
        }
    }

    private void runExit() {
        System.out.println("\n** Exit **");
        if (listOfRecords.isEmpty()) {
            System.out.println("Nothing to save!");
            System.out.println("Thanks for using the application!");
        } else {
            List<String> dataForSave = createDataStrings.writeCsvData(recordingList);
            try {
                System.out.println("Saving data ...");
                writeFile.writeTextFile(Path.of("src/main/resources"), "recordings.csv", dataForSave);
                System.out.println("Data saved successfully.");
            } catch (IllegalStateException ise) {
                System.out.println(ise.getMessage());
                ise.getCause().printStackTrace();
            } finally {
                System.out.println("Thanks for using the application!");
            }
        }
    }

    private void runStartRecording() {
        System.out.println("\n** Start recording **\n");
        System.out.println("Enter the name of the new recording!");
        String description = scanner.nextLine();
        try {
            start.startRecording(description, recordingList);
            System.out.println(start.printStartMessage(listOfRecords.get(listOfRecords.size() - 1)));
        } catch (IllegalStateException ise) {
            System.out.println(ise.getMessage());
        } finally {
            runMainMenu();
        }
    }

    private void runStopRecording() {
        System.out.println("\n** Stop recording **\n");
        try {
            Recording finishedRecord = find.findActiveRecording(listOfRecords);
            stop.stopRecording(finishedRecord);
            System.out.println(stop.printStopMessage(finishedRecord));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        } finally {
            runMainMenu();
        }
    }

    private void printMenu() {
        System.out.println();
        for (int i = 0; i < mainMenuItems.size(); i++) {
            System.out.println((i + 1) + ".: " + mainMenuItems.get(i));
        }
    }
}
