package org.workPall.repositories.impl;

import org.workPall.config.DatabaseConnection;
import org.workPall.entities.User;
import org.workPall.enums.Role;
import org.workPall.repositories.interfaces.UserRepositoryInter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryInter {
    private final Connection connection;

    public UserRepositoryImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                String password = rs.getString("password");
                String roleStr = rs.getString("role");

                Role role = Role.valueOf(roleStr);

                User user = new User(firstName, lastName, email, phoneNumber, address, password, role);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        User user = null;
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phoneNumber = rs.getString("phone_number");
                String address = rs.getString("address");
                String password = rs.getString("password");
                String roleStr = rs.getString("role");

                Role role = Role.valueOf(roleStr);

                user = new User(firstName, lastName, email, phoneNumber, address, password, role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public User createUser(User user) {
        String query = "INSERT INTO users (first_name, last_name, email, phone_number, address, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getPassword());
            pstmt.setString(7, user.getRole().name());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Boolean getUserByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        boolean userExists = false;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            userExists = rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExists;
    }

    @Override
    public void update(User user) {
        String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phone_number = ?, address = ?, role = ? WHERE email = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPhoneNumber());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getRole().name());
            pstmt.setString(7, user.getEmail());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
