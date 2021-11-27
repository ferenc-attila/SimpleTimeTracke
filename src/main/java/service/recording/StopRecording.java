package service.recording;

import datamanagement.data.Recording;

import java.time.LocalDateTime;

public class StopRecording {

    public void stopRecording(Recording recording) {
        recording.finishRecording(LocalDateTime.now());
    }

    public StringBuilder printStopMessage(Recording recording) {
        StringBuilder message = new StringBuilder();
        message.append("Recording ended with parameters below:\n");
        message.append(recording.toString());
        return message;
    }
}
