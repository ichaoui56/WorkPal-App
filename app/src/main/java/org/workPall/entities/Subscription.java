package org.workPall.entities;

import java.time.LocalDate;

public class Subscription {
    private int id;
    private String title;
    private int price;
    private Boolean status;
    private LocalDate startDate;
    private LocalDate endDate;
    private User user;
    private Space space;

    public Subscription(int id, LocalDate startDate, LocalDate endDate, User user, Space space, String title, int price, Boolean status) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.space = space;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Space getSpace() {
        return space;
    }
    public void setSpace(Space space) {
        this.space = space;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", user=" + user +
                ", space=" + space +
                '}';
    }
}

