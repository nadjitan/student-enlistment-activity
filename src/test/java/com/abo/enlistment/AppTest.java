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

    @Test
    public void enlist_2_sections_no_sched_overlaps() {
        // Given one student and 2 sections no overlapping schedules
        Student student = new Student(1);
        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830));
        Section sec2 = new Section("B", new Schedule(Days.TF, Period.H1000));

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
        Student student = new Student(1, Collections.emptyList());
        Section sec1 = new Section("A", new Schedule(Days.MTH, Period.H0830));
        Section sec2 = new Section("B", new Schedule(Days.MTH, Period.H0830));

        // When the student enlists in both sections
        student.enlist(sec1);

        // Then an exception should be thrown in the second enlistment
        assertThrows(ScheduleConflictException.class, () -> student.enlist(sec2));
    }
}
