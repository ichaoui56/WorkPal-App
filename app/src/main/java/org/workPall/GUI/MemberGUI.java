package org.workPall.GUI;

import org.workPall.entities.User;
import org.workPall.services.interfaces.UserServiceInter;

import javax.swing.*;
import java.awt.*;

public class MemberGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel sidebarPanel;
    private JPanel profilePanel;
    private UserServiceInter userService;
    public MemberGUI(UserServiceInter userService) {
        this.userService = userService;
    }

    public void showMemberUI() {
        setTitle("Member Management System");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(createHomePanel(), "Home");
        mainPanel.add(createProfilePanel(), "Profile");
        mainPanel.add(createReservationPanel(), "Reservations");
        mainPanel.add(createSubscriptionPanel(), "Subscription");
        mainPanel.add(createEventsPanel(), "Events");
        mainPanel.add(createNotificationsPanel(), "Notifications");

        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(0, 1));
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));

        JButton homeButton = new JButton("Home");
        JButton reservationButton = new JButton("Reservations");
        JButton subscriptionButton = new JButton("Subscription");
        JButton eventsButton = new JButton("Events");
        JButton notificationsButton = new JButton("Notifications");

        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        reservationButton.addActionListener(e -> cardLayout.show(mainPanel, "Reservations"));
        subscriptionButton.addActionListener(e -> cardLayout.show(mainPanel, "Subscription"));
        eventsButton.addActionListener(e -> cardLayout.show(mainPanel, "Events"));
        notificationsButton.addActionListener(e -> cardLayout.show(mainPanel, "Notifications"));

        sidebarPanel.add(homeButton);
        sidebarPanel.add(reservationButton);
        sidebarPanel.add(subscriptionButton);
        sidebarPanel.add(eventsButton);
        sidebarPanel.add(notificationsButton);

        profilePanel = new JPanel(new BorderLayout());
        profilePanel.setPreferredSize(new Dimension(getWidth() - 200, 50));

        JButton profileButton = new JButton("Profile");
        profileButton.addActionListener(e -> cardLayout.show(mainPanel, "Profile"));

        profilePanel.add(profileButton, BorderLayout.EAST);


        add(sidebarPanel, BorderLayout.WEST);
        add(profilePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        pack();
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome to the Member Management System"));
        return panel;
    }

    private JPanel createProfilePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        User user = userService.getUserData();

        JLabel nameLabel = new JLabel("Name: " + user.getFirstName() + " " + user.getLastName());
        JLabel emailLabel = new JLabel("Email: " + user.getEmail());
        JLabel phoneNumberLabel = new JLabel("Phone Number: " + user.getPhoneNumber());
        JLabel addressLabel = new JLabel("Address: " + user.getAddress());
        JLabel roleLabel = new JLabel("Role: " + user.getRole());

        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        emailLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        phoneNumberLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addressLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        roleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton modifyProfileButton = new JButton("Modify Profile");
        JButton logoutButton = new JButton("Log Out");

        modifyProfileButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Modify Profile clicked");
        });

        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged out successfully!");
            userService.logOut();
            setVisible(false);
            ((AuthGUI) getParent()).login();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(modifyProfileButton);
        buttonPanel.add(logoutButton);
        panel.add(nameLabel);
        panel.add(emailLabel);
        panel.add(phoneNumberLabel);
        panel.add(addressLabel);
        panel.add(roleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(modifyProfileButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(logoutButton);
        panel.add(buttonPanel);


        return panel;
    }


    private JPanel createReservationPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(new JButton("Search Spaces by Date"));
        panel.add(new JButton("Reserve Space"));
        panel.add(new JButton("Consult Spaces"));
        panel.add(new JButton("Save Favorite Spaces"));
        panel.add(new JButton("View Reservation History"));
        panel.add(new JButton("Cancel Reservations"));
        return panel;
    }

    private JPanel createSubscriptionPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.add(new JButton("Choose Subscription"));
        panel.add(new JButton("Renew Subscription"));
        panel.add(new JButton("Cancel Subscription"));
        panel.add(new JButton("View Subscription History"));
        return panel;
    }

    private JPanel createEventsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(new JButton("Sign Up for Events"));
        panel.add(new JButton("Consult Calendar"));
        return panel;
    }

    private JPanel createNotificationsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(new JButton("Receive Reservation Reminders"));
        panel.add(new JButton("Receive Space Change Notifications"));
        return panel;
    }

}
