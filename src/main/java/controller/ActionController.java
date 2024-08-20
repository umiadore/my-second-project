package controller;

import service.AuthorizationService;
import service.RegistrationService;

import java.util.Scanner;

public class ActionController {
    private Scanner scanner;
    private RegistrationService registrationService;
    private AuthorizationService authorizationService;

    public ActionController(Scanner scanner, RegistrationService registrationService, AuthorizationService authorizationService) {
        this.scanner = scanner;
        this.registrationService = registrationService;
        this.authorizationService = authorizationService;
    }

    public void chooseAction () {
        while (true) {
            String actionSelection = scanner.nextLine();
            Action action = Action.valueOf(actionSelection);

            switch (action) {
                case REGISTRATION -> registration () ;
                case AUTHORIZATION -> authorization ();
                // case RECOVER_PASSWORD ->recoverPass ();
               // case EXIT -> exit ();
            }
        }
    }
    private void registration () {
        System.out.println("Create new login x pass: ");
        String login = scanner.nextLine();
        String password = scanner.nextLine();
        boolean isRegistered = registrationService.registered(login, password);
        if (isRegistered)  {
            System.out.println("Your registration has been proceed successfully!");
        } else  {
            System.out.println("Try again!");
        }
    }
    private void authorization () {
        System.out.println("Input your login x pass: ");
        String login = scanner.nextLine();
        String password = scanner.nextLine();
        boolean isAuthorized = authorizationService.authorizedUser(login,password);
        if (isAuthorized)  {
            System.out.println("You are logged in!");
        } else  {
            System.out.println("Try again!");
        }

    }
/*
    private void recoverPass () {

    }

    private void exit () {

    }

 */
}
// в каждом сервисе сделать конструктор который тзаполняет все параметры