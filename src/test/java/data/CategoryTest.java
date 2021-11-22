package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void removeActivityTest() {
        work.removeActivity(administration);
        assertEquals(1, work.getActivities().size());
        assertEquals(0, work.getActivities().indexOf(commute));
    }

    @Test
    void toStringTest() {
        work.setDescription("Munkahelyi tevékenységek");
        work.setNotes("Főállású munkahely");
        String workString = """
                Id: 1
                Kategória: Work
                Leírás: Munkahelyi tevékenységek
                Megjegyzés: Főállású munkahely
                Aktivitások: administration, Commute to work""";
        assertEquals(workString, work.toString());
    }

    @Test
    void setNameTest() {
        work.setName("Full time work");
        assertEquals("Full time work", work.getName());
    }

    @Test
    void setDescriptionTest() {
        work.setDescription("Munkahelyi feladatok");
        assertEquals("Munkahelyi feladatok", work.getDescription());
    }

    @Test
    void setNotesTest() {
        work.setNotes("Jelenlegi főállás, valamint a két mellékállás");
        assertEquals("Jelenlegi főállás, valamint a két mellékállás", work.getNotes());
    }
}