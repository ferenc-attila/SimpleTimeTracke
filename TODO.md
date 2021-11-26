# ToDo List

Verify that the IDs and names are unique in the activities and categories.
Check that the recorded activities do not overlap in time.
Check the correct relationship between isActive and endTime values.

Read isActive from csv file:

    private boolean parseIsActive(String[] cells) {
        if ("yes".equals(cells[2])) {
            return true;
        } else if ("no".equals(cells[2])) {
            return false;
        } else {
            throw new IllegalArgumentException("Invalid data in csv!");
        }
    }