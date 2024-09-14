package org.workPall.console;

import org.workPall.repositories.impl.ReservationRepositoryImpl;
import org.workPall.repositories.impl.SpaceRepositoryImpl;
import org.workPall.repositories.impl.UserRepositoryImpl;
import org.workPall.repositories.interfaces.ReservationRepositoryInter;
import org.workPall.repositories.interfaces.SpaceRepositoryInter;
import org.workPall.repositories.interfaces.UserRepositoryInter;
import org.workPall.services.impl.ReservationServiceImpl;
import org.workPall.services.impl.SpaceServiceImpl;
import org.workPall.services.impl.UserServiceImpl;
import org.workPall.services.interfaces.ReservationServiceInter;
import org.workPall.services.interfaces.SpaceServiceInter;
import org.workPall.services.interfaces.UserServiceInter;

import java.sql.SQLException;

public class MainConsole {

    private final AuthConsole authConsole;

    public MainConsole() throws SQLException {
        UserRepositoryInter userRepository = new UserRepositoryImpl();
        SpaceRepositoryInter spaceRepository = new SpaceRepositoryImpl();
        ReservationRepositoryInter reservationRepository = new ReservationRepositoryImpl();
        UserServiceInter userService = new UserServiceImpl(userRepository);
        SpaceServiceInter spaceService = new SpaceServiceImpl(spaceRepository);
        ReservationServiceInter reservationService = new ReservationServiceImpl(reservationRepository);
        MemberConsole memberConsole = new MemberConsole(userService, spaceService, reservationService);
        AdminConsole adminConsole = new AdminConsole();
        ManagerConsole managerConsole = new ManagerConsole(userService, spaceService);

        authConsole = new AuthConsole(userService, memberConsole, managerConsole, adminConsole);
    }

    public void main(String[] args) throws SQLException {
        authConsole.start();
    }
}
