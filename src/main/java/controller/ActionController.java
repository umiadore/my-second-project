package controller;

import service.ActionService;

import java.util.Scanner;

public class ActionController {
    private Scanner scanner;
    private ActionService actionService;

    public void chooseAction () {
        while (true) {
            String actionSelection = scanner.nextLine();
            Action action = Action.valueOf(actionSelection);

            switch (action) {
                case REGISTRATION -> registration () ;
                case AUTHORIZATION -> authorization ();
            }
        }
    }
    private void registration () {
        System.out.println("Create login x pass: ");
        String login = scanner.nextLine();
        String password = scanner.nextLine();
        boolean isRegistered = actionService.registered(login, password);
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
        boolean isAuthorized = actionService.authorized(login, password);
        if (isAuthorized)  {
            System.out.println("You are logged in!");
        } else  {
            System.out.println("Try again!");
        }

    }
}
