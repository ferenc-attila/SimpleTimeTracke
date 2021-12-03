package datamanagement.data.recording;

import java.util.ArrayList;
import java.util.List;

public class RecordingList {

    private List<Recording> recordings = new ArrayList<>();

    public void addRecording(Recording recording) {
        recordings.add(recording);
    }

    public List<Recording> getRecordings() {
        return recordings;
    }
}
