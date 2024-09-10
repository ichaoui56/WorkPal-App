package org.workPall.entities;

public class Feedback {
    private int id;
    private String comment;
    private int rating;
    private User user;
    private Space space;

    public Feedback(int id, String comment, int rating, User user, Space space) {
        this.id = id;
        this.comment = comment;
        this.rating = rating;
        this.user = user;
        this.space = space;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Space getSpace() { return space; }
    public void setSpace(Space space) {
        this.space = space;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", user=" + user +
                ", space=" + space +
                '}';
    }
}
