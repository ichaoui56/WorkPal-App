package org.workPall.console;

import org.workPall.entities.Space;
import org.workPall.entities.User;
import org.workPall.services.interfaces.ReservationServiceInter;
import org.workPall.services.interfaces.SpaceServiceInter;
import org.workPall.services.interfaces.UserServiceInter;
import org.workPall.session.SessionManager;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;

public class MemberConsole {
    private UserServiceInter userService;
    private SpaceServiceInter spaceService;
    private ReservationServiceInter reservationService;
    private Scanner scanner;

    public MemberConsole(UserServiceInter userService, SpaceServiceInter spaceService, ReservationServiceInter reservationService) {
        this.userService = userService;
        this.spaceService = spaceService;
        this.reservationService = reservationService;
        this.scanner = new Scanner(System.in);
    }

    public void showMemberUI() {
        boolean running = true;
        while (running) {
            System.out.println("===== Member Management System =====");
            System.out.println("1. Home");
            System.out.println("2. Profile");
            System.out.println("3. Reservations");
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
                    manageReservations();
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
        System.out.println("Welcome to the Member Management System");
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

    private void manageReservations() {
        boolean running = true;
        while (running) {
            System.out.println("===== Reservations =====");
            System.out.println("1. Search Spaces");
            System.out.println("2. Reserve a Space");
//            System.out.println("3. View Reservation Details");
//            System.out.println("4. Save Favorite Spaces");
//            System.out.println("5. View Events Calendar");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchSpaces();
                    break;
                case 2:
                    reserveSpace();
                    break;

                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }


    private void searchSpaces() {
        System.out.println("===== Search Spaces =====");
        System.out.print("Enter space name (leave blank if not searching by name): ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter space location (leave blank if not searching by location): ");
        String location = scanner.nextLine().trim();

        System.out.print("Is the space available (true/false, leave blank if not searching by availability): ");
        String availableInput = scanner.nextLine().trim();
        Boolean isAvailable = null;
        if (!availableInput.isEmpty()) {
            if (availableInput.equalsIgnoreCase("true")) {
                isAvailable = true;
            } else if (availableInput.equalsIgnoreCase("false")) {
                isAvailable = false;
            } else {
                System.out.println("Invalid input for availability. Searching will ignore this criterion.");
            }
        }

        Map<Integer, Space> spaces = spaceService.searchSpaces(name, location, isAvailable);

        if (spaces.isEmpty()) {
            System.out.println("No spaces found matching the criteria.");
        } else {
            System.out.println("Spaces found:");
            for (Space space : spaces.values()) {
                System.out.println(space.toString());
            }
        }
    }
    private void reserveSpace() {
        System.out.println("===== Reserve a Space =====");
        System.out.print("Enter the space ID to reserve: ");
        int spaceId = scanner.nextInt();
        scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.print("Enter start time (YYYY-MM-DD HH:MM:SS): ");
        String startTimeStr = scanner.nextLine().trim();

        System.out.print("Enter end time (YYYY-MM-DD HH:MM:SS): ");
        String endTimeStr = scanner.nextLine().trim();

        try {
            LocalDateTime startTime = LocalDateTime.parse(startTimeStr, formatter);
            LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);

            int userId = SessionManager.getLoggedInUser().getId();

            boolean success = reservationService.reserveSpace(spaceId, startTime, endTime,userId);
            if (success) {
                System.out.println("Reservation successful!");
            } else {
                System.out.println("Failed to reserve space. Please check the space ID and time values and try again.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format. Please use the format YYYY-MM-DD HH:MM:SS.");
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
