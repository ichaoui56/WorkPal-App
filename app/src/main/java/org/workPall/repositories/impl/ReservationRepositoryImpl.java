package org.workPall.repositories.impl;

import org.workPall.config.DatabaseConnection;
import org.workPall.entities.Reservation;
import org.workPall.repositories.interfaces.ReservationRepositoryInter;

import java.sql.*;
import java.time.LocalDate;

public class ReservationRepositoryImpl implements ReservationRepositoryInter {
    private Connection connection;

    public ReservationRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void reserveSpace(Reservation reservation) throws SQLException {
        String query = "INSERT INTO reservations (start_time, end_time, user_id, space_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTimestamp(1, Timestamp.valueOf(reservation.getStartTime()));
            stmt.setTimestamp(2, Timestamp.valueOf(reservation.getEndTime()));
            stmt.setInt(3, reservation.getUserId());
            stmt.setInt(4, reservation.getSpaceId());

            stmt.executeUpdate();
        }
    }

}
