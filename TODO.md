# ToDo List

Write missing unit tests.  
Exception handling.  
Solve CSV header read/write problems.

Validations:
 - Verify that the IDs are unique.
 - Check that the recorded activities do not overlap in time.
 - Check the correct relationship between isActive and endTime values.

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