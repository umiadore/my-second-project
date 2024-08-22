package controller;

import service.AuthorizationService;
import service.RecoverPassImpl;
import service.RegistrationService;

import java.util.Scanner;

public class ActionController {

    private final Scanner scanner;
    private final RegistrationService registrationService;
    private final AuthorizationService authorizationService;
    private final RecoverPassImpl recoverPassImplService;

    public ActionController(Scanner scanner, RegistrationService registrationService, AuthorizationService authorizationService, RecoverPassImpl recoverPassImplService) {
        this.scanner = scanner;
        this.registrationService = registrationService;
        this.authorizationService = authorizationService;
        this.recoverPassImplService = recoverPassImplService;
    }

    public void chooseAction() {
        while (true) {
            System.out.println("*******************************");
            System.out.println("*    Welcome!                 *");
            System.out.println("*******************************");
            System.out.println("* Choose an action:           *");
            System.out.println("* 1. REGISTRATION             *");
            System.out.println("* 2. AUTHORIZATION            *");
            System.out.println("* 3. RECOVER PASSWORD         *");
            System.out.println("* 4. EXIT                     *");
            System.out.println("*******************************");
            System.out.print("Your choice (1-4): ");

            String actionSelection = scanner.nextLine();
            Action action = Action.fromCode(actionSelection);

            if (action == null) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            switch (action) {
                case REGISTRATION -> registration();
                case AUTHORIZATION -> authorization();
                case RECOVER_PASSWORD -> recoverPass();
                case EXIT -> {
                    exit();
                    return;
                }
            }
        }
    }

    private void registration() {
        System.out.println("*******************************");
        System.out.println("* User Registration            *");
        System.out.println("*******************************");
        System.out.print("Create a new username: ");
        String login = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();
        boolean isRegistered = registrationService.registerUser(login, password);

        if (isRegistered) {
            System.out.println("Your registration was successful!");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    private void authorization() {
        System.out.println("*******************************");
        System.out.println("* User Authorization           *");
        System.out.println("*******************************");
        System.out.print("Enter your username: ");
        String login = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        boolean isAuthorized = authorizationService.authorizedUser(login, password);

        if (isAuthorized) {
            System.out.println("You have successfully logged in!");
        } else {
            System.out.println("Authorization failed. Please try again.");
        }
    }

    private void recoverPass() {
        System.out.println("*******************************");
        System.out.println("* Password Recovery            *");
        System.out.println("*******************************");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        recoverPassImplService.recoverPassword(username);
    }

    private void exit() {
        System.out.println("*******************************");
        System.out.println("* Exiting the system           *");
        System.out.println("*******************************");
        System.exit(0);
    }
}