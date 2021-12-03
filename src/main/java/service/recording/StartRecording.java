package service.recording;

import datamanagement.data.recording.Recording;
import datamanagement.data.recording.RecordingList;
import datamanagement.queries.FindRecordings;

import java.time.LocalDateTime;
import java.util.List;

public class StartRecording {

    FindRecordings find = new FindRecordings();

    public void startRecording(String description, RecordingList recordingList) {
        if (find.numberOfActiveRecording(recordingList.getRecordings()) > 0) {
            throw new IllegalStateException("Active recording running! Try to stop it before start another!");
        }
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
