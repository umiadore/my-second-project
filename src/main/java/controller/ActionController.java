package controller;

import java.util.Scanner;

public class ActionController {
    //in order to interact with ENUM, we need a scanner
    private Scanner scanner;



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
        // later on ill add the logic
    }
    private void authorization () {
        // later on
    }
}
