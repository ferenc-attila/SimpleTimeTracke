package datamanagement.data.activity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityColumnsTest {

    @Test
    void getActivityHeaderTest() {
        ActivityColumns columns = new ActivityColumns();
        String expectedString = "identifier;name;description;notes";
        assertEquals(expectedString, columns.getActivityHeader());
    }
}