package service.recording;

import datahandling.data.Recording;

import java.time.LocalDateTime;

public class StopRecording {

    public void stopRecording(Recording recording) {
        recording.setEndTime(LocalDateTime.now());
        recording.setActive(false);
    }
}
