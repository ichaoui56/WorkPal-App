package org.workPall.services.impl;

import org.workPall.entities.Reservation;
import org.workPall.repositories.interfaces.ReservationRepositoryInter;
import org.workPall.services.interfaces.ReservationServiceInter;

import java.sql.SQLException;

import java.time.LocalDateTime;

import java.util.Map;

public class ReservationServiceImpl implements ReservationServiceInter {
    private ReservationRepositoryInter reservationRepository;

    public ReservationServiceImpl(ReservationRepositoryInter reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public boolean reserveSpace(int spaceId, LocalDateTime startTime, LocalDateTime endTime, int userId) {
        Reservation reservation = new Reservation();
        reservation.setSpaceId(spaceId);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setUserId(userId);

        try {
            reservationRepository.reserveSpace(reservation);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public  void cancelReservation(int reservationId, int userId) throws SQLException {
        try {
            reservationRepository.cancelReservation(reservationId);

        } catch (SQLException e) {
            System.out.println("Error cancelling reservation: " + e.getMessage());
        }
    }

    public Map<Integer,Reservation> getAllReservations() throws SQLException {
        return reservationRepository.getAllReservations();
    }
}