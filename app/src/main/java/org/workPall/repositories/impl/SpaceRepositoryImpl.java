package org.workPall.repositories.impl;

import org.workPall.config.DatabaseConnection;
import org.workPall.entities.Space;
import org.workPall.repositories.interfaces.SpaceRepositoryInter;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SpaceRepositoryImpl implements SpaceRepositoryInter {
    private final Connection connection;

    public SpaceRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Space createSpace(Space space) {
        String query = "INSERT INTO spaces (name, description, location, available) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, space.getName());
            pstmt.setString(2, space.getDescription());
            pstmt.setString(3, space.getLocation());
            pstmt.setBoolean(4, space.getAvailable());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        space.setId(id);
                        return space;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
