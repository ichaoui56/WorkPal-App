package org.workPall.entities;

public class Favorite {
    private int id;
    private User user;
    private Space space;

    public Favorite(int id, User user, Space space) {
        this.id = id;
        this.user = user;
        this.space = space;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Space getSpace() {
        return space;
    }
    public void setSpace(Space space) {
        this.space = space;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", user=" + user +
                ", space=" + space +
                '}';
    }
}
