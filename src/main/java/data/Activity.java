package data;

import java.util.ArrayList;
import java.util.List;

public class Activity {

    private final int identifier;
    private Category category;
    private String name;
    private String description;
    private String notes;
    private List<Record> records = new ArrayList<>();

    public Activity(int identifier, Category category, String name) {
        this.identifier = identifier;
        this.category = category;
        this.name = name;
        category.addActivity(this);
    }

    public void addRecord(Record record) {
        this.records.add(record);
    }

    public void removeRecord(Record record) {
        records.remove(record);
    }

    public List<Record> getRecords() {
        return records;
    }

    @Override
    public String toString() {
        StringBuilder activityString = new StringBuilder();
        activityString.append("Id: ");
        activityString.append(identifier);
        activityString.append("\nKategória: ");
        activityString.append(category.getName());
        activityString.append("\nNév: ");
        activityString.append(name);
        activityString.append("\nLeírás: ");
        activityString.append(description);
        activityString.append("\nMegjegyzés: ");
        activityString.append(notes);
        return activityString.toString();
    }

    public int getIdentifier() {
        return identifier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category.removeActivity(this);
        this.category = category;
        category.addActivity(this);
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
}
