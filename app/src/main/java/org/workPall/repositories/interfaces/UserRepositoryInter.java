package org.workPall.repositories.interfaces;

import org.workPall.entities.User;
import java.util.List;

public interface UserRepositoryInter {
    List<User> findAll();
    User findByEmail(String email);
    Boolean getUserByEmailAndPassword(String email, String password);
    User createUser(User user);
    void update(User user);
    void delete(int id);
}
