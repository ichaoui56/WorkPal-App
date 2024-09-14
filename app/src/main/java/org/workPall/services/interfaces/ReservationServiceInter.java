package org.workPall.services.interfaces;

import org.workPall.entities.Reservation;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

public interface ReservationServiceInter {
    void cancelReservation(int reservationId, int userId) throws SQLException;
    boolean reserveSpace( int spaceId,LocalDateTime startTime, LocalDateTime endTime, int userId);
    Map<Integer, Reservation> getAllReservations() throws SQLException;
}
