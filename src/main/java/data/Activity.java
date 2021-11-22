package data;

public class Activity {

    private final int identifier;
    private Category category;
    private String name;
    private String description;
    private String notes;

    public Activity(int identifier, Category category, String name) {
        this.identifier = identifier;
        this.category = category;
        category.addActivity(this);
        this.name = name;
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
        this.category = category;
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
