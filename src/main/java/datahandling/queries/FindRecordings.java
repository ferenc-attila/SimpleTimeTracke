package datahandling.queries;

import datahandling.data.Recording;

import java.util.ArrayList;
import java.util.List;

public class FindRecordings {

    public Recording findActiveRecording(List<Recording> list) {
        List<Recording> activeRecordings = new ArrayList<>();
        for (Recording recording : list) {
            if (recording.isActive()) {
                activeRecordings.add(recording);
            }
        }
        if (activeRecordings.isEmpty()) {
            throw new IllegalArgumentException("No active recording in the list!");
        } else if (activeRecordings.size() > 1) {
            throw new IllegalArgumentException(("Invalid list, " + activeRecordings.size() + " active records in the list!"));
        } else {
            return activeRecordings.get(0);
        }
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
