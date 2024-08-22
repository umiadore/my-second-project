import controller.Action;
import controller.ActionController;
import repository.DataRepository;
import repository.DataRepositoryImpl;
import service.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        DataRepository dataRepository = new DataRepositoryImpl(); 
        DataProcessing dataProcessing = new DataProcessingImpl(); 
        EncryptionService encryptionService = new EncryptionServiceImpl(null); 
        AuthorizationService authorizationService = new AuthorizationServiceImpl(dataRepository, encryptionService); 
        RegistrationService registrationService = new RegistrationServiceImpl(dataRepository, encryptionService, dataProcessing); 
        EmailService emailService = new EmailService(); 
        RecoverPass recoverPassService = new RecoverPass(dataRepository, emailService, encryptionService); 

        
        Scanner scanner = new Scanner(System.in);

        
        ActionController actionController = new ActionController(scanner, registrationService, authorizationService, recoverPassService);

        
        actionController.chooseAction();
    }
}
