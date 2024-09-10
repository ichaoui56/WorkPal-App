package org.workPall.entities;

public class Favorite {
    private Long id;
    private User user;
    private Space space;

    public Favorite(Long id, User user, Space space) {
        this.id = id;
        this.user = user;
        this.space = space;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
