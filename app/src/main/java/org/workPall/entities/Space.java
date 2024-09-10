package org.workPall.entities;

import java.util.HashMap;
import java.util.Map;

public class Space {
    private int id;
    private String name;
    private String description;
    private String location;
    private Boolean available;
    private Map<Long, Reservation> reservations = new HashMap<>();
    private Map<Long, Feedback> feedbacks = new HashMap<>();
    private Map<Long, Favorite> favorites = new HashMap<>();
    private Map<Long, Event> events = new HashMap<>();
    private Map<Long, Service> services = new HashMap<>();

    public Space(int id, String name, String description, String location, Boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.available = available;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Map<Long, Reservation> getReservations() {
        return reservations;
    }
    public void setReservations(Map<Long, Reservation> reservations) {
        this.reservations = reservations;
    }

    public Map<Long, Feedback> getFeedbacks() {
        return feedbacks;
    }
    public void setFeedbacks(Map<Long, Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Map<Long, Favorite> getFavorites() {
        return favorites;
    }
    public void setFavorites(Map<Long, Favorite> favorites) {
        this.favorites = favorites;
    }

    public Map<Long, Event> getEvents() {
        return events;
    }
    public void setEvents(Map<Long, Event> events) {
        this.events = events;
    }

    public Map<Long, Service> getServices() {
        return services;
    }
    public void setServices(Map<Long, Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Space{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
