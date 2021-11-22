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
        this.name = name;
    }

    @Override
    public String toString() {
        return "Id.: " + identifier + "\n" +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", notes='" + notes + '\'' +
                '}';
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
