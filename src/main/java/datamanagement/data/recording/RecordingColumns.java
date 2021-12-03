package datamanagement.data.recording;

public class RecordingColumns {

    private final String idHeader = "identifier";
    private final String descriptionHeader = "description";
    private final String isActiveHeader = "in_progress";
    private final String startTimeHeader = "start_time";
    private final String endTimeHeader = "end_time";
    private final String notesHeader = "notes";

    public String getHeader() {
        StringBuilder csvHeader = new StringBuilder();
        csvHeader.append(idHeader).append(";");
        csvHeader.append(descriptionHeader).append(";");
        csvHeader.append(isActiveHeader).append(";");
        csvHeader.append(startTimeHeader).append(";");
        csvHeader.append(endTimeHeader).append(";");
        csvHeader.append(notesHeader);
        return csvHeader.toString();
    }
}
