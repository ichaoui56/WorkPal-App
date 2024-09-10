package org.workPall.GUI;

import org.workPall.repositories.impl.UserRepositoryImpl;
import org.workPall.services.impl.UserServiceImpl;
import org.workPall.services.interfaces.UserServiceInter;

import java.sql.SQLException;

public class MainGUI {

    private final AuthGUI authGUI;

    public MainGUI() throws SQLException {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        UserServiceInter userServiceInter = new UserServiceImpl(userRepository);
        MemberGUI memberGUI = new MemberGUI(userServiceInter);
        authGUI = new AuthGUI(userServiceInter, memberGUI);
    }

    public void displayAuth() {
        authGUI.login();
    }

}
