package org.workPall.console;

import org.workPall.entities.User;
import org.workPall.services.interfaces.UserServiceInter;

import java.util.Scanner;

public class MemberConsole {
    private UserServiceInter userService;
    private Scanner scanner;

    public MemberConsole(UserServiceInter userService) {
        this.userService = userService;
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
        System.out.println("===== Reservations =====");
        System.out.println("Reservation management features are under development.");
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
