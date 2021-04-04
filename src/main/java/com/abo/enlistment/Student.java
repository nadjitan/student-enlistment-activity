package com.abo.enlistment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

class Student {
    private final int studentNumber;
    private final Collection<Section> sections = new HashSet<>();

    Student(int studentNumber) {
        this(studentNumber, Collections.emptyList());
    }

    Student(int studentNumber, Collection<Section> sections) {
        if (studentNumber < 0) {
            throw new IllegalArgumentException("studentNumber should be non-negative");
        }
        if (sections == null) {
            throw new NullPointerException("sections was null");
        }

        this.studentNumber = studentNumber;
        this.sections.addAll(sections);
    }

    boolean enlist(Section newSection) {
        if (newSection == null) {
            throw new NullPointerException("section was null");
        }

        sections.forEach(currSection -> currSection.checkScheduleConflict(newSection));
        newSection.confirmPeriod();
        newSection.comfirmStudentInRoom();
        sections.add(newSection);

        return true;
    }

    Collection<Section> getSections() {
        return new ArrayList<>(sections);
    }

    @Override
    public String toString() {
        return "Student #" + studentNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + studentNumber;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (studentNumber != other.studentNumber)
            return false;
        return true;
    }
}
