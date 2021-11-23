package data;

import java.time.LocalDateTime;

public class Record {

    private final int identifier;
    private boolean isActive;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Activity activity;
    private String description;
    private String notes;

    public Record(int identifier, LocalDateTime startTime, Activity activity) {
        this.identifier = identifier;
        isActive = true;
        this.startTime = startTime;
        activity.addRecord(this);
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

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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
