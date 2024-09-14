package org.workPall.repositories.interfaces;

import org.workPall.entities.Reservation;

import java.sql.SQLException;
import java.sql.Timestamp;

public interface ReservationRepositoryInter {
    void reserveSpace(Reservation reservation) throws SQLException;
}
