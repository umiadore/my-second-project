import controller.Action;
import controller.ActionController;
import repository.DataRepository;
import repository.DataRepositoryImpl;
import service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataRepository dataRepository = new DataRepositoryImpl();
        AuthorizationService authorizationService = new AuthorizationServiceImpl(dataRepository);

        EncryptionService encryptionService = new EncryptionServiceImpl( authorizationService);
        RegistrationService registrationService = new RegistrationServiceImpl(encryptionService, dataRepository, scanner);



        System.out.println("Log in or Sign in:");

        ActionController actionController = new ActionController(scanner, registrationService, authorizationService);
        actionController.chooseAction();


    }
}
