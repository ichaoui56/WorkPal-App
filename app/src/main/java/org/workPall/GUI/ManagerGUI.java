package org.workPall.GUI;

import org.workPall.entities.Space;
import org.workPall.entities.User;
import org.workPall.services.interfaces.SpaceServiceInter;
import org.workPall.services.interfaces.UserServiceInter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ManagerGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel sidebarPanel;
    private JPanel profilePanel;
    private UserServiceInter userService;
    private static SpaceServiceInter spaceService;

    public ManagerGUI(UserServiceInter userService, SpaceServiceInter spaceService) {
        this.userService = userService;
        this.spaceService = spaceService;
    }

    public void showManagerUI() {
        setTitle("Manager Management System");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(createHomePanel(), "Home");
        mainPanel.add(createProfilePanel(), "Profile");
        mainPanel.add(createSpacePanel(), "Spaces");
        mainPanel.add(createSubscriptionPanel(), "Subscription");
        mainPanel.add(createEventsPanel(), "Events");
        mainPanel.add(createNotificationsPanel(), "Notifications");

        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(0, 1));
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));

        JButton homeButton = new JButton("Home");
        JButton spaceButton = new JButton("Spaces");
        JButton subscriptionButton = new JButton("Subscription");
        JButton eventsButton = new JButton("Events");
        JButton notificationsButton = new JButton("Notifications");

        homeButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        spaceButton.addActionListener(e -> cardLayout.show(mainPanel, "Spaces"));
        subscriptionButton.addActionListener(e -> cardLayout.show(mainPanel, "Subscription"));
        eventsButton.addActionListener(e -> cardLayout.show(mainPanel, "Events"));
        notificationsButton.addActionListener(e -> cardLayout.show(mainPanel, "Notifications"));

        sidebarPanel.add(homeButton);
        sidebarPanel.add(spaceButton);
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
        panel.add(new JLabel("Welcome to the Manager Management System"));
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


    private static JPanel createSpacePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        JButton addButton = new JButton("Add a space");
        addButton.addActionListener(e -> {
            createAddSpaceForm();
        });

        panel.add(addButton);
        panel.add(new JButton("Modify Space"));
        panel.add(new JButton("Delete a space"));
        panel.add(new JButton("Display all spaces"));

        return panel;
    }


    private static int nextSpaceId = 1; // Auto-increment ID

    private static void createAddSpaceForm() {
        JFrame addSpaceFrame = new JFrame("Add Space");
        addSpaceFrame.setSize(400, 300);
        addSpaceFrame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // 10px padding between components
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // 20px padding around the panel

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();
        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField();
        JLabel availableLabel = new JLabel("Available (true/false):");
        JTextField availableField = new JTextField();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String description = descriptionField.getText();
            String location = locationField.getText();
            Boolean available = Boolean.parseBoolean(availableField.getText());

            Space newSpace = new Space(0, name, description, location, available);
            Space createSpace = spaceService.addSpace(newSpace);
            if (createSpace != null && createSpace.getId() > 0) {
                JOptionPane.showMessageDialog(addSpaceFrame, "Space added successfully: " + createSpace.toString(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(addSpaceFrame, "Failed to add space.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            addSpaceFrame.dispose();
        });

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(descriptionLabel);
        formPanel.add(descriptionField);
        formPanel.add(locationLabel);
        formPanel.add(locationField);
        formPanel.add(availableLabel);
        formPanel.add(availableField);
        formPanel.add(new JLabel()); // Empty cell
        formPanel.add(submitButton);

        addSpaceFrame.add(formPanel, BorderLayout.CENTER);

        addSpaceFrame.setLocationRelativeTo(null);

        addSpaceFrame.setVisible(true);
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
