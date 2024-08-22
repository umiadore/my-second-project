package service;

import repository.DataRepository;
import entity.User;

public class RecoverPass {

    private final DataRepository dataRepository; 
    private final EmailService emailService; 
    private final EncryptionService encryptionService; 

    
    public RecoverPass(DataRepository dataRepository, EmailService emailService, EncryptionService encryptionService) {
        this.dataRepository = dataRepository;
        this.emailService = emailService;
        this.encryptionService = encryptionService;
    }

    
    public void recoverPassword(String username) {
        User user = dataRepository.findUserByUsername(username); 

        if (user == null) {
            System.out.println("User not found."); 
            return; 
        }

        String newPassword = generateTemporaryPassword(); 
        String encryptedNewPassword = encryptionService.encryptedPass(newPassword); 

        user.setUsername(encryptedNewPassword); 
        dataRepository.saveUser(user); 

        String emailBody = "Your new password is: " + newPassword; 
        emailService.sendEmail(username, "Password Recovery", emailBody); 

        System.out.println("A new password has been sent to your email."); 
    }

    private String generateTemporaryPassword() {
        
        return "TempPass1234";
    }
}
