package com.abo.enlistment;

import static java.time.temporal.ChronoUnit.MINUTES;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static org.apache.commons.lang3.Validate.*;

class Schedule {
    private final Days days;
    private final Period period;
    private final String minPeriod = "0830";    // 08:30 AM
    private final String maxPeriod = "1700";    // 05:00 PM
    private final int increment = 30;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");

    Schedule(Days days, Period period) {
        notNull(days);
        notNull(period);
        this.days = days;
        this.period = period;
    }

    void checkPeriodConstraints(String startPeriod, String endPeriod) {
        LocalTime parsedStartPeriod = LocalTime.parse(startPeriod, formatter);
        LocalTime parsedEndPeriod = LocalTime.parse(endPeriod, formatter);
        LocalTime parsedMinPeriod = LocalTime.parse(minPeriod, formatter);
        LocalTime parsedMaxPeriod = LocalTime.parse(maxPeriod, formatter);

        // checks if duration is of 30-min increments
        if (!((MINUTES.between(parsedStartPeriod,parsedEndPeriod) % increment) == 0))
            throw new ScheduleConflictException(
                    "This period " + startPeriod + "-" + endPeriod + " should be in" + increment + "minute increments");

        // within the hours of 8:30am - 5:00pm
        if (!parsedStartPeriod.isAfter(parsedMinPeriod) && !parsedEndPeriod.isBefore(parsedMaxPeriod))
            throw new ScheduleConflictException(
                    "This period " + startPeriod + "-" + endPeriod + " does not fit in between the minimum and maximum hours");

        // periods should be at the top or bottom of each hour
        if (!(parsedStartPeriod.getMinute() == 30 || parsedStartPeriod.getMinute() == 00 
            || parsedEndPeriod.getMinute() == 30 || parsedEndPeriod.getMinute() == 00))
            throw new ScheduleConflictException(
                    "This period " + startPeriod + "-" + endPeriod + " should be at the top or bottom of each hour");
    }

    @Override
    public String toString() {
        return days + " " + period;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((days == null) ? 0 : days.hashCode());
        result = prime * result + ((days == null) ? 0 : period.hashCode());
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
        if (period != other.period)
            return false;
        return true;
    }
}

enum Days {
    MTH, TF, WS
}

enum Period {
    H0830, H1000, H1130, H1430, H1600
}
