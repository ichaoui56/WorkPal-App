package org.workPall.console;

import org.workPall.entities.User;
import org.workPall.enums.Role;
import org.workPall.services.interfaces.UserServiceInter;
import org.workPall.services.interfaces.SpaceServiceInter;

import java.util.Map;
import java.util.Scanner;

public class AdminConsole {
    private UserServiceInter userService;
    private Scanner scanner;

    public AdminConsole(UserServiceInter userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showAdminUI() {
        boolean running = true;
        while (running) {
            System.out.println("===== Admin Dashboard =====");
            System.out.println("1. Home");
            System.out.println("2. Manage Users");
            System.out.println("3. Log Out");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showHome();
                    break;
                case 2:
                    manageUsers();
                    break;
                case 3:
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
        System.out.println("Welcome to the Admin Dashboard");
    }

    private void manageUsers() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("===== Manage Users =====");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Back");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAddUserForm();
                    break;
                case 2:
                    displayAllUsers();
                    break;
                case 3:
                    createUpdateUserForm();
                    break;
                case 4:
                    createDeleteUserForm();
                    break;
                case 5:
                    keepRunning = false;
                    System.out.println("Exiting user management.");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void createAddUserForm() {
        System.out.println("===== Add User =====");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Role (ADMIN/USER): ");
        String roleInput = scanner.nextLine();
        Role role = Role.valueOf(roleInput.toUpperCase());

        User newUser = new User(0, firstName, lastName, email, phoneNumber, address, password, role);
        try {
            userService.createUser(newUser);
            System.out.println("User added successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createUpdateUserForm() {
        System.out.println("===== Update User =====");
        System.out.print("Enter User ID to Update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User userToUpdate = userService.getUserById(userId);
        if (userToUpdate == null) {
            System.out.println("No user found with the given ID.");
            return;
        }

        System.out.print("New First Name (leave blank to keep current): ");
        String firstName = scanner.nextLine();
        if (!firstName.isEmpty()) {
            userToUpdate.setFirstName(firstName);
        }

        System.out.print("New Last Name (leave blank to keep current): ");
        String lastName = scanner.nextLine();
        if (!lastName.isEmpty()) {
            userToUpdate.setLastName(lastName);
        }

        System.out.print("New Email (leave blank to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            userToUpdate.setEmail(email);
        }

        System.out.print("New Phone Number (leave blank to keep current): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            userToUpdate.setPhoneNumber(phoneNumber);
        }

        System.out.print("New Address (leave blank to keep current): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            userToUpdate.setAddress(address);
        }

        System.out.print("New Password (leave blank to keep current): ");
        String password = scanner.nextLine();
        if (!password.isEmpty()) {
            userToUpdate.setPassword(password);
        }

        System.out.print("New Role (ADMIN/USER, leave blank to keep current): ");
        String roleInput = scanner.nextLine();
        if (!roleInput.isEmpty()) {
            Role role = Role.valueOf(roleInput.toUpperCase());
            userToUpdate.setRole(role);
        }
        try {
            userService.updateUser(userToUpdate);
            System.out.println("User updated successfully.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void createDeleteUserForm() {
        System.out.println("===== Delete User =====");
        System.out.print("Enter User ID to Delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayAllUsers() {
        Map<Integer, User> users = userService.getAllUsers();
        System.out.println("===== All Users =====");
        for (User user : users.values()) {
            System.out.println(user.toString());
        }
    }


}
