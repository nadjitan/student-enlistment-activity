package com.abo.enlistment;

import static org.apache.commons.lang3.Validate.*;

class Room {
    private final String roomName;
    private final int capacity;
    private int numberOfStudents = 0;

    Room(String roomName, int capacity) {
        notBlank(roomName);
        notNull(capacity);
        this.roomName = roomName;
        this.capacity = capacity;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public int getCapacity() {
        return this.capacity;
    }

    void addStudentToRoom() {
        if (this.numberOfStudents < this.capacity) {
            this.numberOfStudents++;
        } else {
            throw new IllegalArgumentException("Max capacity of room reached. Student cannot be added.");
        }
    }

    @Override
    public String toString() {
        return this.roomName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((roomName == null) ? 0 : roomName.hashCode());
        result = prime * result + capacity;
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
        Room other = (Room) obj;
        if (roomName != other.roomName)
            return false;
        if (capacity != other.capacity)
            return false;
        return true;
    }
}
