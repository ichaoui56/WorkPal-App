package org.workPall.repositories.interfaces;

import org.workPall.entities.Reservation;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public interface ReservationRepositoryInter {
    void reserveSpace(Reservation reservation) throws SQLException;
    void cancelReservation(int reservationId) throws SQLException;
    Map<Integer,Reservation> getAllReservations() throws SQLException;
}
