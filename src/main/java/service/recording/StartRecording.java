package service.recording;

import datahandling.data.Recording;
import datahandling.data.RecordingList;

import java.time.LocalDateTime;
import java.util.List;

public class StartRecording {

    public void startRecording(String description, RecordingList recordingList) {
        int identifier = createIdentifier(recordingList);
        Recording recording = new Recording(identifier, description, LocalDateTime.now());
        recordingList.addRecording(recording);
    }

    public StringBuilder printStartMessage(Recording recording) {
        StringBuilder message = new StringBuilder();
        message.append("Recording started with parameters below:\n");
        message.append(recording.toString());
        return message;
    }

    private int createIdentifier(RecordingList recordingList) {
        List<Recording> list = recordingList.getRecordings();
        int maxIdentifier;
        if (!list.isEmpty()) {
            maxIdentifier = list.get(list.size() - 1).getIdentifier();
            for (int i = 0; i < list.size() - 1; i++) {
                if (maxIdentifier < list.get(i + 1).getIdentifier()) {
                    maxIdentifier = list.get(i + 1).getIdentifier();
                }
            }
            return maxIdentifier + 1;
        } else {
            return 0;
        }
    }
}
