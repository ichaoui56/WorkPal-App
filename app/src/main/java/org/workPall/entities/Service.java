package org.workPall.entities;

import java.util.HashMap;
import java.util.Map;

public class Service {
    private int id;
    private String name;
    private String description;
    private int quantity;
    private int price;
    private Space space;
    private Map<Long, Reservation> reservations = new HashMap<>();

    public Service(int id, String name, String description, int quantity, int price, Space space) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.space = space;
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

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
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

