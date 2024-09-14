package org.workPall.repositories.impl;

import org.workPall.config.DatabaseConnection;
import org.workPall.entities.Reservation;
import org.workPall.entities.Space;
import org.workPall.repositories.interfaces.ReservationRepositoryInter;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public void cancelReservation(int reservationId) throws SQLException {
        String query = "DELETE FROM reservations WHERE id=?";

        try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reservationId);
            int state= stmt.executeUpdate();
            if (state >0) {
                System.out.println("Reservation cancelled successfully.");
            } else {
                System.out.println("Reservation not found.");
            }
        }
    }

    public Map<Integer, Reservation> getAllReservations() throws SQLException {
        Map<Integer,Reservation> reservations = new HashMap<>();
        String query = "SELECT u.id AS user_id, u.name AS user_name, " +
                "r.id AS reservation_id, r.start_time, r.end_time, r.space_id, " +
                "s.name AS space_name, s.location AS space_location " +
                "FROM users u " +
                "LEFT JOIN reservations r ON u.id = r.user_id " +
                "LEFT JOIN spaces s ON r.space_id = s.id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(rs.getInt("id"));
                reservation.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                reservation.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
                reservation.setUserId(rs.getInt("user_id"));
                reservation.setSpaceId(rs.getInt("space_id"));

                Space space = new Space();
                space.setId(rs.getInt("space_id"));
                space.setName(rs.getString("name"));
                space.setLocation(rs.getString("location"));

                reservation.setSpace(space);

                reservations.put(reservation.getId(), reservation);
            }
        }

        return reservations;
    }

}
