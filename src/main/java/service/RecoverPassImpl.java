package service;

import repository.DataRepository;
import entity.User;

public class RecoverPassImpl implements RecoverPass{

    private final DataRepository dataRepository; 
    private final EmailServiceImpl emailServiceImpl;
    private final EncryptionService encryptionService; 

    
    public RecoverPassImpl(DataRepository dataRepository, EmailServiceImpl emailServiceImpl, EncryptionService encryptionService) {
        this.dataRepository = dataRepository;
        this.emailServiceImpl = emailServiceImpl;
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

        user.setPassword(encryptedNewPassword);  
        dataRepository.saveUser(user);

        String emailBody = "Your new password is: " + newPassword;
        emailServiceImpl.sendEmail(username, "Password Recovery", emailBody);

        System.out.println("A new password has been sent to your email.");
    }

    private String generateTemporaryPassword() {
        
        return "TempPass1234";
    }
}
