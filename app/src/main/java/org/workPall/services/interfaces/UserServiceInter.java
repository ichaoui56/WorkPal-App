package org.workPall.services.interfaces;

import org.workPall.entities.User;
import java.util.List;
import java.util.Map;

public interface UserServiceInter {
    Map<Integer,User> getAllUsers();
    void logOut();
    User getUserData();
    User createUser(User user);
    Boolean login(String email, String password);
    void updateUser(User user);
    void deleteUser(int id);
    String getUserEmailById(int id) throws Exception;
    User getUserById(int id);
}
