package recording;

import data.Recording;

import java.time.LocalDateTime;

public class StartRecording {

    public Recording startRecording(int identifier, String description, LocalDateTime startTime) {
        return new Recording(identifier, description, startTime);
    }
}
