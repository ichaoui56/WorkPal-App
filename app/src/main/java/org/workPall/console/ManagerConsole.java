package org.workPall.console;

import org.workPall.entities.Event;
import org.workPall.entities.Space;
import org.workPall.entities.User;
import org.workPall.services.interfaces.SpaceServiceInter;
import org.workPall.services.interfaces.UserServiceInter;

import java.util.Map;
import java.util.Scanner;

public class ManagerConsole {
    private UserServiceInter userService;
    private static SpaceServiceInter spaceService;
    private Scanner scanner;

    public ManagerConsole(UserServiceInter userService, SpaceServiceInter spaceService) {
        this.userService = userService;
        this.spaceService = spaceService;
        this.scanner = new Scanner(System.in);
    }

    public void showManagerUI() {
        boolean running = true;
        while (running) {
            System.out.println("===== Manager Management System =====");
            System.out.println("1. Home");
            System.out.println("2. Profile");
            System.out.println("3. Spaces");
            System.out.println("4. Subscription");
            System.out.println("5. Events");
            System.out.println("6. Notifications");
            System.out.println("7. Log Out");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showHome();
                    break;
                case 2:
                    showProfile();
                    break;
                case 3:
                    manageSpaces();
                    break;
                case 4:
                    manageSubscription();
                    break;
                case 5:
                    manageEvents();
                    break;
                case 6:
                    manageNotifications();
                    break;
                case 7:
                    System.out.println("Logged out successfully!");
                    userService.logOut();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");

            }
        }
    }

    private void showHome() {
        System.out.println("Welcome to the Manager Management System");
    }

    private void showProfile() {
        User user = userService.getUserData();
        System.out.println("===== Profile =====");
        System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone Number: " + user.getPhoneNumber());
        System.out.println("Address: " + user.getAddress());
        System.out.println("Role: " + user.getRole());
        System.out.println("1. Modify Profile");
        System.out.println("2. Back");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            modifyProfile();
        }
    }

    private void modifyProfile() {
        System.out.println("Modify Profile feature is under development.");
    }

    private void manageSpaces() {
        System.out.println("===== Spaces =====");
        System.out.println("1. Add a Space");
        System.out.println("2. Display All Spaces");
        System.out.println("3. Delete a Space");
        System.out.print("Select an option: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                createAddSpaceForm();
                break;
            case 2:
                displayAllSpaces();
                break;
            case 3:
                createDeleteSpaceForm();
                break;
            default:
                System.out.println("Invalid option, please try again.");
                manageSpaces();
        }
    }

    private void createAddSpaceForm() {
        System.out.println("===== Add Space =====");
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Description: ");
        String description = scanner.next();
        System.out.print("Location: ");
        String location = scanner.next();
        System.out.print("Available (true/false): ");
        boolean available = scanner.nextBoolean();

        Space newSpace = new Space(0, name, description, location, available);
        Space createdSpace = spaceService.addSpace(newSpace);
        if (createdSpace != null && createdSpace.getId() > 0) {
            System.out.println("Space added successfully: " + createdSpace.toString());
        } else {
            System.out.println("Failed to add space.");
        }
    }

    private void displayAllSpaces() {
        Map<Integer, Space> spaces = spaceService.getAllSpaces();
        System.out.println("===== All Spaces =====");
        for (Space space : spaces.values()) {
            System.out.println(space.toString());
        }
    }

    private void createDeleteSpaceForm() {
        System.out.println("===== Delete Space =====");
        System.out.print("Enter Space Name: ");
        String spaceName = scanner.next();
        boolean deleted = spaceService.deleteSpaceByName(spaceName);
        if (deleted) {
            System.out.println("Space '" + spaceName + "' deleted successfully.");
        } else {
            System.out.println("Space '" + spaceName + "' not found.");
        }
    }

    private void manageSubscription() {
        System.out.println("===== Subscription =====");
        System.out.println("Subscription management features are under development.");
    }

    private void manageEvents() {
        System.out.println("===== Events =====");
        System.out.println("Event management features are under development.");
    }

    private void manageNotifications() {
        System.out.println("===== Notifications =====");
        System.out.println("Notification management features are under development.");
    }
}
