package data;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private final int identifier;
    private String name;
    private String description;
    private String notes;
    private List<Activity> activities = new ArrayList<>();

    public Category(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    @Override
    public String toString() {
        StringBuilder categoryString = new StringBuilder();
        categoryString.append("Id: ");
        categoryString.append(identifier);
        categoryString.append("\nKategória: ");
        categoryString.append(name);
        categoryString.append("\nLeírás: ");
        categoryString.append(description);
        categoryString.append("\nMegjegyzés: ");
        categoryString.append(notes);
        categoryString.append("\nAktivitások: ");
        categoryString.append(listActivities());
        return categoryString.toString();
    }

    private String listActivities() {
        StringBuilder activityList = new StringBuilder();
        boolean first = true;
        for (Activity activity : this.activities) {
            if (first) {
                activityList.append(activity.getName());
                first = false;
            } else {
                activityList.append(", ");
                activityList.append(activity.getName());
            }
        }
        return activityList.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getIdentifier() {
        return identifier;
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
