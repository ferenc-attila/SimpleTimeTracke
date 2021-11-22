package data;

import org.junit.jupiter.api.Test;

import javax.xml.stream.FactoryConfigurationError;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category work = new Category(1, "Work");
    Activity administration = new Activity(1, work, "administration");
    Activity commute = new Activity(2, work, "Commute to work");

    @Test
    void createTest() {
        Category family = new Category(2, "Family");
        assertEquals(2, family.getIdentifier());
        assertEquals("Family", family.getName());
    }

    @Test
    void addActivityTest() {
        work.addActivity(administration);
        work.addActivity(commute);

        assertEquals(4, work.getActivities().size());
        assertEquals("administration", work.getActivities().get(2).getName());
    }

    @Test
    void toStringTest() {
    }

    @Test
    void setNameTest() {
    }

    @Test
    void setDescriptionTest() {
    }

    @Test
    void setNotesTest() {
    }
}