package org.workPall.entities;

import java.time.LocalDateTime;

public class Notification {
    private int id;
    private String message;
    private LocalDateTime timestamp;
    private User user;

    public Notification(int id, String message, LocalDateTime timestamp, User user) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
        this.user = user;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                '}';
    }
}
