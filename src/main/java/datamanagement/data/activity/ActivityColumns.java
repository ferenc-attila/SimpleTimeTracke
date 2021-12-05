package datamanagement.data.activity;

public class ActivityColumns {

    private final String idHeader = "identifier";
    private final String nameHeader = "name";
    private final String descriptionHeader = "description";
    private final String notesHeader = "notes";

    public String getActivityHeader() {
        StringBuilder activityHeader = new StringBuilder();
        activityHeader.append(idHeader).append(";");
        activityHeader.append(nameHeader).append(";");
        activityHeader.append(descriptionHeader).append(";");
        activityHeader.append(notesHeader);
        return activityHeader.toString();
    }
}
