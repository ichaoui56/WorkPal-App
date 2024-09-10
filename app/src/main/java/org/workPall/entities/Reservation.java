package org.workPall.entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Reservation {
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private User user;
    private Space space;
    private Map<Long, Service> services = new HashMap<>();
    private Payment payment;

    public Reservation(int id, LocalDateTime startTime, LocalDateTime endTime, User user, Space space) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.space = space;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime)
    { this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) { this.user = user; }

    public Space getSpace() {
        return space;
    }
    public void setSpace(Space space) {
        this.space = space;
    }

    public Map<Long, Service> getServices() {
        return services;
    }
    public void setServices(Map<Long, Service> services) {
        this.services = services;
    }

    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", user=" + user +
                ", space=" + space +
                '}';
    }
}
