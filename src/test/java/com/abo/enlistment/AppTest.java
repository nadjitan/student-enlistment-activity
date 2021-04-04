package com.abo.enlistment;

import java.util.Collections;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

/**
 * Unit test
 */
public class AppTest {
    // #region SCHEDULE TESTS
    @Test
    public void enlist_2_sections_no_sched_overlaps() {
        // Given one student and 2 sections no overlapping schedules
        Student student = new Student(1);
        Section sec1 = new Section("A", new Schedule(Days.MTH, "0830", "1000"), new Room("Room1", 30));
        Section sec2 = new Section("B", new Schedule(Days.TF, "0900", "1200"), new Room("Room2", 30));

        // When the student enlists in both sections
        student.enlist(sec1);
        student.enlist(sec2);

        // Then both sections can be found in the student, but no other
        Collection<Section> sections = student.getSections();
        assertAll(() -> assertTrue(sections.containsAll(List.of(sec1, sec2))), () -> assertEquals(2, sections.size()));
    }

    @Test
    public void enlist_2_sections_same_schedule() {
        // Given one student and 2 sections w/ same schedule
        Student student = new Student(1);
        Section sec1 = new Section("A", new Schedule(Days.MTH, "0830", "1000"), new Room("Room1", 30));
        Section sec2 = new Section("B", new Schedule(Days.MTH, "0830", "1000"), new Room("Room2", 30));

        // When the student enlists in both sections
        student.enlist(sec1);
        student.enlist(sec1);

        // Then an exception should be thrown in the second enlistment
        assertThrows(ScheduleConflictException.class, () -> student.enlist(sec2));
    }
    // #endregion SCHEDULE TESTS

    // #region PERIOD TESTS
    // Period tests should fail given they have invalid inputs
    @Test
    public void thirty_increments_only() {
        // Given the period of section 1 is 8:30am - 10:25am
        Student student = new Student(1);
        Section sec1 = new Section("A", new Schedule(Days.MTH, "0830", "1025"), new Room("Room1", 1));

        // When student enlists in section 1
        student.enlist(sec1);

        // Then throw an exception if the capacity exceeds
        assertThrows(ScheduleConflictException.class, () -> student.enlist(sec1));
    }

    @Test
    public void period_not_in_min_max() {
        // Given the period of section 1 is 4:30pm - 5:30pm
        Student student = new Student(1);
        Section sec1 = new Section("A", new Schedule(Days.MTH, "1630", "1730"), new Room("Room1", 1));

        // When student enlists in section 1
        student.enlist(sec1);

        // Then throw an exception period is not between 8:30am - 5:00pm
        assertThrows(ScheduleConflictException.class, () -> student.enlist(sec1));
    }

    @Test
    public void period_top_or_bottom_of_each_hour() {
        // Given the period of section 1 is 5:15pm - 6:45pm
        Student student = new Student(1);
        Section sec1 = new Section("A", new Schedule(Days.MTH, "1515", "1645"), new Room("Room1", 1));

        // When student enlists in section 1
        student.enlist(sec1);

        // Then throw an exception should be at the top or bottom of each hour
        assertThrows(ScheduleConflictException.class, () -> student.enlist(sec1));
    }

    @Test
    public void period_same_start_and_end() {
        // Given the period of section 1 is 10:00am - 10:00am
        Student student = new Student(1);
        Section sec1 = new Section("A", new Schedule(Days.MTH, "1000", "1000"), new Room("Room1", 1));

        // When student enlists in section 1
        student.enlist(sec1);

        // Then throw an exception should be at the top or bottom of each hour
        assertThrows(ScheduleConflictException.class, () -> student.enlist(sec1));
    }

    @Test
    public void end_period_set_before_start_period() {
        // Given the period of section 1 is 13:00pm - 12:00pm
        Student student = new Student(1);
        Section sec1 = new Section("A", new Schedule(Days.MTH, "1300", "1200"), new Room("Room1", 1));

        // When student enlists in section 1
        student.enlist(sec1);

        // Then throw an exception should be at the top or bottom of each hour
        assertThrows(ScheduleConflictException.class, () -> student.enlist(sec1));
    }
    // #endregion PERIOD TESTS

    @Test
    public void room_capacity_exceeded() {
        // Given 2 students is added in section 1 but it only has 1 capacity
        Student student = new Student(1);
        Student student2 = new Student(2);
        Section sec1 = new Section("A", new Schedule(Days.MTH, "0830", "1000"), new Room("Room1", 1));

        // When 2 students join 1 section but the section only has 1 capacity
        student.enlist(sec1);
        student2.enlist(sec1);

        // Then throw an exception if the capacity exceeds
        assertThrows(IllegalArgumentException.class, () -> student2.enlist(sec1));
    }
}
