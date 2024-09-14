package org.workPall.entities;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Reservation {
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int userId;
    private int spaceId;
    private Map<Long, Service> services = new HashMap<>();
    private Payment payment;


    public Reservation() {};
    public Reservation(int id, LocalDateTime startTime, LocalDateTime endTime, int userId, int spaceId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
        this.spaceId = spaceId;
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
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSpaceId() {
        return spaceId;
    }
    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
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
                ", userId=" + userId +
                ", spaceId=" + spaceId +
                '}';
    }
}
