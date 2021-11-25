package userlayer.menusystem;

import datahandling.data.Recording;
import datahandling.data.RecordingList;
import datahandling.queries.FindRecordings;
import datahandling.writedata.CreateCsvData;
import filehandling.WriteTextFile;
import service.recording.StartRecording;
import service.recording.StopRecording;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private final List<String> mainMenuItems = Arrays.asList("\n*** Simple Time Tracker ***\n",
            "Start recording",
            "Stop recording",
            "Exit");

    Scanner scanner = new Scanner(System.in);
    RecordingList recordingList = new RecordingList();
    StartRecording start = new StartRecording();
    StopRecording stop = new StopRecording();
    WriteTextFile write = new WriteTextFile();
    CreateCsvData createDataStrings = new CreateCsvData();
    FindRecordings find = new FindRecordings();
    List<Recording> listOfRecords = recordingList.getRecordings();

    public void runMainMenu() {
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

    private void runExit() {
        List<String> dataForSave = createDataStrings.writeCsvData(recordingList);
        try {
            System.out.println("Saving data ...");
            write.writeTextFile(Path.of("src/main/resources"), "recordings.csv", dataForSave);
            System.out.println("Data saved successfully.");
        } catch (IllegalStateException ise) {
            System.out.println(ise.getMessage());
            ise.getCause().printStackTrace();
        } finally {
            System.out.println("Exiting");
        }
    }

    private void runStartRecording() {
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
        System.out.println(mainMenuItems);
    }
}
