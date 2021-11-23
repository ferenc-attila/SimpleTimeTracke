package data;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {

    Category sport = new Category(1, "Sport");
    Activity jogging = new Activity(15, sport, "Jogging");
    Record morningJogging = new Record(0, LocalDateTime.of(2021, 05, 15, 6, 21), jogging);
    Record walking = new Record(1, LocalDateTime.of(2021, 10, 30, 14, 22), jogging);

    @Test
    void testActiveToStringTest() {
        morningJogging.setDescription("Futópályán");
        morningJogging.setNotes("minden nap kell");
        String morningJoggingString = """
                Id: 0
                Kategória: Sport
                Aktivitás: Jogging
                Kezdés: 2021-05-15T06:21
                Befejezés: Folyamatban
                Folyamatban: igen
                Leírás: Futópályán
                Megjegyzés: minden nap kell""";
        assertEquals(morningJoggingString, morningJogging.toString());
    }

    @Test
    void testInactiveToStringTest() {
        walking.setEndTime(LocalDateTime.of(2021, 10, 30, 15, 42));
        walking.setActive(false);
        walking.setDescription("A hegyi úton");
        walking.setNotes("kirándulás");
        String walkingString = """
                Id: 1
                Kategória: Sport
                Aktivitás: Jogging
                Kezdés: 2021-10-30T14:22
                Befejezés: 2021-10-30T15:42
                Folyamatban: nem
                Leírás: A hegyi úton
                Megjegyzés: kirándulás""";
        assertEquals(walkingString, walking.toString());
    }

    @Test
    void setActiveTest() {
        assertTrue(morningJogging.isActive());
        morningJogging.setActive(false);
        assertFalse(morningJogging.isActive());
    }

    @Test
    void setStartTimeTest() {
        morningJogging.setStartTime(LocalDateTime.of(2021, 6, 17, 9, 14));
        assertEquals("2021-06-17T09:14", morningJogging.getStartTime().toString());
    }

    @Test
    void setEndTimeTest() {
        morningJogging.setEndTime(LocalDateTime.of(2021, 10, 30, 16, 20));
        assertEquals("2021-10-30T16:20", morningJogging.getEndTime().toString());
    }

    @Test
    void resetEndTimeTest() {
        morningJogging.setEndTime(null);
        assertNull(morningJogging.getEndTime());
    }

    @Test
    void setActivityTest() {
        Activity hiking = new Activity(2, sport, "Túrázás");
        walking.setActivity(hiking);
        assertEquals("Túrázás", walking.getActivity().getName());
    }

    @Test
    void setDescription() {
        walking.setDescription("A hegyi úton");
        assertEquals("A hegyi úton", walking.getDescription());
    }

    @Test
    void setNotes() {
        walking.setNotes("kirándulás");
        assertEquals("kirándulás", walking.getNotes());
    }
}