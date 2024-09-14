package org.workPall.repositories.impl;

import org.workPall.config.DatabaseConnection;
import org.workPall.entities.Space;
import org.workPall.repositories.interfaces.SpaceRepositoryInter;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SpaceRepositoryImpl implements SpaceRepositoryInter {
    private final Connection connection;
    private Map<Integer, Space> spaces;

    public SpaceRepositoryImpl() throws SQLException {
        this.spaces = new HashMap<>();
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

    @Override
    public Boolean modifySpace(Space space) {
        String query = "UPDATE spaces SET name=?, description=?, location=?, available=? WHERE id=?";
        try(PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1,space.getName());
            pstmt.setString(2, space.getDescription());
            pstmt.setString(3, space.getLocation());
            pstmt.setBoolean(4, space.getAvailable());
            pstmt.setInt(5, space.getId());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Integer> findSpaceIdByName(String name) {
        String query = "SELECT id FROM spaces WHERE name = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    return Optional.of(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Map<Integer, Space> searchSpaces(String name, String location, Boolean isAvailable) {
        Map<Integer, Space> spaces = new HashMap<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM spaces WHERE 1=1");

        if (name != null && !name.trim().isEmpty()) {
            queryBuilder.append(" AND name LIKE ?");
        }
        if (location != null && !location.trim().isEmpty()) {
            queryBuilder.append(" AND location LIKE ?");
        }
        if (isAvailable != null) {
            queryBuilder.append(" AND available = ?");
        }

        String query = queryBuilder.toString();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            int paramIndex = 1;
            if (name != null && !name.trim().isEmpty()) {
                pstmt.setString(paramIndex++, "%" + name + "%");
            }
            if (location != null && !location.trim().isEmpty()) {
                pstmt.setString(paramIndex++, "%" + location + "%");
            }
            if (isAvailable != null) {
                pstmt.setBoolean(paramIndex++, isAvailable);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String spaceName = rs.getString("name");
                    String description = rs.getString("description");
                    String spaceLocation = rs.getString("location");
                    boolean available = rs.getBoolean("available");

                    Space space = new Space(id, spaceName, description, spaceLocation, available);
                    spaces.put(id, space);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spaces;
    }


    @Override
    public Map<Integer, Space> getSpacesByName(String name) {
        Map<Integer, Space> spaces = new HashMap<>();
        String query = "SELECT * FROM spaces WHERE name = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String spaceName = rs.getString("name");
                    String description = rs.getString("description");
                    String location = rs.getString("location");
                    boolean available = rs.getBoolean("available");

                    Space space = new Space(id, spaceName, description, location, available);
                    spaces.put(id, space);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spaces;
    }


    @Override
    public boolean deleteSpaceById(int id) {
        String query = "DELETE FROM spaces WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<Integer, Space> displayAllSpaces() {
        String query = "SELECT * FROM spaces";
        Map<Integer, Space> spacesMap = new HashMap<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String location = rs.getString("location");
                boolean available = rs.getBoolean("available");

                Space space = new Space(id, name, description, location, available);
                spacesMap.put(id, space);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return spacesMap;
    }

}
