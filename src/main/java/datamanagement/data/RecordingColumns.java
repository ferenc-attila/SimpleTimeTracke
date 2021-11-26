package datamanagement.data;

public enum RecordingColumns {

    ID("identifier"), DESCRIPTION("description"), IS_ACTIVE("in_progress"), START_TIME("start_time"), END_TIME("end_time"), NOTES("notes");

    private final String columnName;

    RecordingColumns(String columnName) {
        this.columnName = columnName;
    }

    public static String getHeader() {
        StringBuilder csvHeader = new StringBuilder();
        for (int i = 0; i < RecordingColumns.values().length; i++) {
            if (i < RecordingColumns.values().length - 1) {
                csvHeader.append(RecordingColumns.values()[i].getColumnName());
                csvHeader.append(";");
            } else {
                csvHeader.append(RecordingColumns.values()[i].getColumnName());
            }
        }
        return csvHeader.toString();
    }

    public String getColumnName() {
        return columnName;
    }
}
