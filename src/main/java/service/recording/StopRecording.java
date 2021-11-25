package service.recording;

import datahandling.data.Recording;
import datahandling.data.RecordingList;
import datahandling.queries.FindRecordings;

import java.time.LocalDateTime;

public class StopRecording {

    FindRecordings find = new FindRecordings();

    public void stopRecording(Recording recording) {
        recording.setEndTime(LocalDateTime.now());
    }

    public StringBuilder printStopMessage(Recording recording) {
        StringBuilder message = new StringBuilder();
        message.append("Recording ended with parameters below:\n");
        message.append(recording.toString());
        return message;
    }
}
