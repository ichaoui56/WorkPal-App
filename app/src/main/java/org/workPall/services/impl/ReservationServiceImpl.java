package org.workPall.services.impl;

import org.workPall.entities.Reservation;
import org.workPall.repositories.interfaces.ReservationRepositoryInter;
import org.workPall.services.interfaces.ReservationServiceInter;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
}