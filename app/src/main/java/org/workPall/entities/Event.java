package org.workPall.entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Event {
    private Long id;
    private String name;
    private LocalDateTime dateTime;
    private Space space;
    private Map<Long, Reservation> reservations = new HashMap<>();

    public Event(Long id, String name, LocalDateTime dateTime, Space space) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.space = space;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime;
    }

    public Space getSpace() {
        return space;
    }
    public void setSpace(Space space) {
        this.space = space;
    }

    public Map<Long, Reservation> getReservations() {
        return reservations;
    }
    public void setReservations(Map<Long, Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", space=" + space +
                '}';
    }
}
