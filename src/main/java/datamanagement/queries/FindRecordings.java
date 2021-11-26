package datamanagement.queries;

import datamanagement.data.Recording;

import java.util.List;

public class FindRecordings {

    public Recording findActiveRecording(List<Recording> list) {
        Recording activeRecording = null;
        if (numberOfActiveRecording(list) == 0) {
            throw new IllegalArgumentException("No active recording in the list!");
        } else if (numberOfActiveRecording(list) > 1) {
            throw new IllegalArgumentException(("Invalid list, " + numberOfActiveRecording(list) + " active records in the list!"));
        } else {
            for (Recording recording : list) {
                if (recording.isActive()) {
                    activeRecording = recording;
                }
            }
        }
        return activeRecording;
    }

    public int numberOfActiveRecording(List<Recording> list) {
        int count = 0;
        for (Recording recording : list) {
            if (recording.isActive()) {
                count++;
            }
        }
        return count;
    }
}
