package datahandling.readdata;

import datahandling.data.Recording;
import datahandling.data.RecordingList;

import java.time.LocalDateTime;
import java.util.List;

public class ReadCsvData {

    public void readData(List<String> fileContent, RecordingList recordingList) {
        for (String row : fileContent) {
            String[] cells = row.split(";");
            Recording recording = parseRecording(cells);
            recordingList.getRecordings().add(recording);
        }
    }

    private Recording parseRecording(String[] cells) {
        int identifier = Integer.parseInt(cells[0]);
        String description = cells[1];
        LocalDateTime startTime = parseTime(cells, 3);
        LocalDateTime endTime = createEndTimeValue(cells);
        String notes = cells[5];
        return createRecording(identifier, description, startTime, endTime, notes);
    }

    private Recording createRecording(int identifier, String description, LocalDateTime startTime, LocalDateTime endTime, String notes) {
        Recording recording = new Recording(identifier, description, startTime);
        if (endTime != null) {
            recording.setEndTime(endTime);
        }
        recording.setNotes(notes);
        return recording;
    }

    private LocalDateTime createEndTimeValue(String[] cells) {
        if (!cells[4].isEmpty()) {
            return parseTime(cells, 4);
        } else {
            return null;
        }
    }

    private LocalDateTime parseTime(String[] cells, int i) {
        return LocalDateTime.parse(cells[i].replace(" ", "T"));
    }
}
