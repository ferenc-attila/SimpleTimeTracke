package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityTest {

    Category sport = new Category(1, "Sport");
    Activity jogging = new Activity(15, sport, "Jogging");

    @Test
    void createTest() {
        assertEquals(15, jogging.getIdentifier());
        assertEquals("Jogging", jogging.getName());
        assertEquals(1, jogging.getCategory().getIdentifier());
        assertEquals("Sport", jogging.getCategory().getName());
        assertEquals(1, sport.getActivities().size());
    }

    @Test
    void testToString() {
        jogging.setDescription("Futópályán");
        jogging.setNotes("minden nap kell");
        String joggingString = """
                Id: 15
                Kategória: Sport
                Név: Jogging
                Leírás: Futópályán
                Megjegyzés: minden nap kell""";
        assertEquals(joggingString, jogging.toString());
    }

    @Test
    void setCategory() {
        Category healthCare = new Category(2, "Health Care");
        jogging.setCategory(healthCare);
        assertEquals(0, sport.getActivities().size());
        assertEquals(1, healthCare.getActivities().size());
        assertEquals("Health Care", jogging.getCategory().getName());
    }

    @Test
    void setName() {
        jogging.setName("running");
        assertEquals("running", jogging.getName());
    }

    @Test
    void setDescription() {
        jogging.setDescription("Margit-szigeti futópályán");
        assertEquals("Margit-szigeti futópályán", jogging.getDescription());
    }

    @Test
    void setNotes() {
        jogging.setNotes("mindennapos");
        assertEquals("mindennapos", jogging.getNotes());
    }
}