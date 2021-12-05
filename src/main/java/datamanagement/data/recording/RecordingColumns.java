package datamanagement.data.recording;

public class RecordingColumns {

    private final String idHeader = "identifier";
    private final String descriptionHeader = "description";
    private final String activityHeader = "activity";
    private final String isActiveHeader = "in_progress";
    private final String startTimeHeader = "start_time";
    private final String endTimeHeader = "end_time";
    private final String notesHeader = "notes";

    public String getRecordingHeader() {
        StringBuilder recordingHeader = new StringBuilder();
        recordingHeader.append(idHeader).append(";");
        recordingHeader.append(descriptionHeader).append(";");
        recordingHeader.append(activityHeader).append(";");
        recordingHeader.append(isActiveHeader).append(";");
        recordingHeader.append(startTimeHeader).append(";");
        recordingHeader.append(endTimeHeader).append(";");
        recordingHeader.append(notesHeader);
        return recordingHeader.toString();
    }
}
