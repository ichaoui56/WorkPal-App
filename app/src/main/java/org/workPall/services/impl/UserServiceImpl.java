package org.workPall.services.impl;

import org.workPall.entities.User;
import org.workPall.repositories.interfaces.UserRepositoryInter;
import org.workPall.services.interfaces.UserServiceInter;
import org.workPall.session.SessionManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserServiceInter {
    private final UserRepositoryInter userRepository;

    public UserServiceImpl(UserRepositoryInter userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Map<Integer,User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void logOut(){
        SessionManager.logout();
    }

    @Override
    public User getUserData() {
        return SessionManager.getLoggedInUser();
    }

    @Override
    public User createUser(User user) {
        userRepository.createUser(user);
        return user;
    }

    @Override
    public Boolean login(String email, String password) {
        if (userRepository.getUserByEmailAndPassword(email, password)) {
            User loggedInUser = userRepository.findByEmail(email);
            SessionManager.setLoggedInUser(loggedInUser);
            return true;
        }
        return false;
    }

    @Override
    public void updateUser(User user) {
        userRepository.update(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(id);
    }

    @Override
    public String getUserEmailById(int userId) throws SQLException {
        return userRepository.findEmailByUserId(userId);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }
}
