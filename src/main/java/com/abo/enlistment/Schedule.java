package com.abo.enlistment;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.apache.commons.lang3.Validate.*;

class Schedule {
    private final String[] days = { "MTH", "TF", "WS" };
    private final String minPeriod = "0830"; // 08:30 AM
    private final String maxPeriod = "1700"; // 05:00 PM
    private final String day;
    private final String startPeriod;
    private final String endPeriod;
    private final int increment = 30;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
    DateTimeFormatter debugFormat = DateTimeFormatter.ofPattern("HHmm");

    Schedule(String day, String startPeriod, String endPeriod) {
        notNull(day);
        notBlank(startPeriod);
        notBlank(endPeriod);
        this.day = day;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    public String getDay() {
        return this.day;
    }


    public String getPeriod() {
        return LocalTime.parse(this.startPeriod, formatter) + " - " + LocalTime.parse(this.endPeriod, formatter);
    }

    void checkPeriodConstraints() {
        LocalTime parsedStartPeriod = LocalTime.parse(this.startPeriod, formatter);
        LocalTime parsedEndPeriod = LocalTime.parse(this.endPeriod, formatter);
        LocalTime parsedMinPeriod = LocalTime.parse(this.minPeriod, formatter);
        LocalTime parsedMaxPeriod = LocalTime.parse(this.maxPeriod, formatter);

        // checks if duration is of 30-min increments
        if (!((MINUTES.between(parsedStartPeriod, parsedEndPeriod) % increment) == 0))
            throw new ScheduleConflictException("This period " + parsedStartPeriod + " - " + parsedEndPeriod
                    + " should be in " + increment + " minute increments");

        // within the hours of 8:30am - 5:00pm
        if (parsedStartPeriod.isBefore(parsedMinPeriod) || parsedEndPeriod.isAfter(parsedMaxPeriod))
            throw new ScheduleConflictException("This period " + parsedStartPeriod + " - " + parsedEndPeriod
                    + " does not fit in between the minimum and maximum hours");

        // periods should be at the top or bottom of each hour
        if (!(parsedStartPeriod.getMinute() == 30 || parsedStartPeriod.getMinute() == 00
                || parsedEndPeriod.getMinute() == 30 || parsedEndPeriod.getMinute() == 00))
            throw new ScheduleConflictException("This period " + parsedStartPeriod + " - " + parsedEndPeriod
                    + " should be at the top or bottom of each hour");

        // start period should not be equal to the end period or end period should not
        // be set before start period
        if (parsedStartPeriod.compareTo(parsedEndPeriod) >= 0)
            throw new ScheduleConflictException("This period " + parsedStartPeriod + " - " + parsedEndPeriod
                    + " should not have the same start and end or end period time should not be set before start period time");

    }

    @Override
    public String toString() {
        return this.days + " " + LocalTime.parse(this.startPeriod, this.debugFormat) + " - "
                + LocalTime.parse(this.endPeriod, this.debugFormat);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((days == null) ? 0 : days.hashCode());
        result = prime * result + ((startPeriod == null) ? 0 : startPeriod.hashCode());
        result = prime * result + ((endPeriod == null) ? 0 : endPeriod.hashCode());
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
        Schedule other = (Schedule) obj;
        if (days != other.days)
            return false;
        if (startPeriod != other.startPeriod)
            return false;
        if (endPeriod != other.endPeriod)
            return false;
        return true;
    }
}
