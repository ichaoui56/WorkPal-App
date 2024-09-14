package org.workPall.repositories.interfaces;

import org.workPall.entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserRepositoryInter {
    Map<Integer,User> findAll();
    User findByEmail(String email);
    Boolean getUserByEmailAndPassword(String email, String password);
    User createUser(User user);
    void update(User user);
    void delete(int id);
    String findEmailByUserId(int userId) throws SQLException;
    User getUserById(int id);

}
