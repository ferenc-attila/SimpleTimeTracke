# ToDo List

Write missing unit tests (for menu classes).  
Refactoring  
Exception handling.  
Update and delete data.  
Constructor overloading in data classes (solved partially).  
Introduce Activity and Categories classes (activity introduced).  
Data validations:
 - Verify that the IDs are unique (solved).
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

Export/import to /from user defined folder.