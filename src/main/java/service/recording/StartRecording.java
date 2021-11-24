package service.recording;

import datahandling.data.Recording;
import datahandling.data.RecordingList;

import java.time.LocalDateTime;

public class StartRecording {

    public void startRecording(String description, RecordingList recordingList) {
        int identifier = createIdentifier(recordingList);
        Recording recording = new Recording(identifier, description, LocalDateTime.now());
        recordingList.addRecording(recording);
        System.out.println(printMessage(recording));
    }

    private StringBuilder printMessage(Recording recording) {
        StringBuilder message = new StringBuilder();
        message.append("Recording started with parameters below:\n");
        message.append(recording.toString());
        return message;
    }

    private int createIdentifier(RecordingList recordingList) {
        int maxIdentifier = 0;
        if (!recordingList.getRecordings().isEmpty()) {
            for (int i = 0; i < recordingList.getRecordings().size(); i++) {
                maxIdentifier = recordingList.getRecordings().get(i).getIdentifier();
                if (maxIdentifier < recordingList.getRecordings().get(i + 1).getIdentifier()) {
                    maxIdentifier = recordingList.getRecordings().get(i + 1).getIdentifier();
                }
            }
            return maxIdentifier + 1;
        } else {
            return 0;
        }
    }
}
