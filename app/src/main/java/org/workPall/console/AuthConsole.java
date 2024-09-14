package org.workPall.console;

import org.workPall.entities.User;
import org.workPall.enums.Role;
import org.workPall.services.interfaces.UserServiceInter;

import java.util.Scanner;

public class AuthConsole {

    private final UserServiceInter userService;
    private final MemberConsole memberConsole;
    private final ManagerConsole managerConsole;
    private final AdminConsole adminConsole;

    public AuthConsole(UserServiceInter userService, MemberConsole memberConsole, ManagerConsole managerConsole, AdminConsole adminConsole) {
        this.userService = userService;
        this.memberConsole = memberConsole;
        this.managerConsole = managerConsole;
        this.adminConsole = adminConsole;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Login ===");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Both email and password must be provided.");
            return;
        }

        if (userService.login(email, password)) {
            String role = userService.getUserData().getRole().toString();

            switch (role) {
                case "ADMIN":
                    System.out.println("Admin dashboard accessed.");
                    adminConsole.showAdminUI();
                    break;
                case "MANAGER":
                    System.out.println("Manager dashboard accessed.");
                    managerConsole.showManagerUI();
                    break;
                case "MEMBER":
                    System.out.println("Member dashboard accessed.");
                    memberConsole.showMemberUI();
                    break;
                default:
                    System.out.println("Unknown role: " + role);
                    break;
            }
        } else {
            System.out.println("Invalid email or password");
        }
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Register ===");
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

        System.out.println("Role: ");
        for (Role role : Role.getNonAdminRoles()) {
            System.out.println(role.ordinal() + ". " + role);
        }

        System.out.print("Select Role: ");
        int roleIndex = scanner.nextInt();
        scanner.nextLine();
        Role selectedRole = Role.getNonAdminRoles()[roleIndex];

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || password.isEmpty() || selectedRole == null) {
            System.out.println("All fields must be filled out.");
            return;
        }

        User user = new User(0, firstName, lastName, email, phoneNumber, address, password, selectedRole);
        User createdUser = userService.createUser(user);

        if (createdUser != null) {
            System.out.println("User Registered Successfully: " + createdUser.getFirstName());
        } else {
            System.out.println("Registration failed: Email already in use.");
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("=== Menu ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
