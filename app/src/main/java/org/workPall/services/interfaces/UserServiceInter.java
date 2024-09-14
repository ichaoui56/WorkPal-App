package org.workPall.services.interfaces;

import org.workPall.entities.User;
import java.util.List;

public interface UserServiceInter {
    List<User> getAllUsers();
    void logOut();
    User getUserData();
    User createUser(User user);
    Boolean login(String email, String password);
    void updateUser(User user);
    void deleteUser(int id);
    String getUserEmailById(int id) throws Exception;
}
