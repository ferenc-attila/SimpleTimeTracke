package datamanagement.data.activity;

import datamanagement.data.recording.Recording;

import java.util.ArrayList;
import java.util.List;

public class Activity {

    private int identifier;
    private String name;
    private String description;
    private String notes;
    private List<Recording> recordings = new ArrayList<>();

    public Activity(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public Activity(int identifier, String name, String description) {
        this(identifier, name);
        this.name = name;
        this.description = description;
    }

    public Activity(int identifier, String name, String description, String notes) {
        this(identifier, name, description);
        this.notes = notes;
    }

    public void addRecording(Recording recording) {
        recordings.add(recording);
    }

    @Override
    public String toString() {
        return identifier + ": " + name + ", desc.: " + description;
    }

    public String toCsvRow() {
        StringBuilder csvRow = new StringBuilder();
        String separator = ";";
        csvRow.append(identifier);
        addSeparator(csvRow, separator);
        csvRow.append(name);
        addSeparator(csvRow, separator);
        csvRow.append(createBlankStringIfNull(description));
        addSeparator(csvRow, separator);
        csvRow.append(createBlankStringIfNull(notes));

        return csvRow.toString();
    }

    private String createBlankStringIfNull(String string) {
        if (string == null) {
            return "";
        } else {
            return string;
        }
    }

    public String printActivityToMenu() {
        return identifier + ": " + name + ", desc.: " + createBlankStringIfNull(description) + "\nnotes: " + createBlankStringIfNull(notes) + "\n";
    }

    public int getMaxRecordingIdentifier() {
        int max = -1;
        for (Recording actual : recordings) {
            if (actual.getIdentifier() > max) {
                max = actual.getIdentifier();
            }
        }
        return max;
    }

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNotes() {
        return notes;
    }

    public List<Recording> getRecordings() {
        return recordings;
    }

    private void addSeparator(StringBuilder stringBuilder, String separator) {
        stringBuilder.append(separator);
    }
}
