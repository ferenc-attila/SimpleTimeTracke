package datamanagement.data.activity;

import datamanagement.data.recording.Recording;

import java.util.ArrayList;
import java.util.List;

public class Activity {

    private String name;
    private String description;
    private String notes;
    private List<Recording> recordings = new ArrayList<>();

    public Activity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addRecording(Recording recording) {
        recordings.add(recording);
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
}
