package org.workPall.entities;

import java.util.HashMap;
import java.util.Map;

public class Service {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Space space;
    private Map<Long, Reservation> reservations = new HashMap<>();

    public Service(Long id, String name, String description, double price, Space space) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
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
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", space=" + space +
                '}';
    }
}

