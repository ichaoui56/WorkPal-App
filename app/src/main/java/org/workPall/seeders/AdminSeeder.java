package org.workPall.seeders;

import org.workPall.config.DatabaseConnection;
import org.workPall.entities.User;
import org.workPall.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminSeeder {

    private Connection connection;

    public AdminSeeder() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public void seedAdmin() throws SQLException {
        String query = "INSERT INTO users (firstname, lastname, email, phonenumber, address, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            String firstName = "Admin";
            String lastName = "User";
            String email = "admin@example.com";
            String phoneNumber = "123-456-7890";
            String address = "123 Admin St, Admin City";
            String password = "securepassword";
            Role role = Role.ADMIN;

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, address);
            stmt.setString(6, password);
            stmt.setString(7, role.name());

            stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        try {
            AdminSeeder seeder = new AdminSeeder();
            seeder.seedAdmin();
            System.out.println("Admin user seeded successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
