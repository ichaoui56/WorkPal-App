package org.workPall.services.impl;

import org.workPall.entities.User;
import org.workPall.repositories.interfaces.UserRepositoryInter;
import org.workPall.services.interfaces.UserServiceInter;
import org.workPall.session.SessionManager;

import java.util.List;

public class UserServiceImpl implements UserServiceInter {
    private final UserRepositoryInter userRepository;

    public UserServiceImpl(UserRepositoryInter userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
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
}
