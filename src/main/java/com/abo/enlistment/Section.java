package com.abo.enlistment;

import static org.apache.commons.lang3.Validate.*;

import static org.apache.commons.lang3.StringUtils.*;

class Section {
    private final String sectionId;
    private final Schedule schedule;
    private final Room room;

    Section(String sectionId, Schedule schedule, Room room) {
        notBlank(sectionId);
        notNull(schedule);
        notNull(room);
        isTrue(isAlphanumeric(sectionId), "sectionId must be alphanumeric, was: " + sectionId);

        this.sectionId = sectionId;
        this.schedule = schedule;
        this.room = room;
    }

    void checkScheduleConflict(Section other) {
        if (this.schedule.equals(other.schedule)) {
            throw new ScheduleConflictException(
                    "This section " + this + " and other section " + other + " have same schedule at " + schedule);
        }
    }

    void confirmPeriod() {
        this.schedule.checkPeriodConstraints();
    }

    void confirmStudentInRoom() {
        this.room.addStudentToRoom();
    }

    Schedule getSchedule() {
        return this.schedule;
    }

    Room getRoom() {
        return this.room;
    }

    @Override
    public String toString() {
        return this.sectionId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sectionId == null) ? 0 : sectionId.hashCode());
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
        Section other = (Section) obj;
        if (sectionId != other.sectionId)
            return false;
        return true;
    }
}
