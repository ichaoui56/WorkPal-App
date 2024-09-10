package org.workPall.entities;

import org.workPall.enums.Role;

import java.util.HashMap;
import java.util.Map;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private Role role;
    private Map<Long, Reservation> reservations = new HashMap<>();
    private Map<Long, Feedback> feedbacks = new HashMap<>();
    private Map<Long, Favorite> favorites = new HashMap<>();
    private Map<Long, Notification> notifications = new HashMap<>();
    private Map<Long, Subscription> subscriptions = new HashMap<>();

    public User( String firstName, String lastName, String email, String phoneNumber, String address, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Map<Long, Notification> getNotifications() {
        return notifications;
    }
    public void setNotifications(Map<Long, Notification> notifications) {
        this.notifications = notifications;
    }

    public Map<Long, Subscription> getSubscriptions() {
        return subscriptions;
    }
    public void setSubscriptions(Map<Long, Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }


    public String toString() {
        return "User [id=" + id + ", " +
                "firstName=" + firstName + ", " +
                "lastName=" + lastName + ", " +
                "email=" + email + ", " +
                "phoneNumber=" + phoneNumber + ", " +
                "address=" + address + ", " +
                "password=" + password + ", " +
                "role=" + role + "]";
    }
}
