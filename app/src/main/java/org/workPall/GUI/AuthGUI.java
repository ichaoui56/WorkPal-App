package org.workPall.GUI;

import org.workPall.entities.User;
import org.workPall.enums.Role;
import org.workPall.services.interfaces.UserServiceInter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class AuthGUI extends JFrame {

    private final UserServiceInter userServiceInter;
    private final MemberGUI memberGUI;
    private final ManagerGUI managerGUI;
    private final AdminGUI adminGUI;

    public AuthGUI(UserServiceInter userServiceInter, MemberGUI memberGUI, ManagerGUI managerGUI, AdminGUI adminGUI) {
        this.userServiceInter = userServiceInter;
        this.memberGUI = memberGUI;
        this.managerGUI = managerGUI;
        this.adminGUI = adminGUI;
    }

    public void login() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        JButton registerButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(registerButton, gbc);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(AuthGUI.this, "Both email and password must be provided.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (userServiceInter.login(email, password)) {

                String role = userServiceInter.getUserData().getRole().toString();

                switch (role) {
                    case "ADMIN":
                        adminGUI.showAdminUI();
                        break;
                    case "MANAGER":
                        managerGUI.showManagerUI();
                        break;
                    case "MEMBER":
                        memberGUI.showMemberUI();
                        break;
                    default:
                        JOptionPane.showMessageDialog(AuthGUI.this, "Unknown role: " + role, "Role Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }

                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(AuthGUI.this, "Invalid email or password", "Authentication Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            getContentPane().removeAll();
            register();
            revalidate();
            repaint();
        });

        setVisible(true);
    }

    public void register() {
        setTitle("Register");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel firstNameLabel = new JLabel("First Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(firstNameLabel, gbc);

        JTextField firstNameField = new JTextField(20);
        gbc.gridx = 1;
        add(firstNameField, gbc);

        JLabel lastNameLabel = new JLabel("Last Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lastNameLabel, gbc);

        JTextField lastNameField = new JTextField(20);
        gbc.gridx = 1;
        add(lastNameField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        JLabel phoneLabel = new JLabel("Phone Number:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(phoneLabel, gbc);

        JTextField phoneField = new JTextField(20);
        gbc.gridx = 1;
        add(phoneField, gbc);

        JLabel addressLabel = new JLabel("Address:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(addressLabel, gbc);

        JTextField addressField = new JTextField(20);
        gbc.gridx = 1;
        add(addressField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        JLabel roleLabel = new JLabel("Role:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(roleLabel, gbc);

        JComboBox<Role> roleComboBox = new JComboBox<>(Role.getNonAdminRoles());
        gbc.gridx = 1;
        add(roleComboBox, gbc);

        JButton back = new JButton("Back");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(back, gbc);

        JButton registerButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        add(registerButton, gbc);

        registerButton.addActionListener(e -> {

            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneField.getText();
            String address = addressField.getText();
            String password = new String(passwordField.getPassword());
            Role role = (Role) roleComboBox.getSelectedItem();

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || password.isEmpty() || role == null) {
                JOptionPane.showMessageDialog(AuthGUI.this, "All fields must be filled out.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            User user = new User(0, firstName, lastName, email, phoneNumber, address, password, role);
            User createdUser = userServiceInter.createUser(user);

            if (createdUser != null) {

                JOptionPane.showMessageDialog(AuthGUI.this, "User Registered Successfully: " + createdUser.getFirstName());

                getContentPane().removeAll();
                login();
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(AuthGUI.this, "Registration failed: Email already in use.");
            }
            getContentPane().removeAll();
            login();
            revalidate();
            repaint();
        });

        back.addActionListener(e -> {
            getContentPane().removeAll();
            login();
            revalidate();
            repaint();
        });

        setVisible(true);
    }

}
