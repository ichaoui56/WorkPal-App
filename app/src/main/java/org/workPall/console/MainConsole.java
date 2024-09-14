package org.workPall.console;

import org.workPall.repositories.impl.SpaceRepositoryImpl;
import org.workPall.repositories.impl.UserRepositoryImpl;
import org.workPall.services.impl.SpaceServiceImpl;
import org.workPall.services.impl.UserServiceImpl;
import org.workPall.services.interfaces.SpaceServiceInter;
import org.workPall.services.interfaces.UserServiceInter;

import java.sql.SQLException;

public class MainConsole {

    private final AuthConsole authConsole;

    // Constructor initializes all repositories, services, and consoles for the application
    public MainConsole() throws SQLException {
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        SpaceRepositoryImpl spaceRepository = new SpaceRepositoryImpl();
        UserServiceInter userServiceInter = new UserServiceImpl(userRepository);
        SpaceServiceInter spaceServiceInter = new SpaceServiceImpl(spaceRepository);
        MemberConsole memberConsole = new MemberConsole(userServiceInter);
        AdminConsole adminConsole = new AdminConsole();
        ManagerConsole managerConsole = new ManagerConsole(userServiceInter, spaceServiceInter);

        authConsole = new AuthConsole(userServiceInter, memberConsole, managerConsole, adminConsole);
    }

    public void main(String[] args) {
        authConsole.start();
    }
}
