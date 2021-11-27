package datamanagement.data;

import java.time.LocalDateTime;

public class Recording {

    private final int identifier;
    private String description;
    private boolean isActive;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String notes;

    public Recording(int identifier, String description, LocalDateTime startTime) {
        this.identifier = identifier;
        this.description = description;
        this.isActive = true;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        StringBuilder recordString = new StringBuilder();
        recordString.append("Id.: ");
        recordString.append(identifier);
        recordString.append("\nDescription: ");
        recordString.append(description);
        recordString.append("\nStart time: ");
        recordString.append(timeValueToString(this.startTime));
        recordString.append("\nEnd time: ");
        recordString.append(createEndTimeString());
        recordString.append("\nIn progress: ");
        recordString.append(getActiveString());
        recordString.append("\nNotes: ");
        recordString.append(notes);
        return recordString.toString();
    }

    public String toCsvRow() {
        StringBuilder csvRow = new StringBuilder();
        String separator = ";";
        csvRow.append(identifier);
        addSeparator(csvRow, separator);
        csvRow.append(description);
        addSeparator(csvRow, separator);
        csvRow.append(getActiveString());
        addSeparator(csvRow, separator);
        csvRow.append(timeValueToString(this.startTime));
        addSeparator(csvRow, separator);
        csvRow.append(createEndTimeString());
        addSeparator(csvRow, separator);
        csvRow.append(notes);

        return csvRow.toString();
    }

    public String printRecordingToMenu() {
        return identifier + ": " + description + ", In progress: " + getActiveString();
    }

    public void finishRecording(LocalDateTime endTime) {
        this.endTime = endTime;
        this.isActive = false;
    }

    private String timeValueToString(LocalDateTime dateTime) {
        return dateTime.toString().replace('T', ' ').substring(0, 16);
    }

    private void addSeparator(StringBuilder stringBuilder, String separator) {
        stringBuilder.append(separator);
    }

    private String createEndTimeString() {
        if (this.endTime == null) {
            return "";
        } else {
            return timeValueToString(this.endTime);
        }
    }

    private String getActiveString() {
        if (isActive()) {
            return "yes";
        } else {
            return "no";
        }
    }

    public int getIdentifier() {
        return identifier;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
