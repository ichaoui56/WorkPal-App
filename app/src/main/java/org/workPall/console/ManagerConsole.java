package org.workPall.console;

import org.workPall.entities.Event;
import org.workPall.entities.Space;
import org.workPall.entities.User;
import org.workPall.services.interfaces.SpaceServiceInter;
import org.workPall.services.interfaces.UserServiceInter;

import java.util.Map;
import java.util.Optional;
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
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("===== Spaces =====");
            System.out.println("1. Add a Space");
            System.out.println("2. Display All Spaces");
            System.out.println("3. Delete a Space");
            System.out.println("4. Modify a Space");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

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
                case 4:
                    createModifySpaceForm();
                    break;
                case 5:
                    keepRunning = false;
                    System.out.println("Exiting space management.");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
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
        boolean available;
        while(true) {
            String checkAvailableInput = scanner.next();
            if (checkAvailableInput.equalsIgnoreCase("true")) {
                available = true;
                break;
            } else if (checkAvailableInput.equalsIgnoreCase("false")) {
                available = false;
                break;
            } else {
                System.out.println("Invalid option, please try again.");
            }

        }
        Space newSpace = new Space(0, name, description, location, available);
        Space createdSpace = spaceService.addSpace(newSpace);
        if (createdSpace != null && createdSpace.getId() > 0) {
            System.out.println("Space added successfully: " + createdSpace.toString());
        } else {
            System.out.println("Failed to add space.");
        }
    }

    private void createModifySpaceForm() {
        System.out.println("===== Modify Space =====");
        System.out.print("Enter the space name to modify: ");
        String name = scanner.nextLine();

        Map<Integer, Space> spaces = spaceService.getSpacesByName(name);

        if (spaces.isEmpty()) {
            System.out.println("No spaces found with the given name.");
            return;
        }

        System.out.println("Spaces found with the name '" + name + "':");
        for (Map.Entry<Integer, Space> entry : spaces.entrySet()) {
            Space space = entry.getValue();
            System.out.println("ID: " + space.getId() + ", Name: " + space.getName() +
                    ", Description: " + space.getDescription() + ", Location: " + space.getLocation() +
                    ", Available: " + space.getAvailable());
        }

        System.out.print("Enter the ID of the space you want to modify: ");
        int spaceId = scanner.nextInt();
        scanner.nextLine();

        Space spaceToModify = spaces.get(spaceId);
        if (spaceToModify == null) {
            System.out.println("No space found with the given ID.");
            return;
        }

        System.out.print("Enter new name: ");
        spaceToModify.setName(scanner.nextLine());

        System.out.print("Enter new description: ");
        spaceToModify.setDescription(scanner.nextLine());

        System.out.print("Enter new location: ");
        spaceToModify.setLocation(scanner.nextLine());

        System.out.print("Is the space available (true/false): ");
        boolean available;
        while (true) {
            String availableInput = scanner.nextLine().trim();
            if (availableInput.equalsIgnoreCase("true")) {
                available = true;
                break;
            } else if (availableInput.equalsIgnoreCase("false")) {
                available = false;
                break;
            } else {
                System.out.print("Invalid input. Please enter 'true' or 'false': ");
            }
        }
        spaceToModify.setAvailable(available);

        boolean success = spaceService.modifySpaceByName(spaceToModify);
        if (success) {
            System.out.println("Space updated successfully.");
        } else {
            System.out.println("Failed to update space.");
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

        Map<Integer, Space> allSpaces = spaceService.getSpacesByName(spaceName);

        if(allSpaces.isEmpty()) {
            System.out.println("No spaces found with the given name.");
            return;
        }

        for (Map.Entry<Integer, Space> entry : allSpaces.entrySet()) {
            Space space = entry.getValue();
            System.out.println("ID: " + space.getId() + ", Name: " + space.getName() +
                    ", Description: " + space.getDescription() + ", Location: " + space.getLocation() +
                    ", Available: " + space.getAvailable());
        }

        System.out.println("Enter the ID of the space you want to delete: ");
        int spaceId = scanner.nextInt();
        scanner.nextLine();

        Space spaceToDelete = allSpaces.get(spaceId);
        if (spaceToDelete == null) {
            System.out.println("No space found with the given ID.");
        }

        boolean success = spaceService.deleteSpaceByName(spaceId);
        if (success) {
            System.out.println("Space deleted successfully.");
        } else {
            System.out.println("Failed to delete space.");
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
