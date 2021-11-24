package recording;

import data.Category;
import data.Activity;
import data.Record;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class StartRecordingTest {

    Category sport = new Category(0, "Sport");
    Activity jogging = new Activity(0, sport, "Futás");
    Activity hiking = new Activity(1, sport, "Túrázás");

    @Test
    void startRecordTest() {
        Record morningJogging = new Record(0, LocalDateTime.of(2021, 10, 30, 06, 51), jogging);
        morningJogging.startRecord();
        assertTrue(morningJogging.isActive());
        assertNull(morningJogging.getEndTime());
        assertEquals(1, jogging.getRecords().size());
        assertEquals(1, sport.getActivities().size());
        assertEquals(0, sport.getActivities().get(0).getRecords().get(0).getIdentifier());
    }

    @Test
    void addRecordTest() {
        Record summerHiking = new Record(0, LocalDateTime.of(2021, 6, 15, 8, 1), hiking);
        summerHiking.addRecord(LocalDateTime.of(2021,6,15,14,23));
        assertFalse(summerHiking.isActive());
        assertEquals("2021-06-15T14:23", summerHiking.getEndTime().toString());
        assertEquals(1,sport.getActivities().size());
        assertEquals("Túrázás", sport.getActivities().get(0).getName());
        assertEquals(0,sport.getActivities().get(0).getRecords().indexOf(summerHiking));
    }

    @Test
    void addDescription() {

    }

    @Test
    void addNotes () {

    }
}