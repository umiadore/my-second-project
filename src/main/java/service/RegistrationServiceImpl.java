package service;

import entity.User;
import repository.DataRepository;

public class RegistrationServiceImpl implements RegistrationService {

    private final DataRepository dataRepository;
    private final EncryptionService encryptionService;
    private final DataProcessing dataProcessing;

    public RegistrationServiceImpl(DataRepository dataRepository, EncryptionService encryptionService, DataProcessing dataProcessing) {
        this.dataRepository = dataRepository;
        this.encryptionService = encryptionService;
        this.dataProcessing = dataProcessing;
    }

    @Override
    public boolean registerUser(String username, String password) {
        

        if (!dataProcessing.isValidUsername(username)) {
            System.out.println("Invalid username. Please try again.");
            return false;
        }

        if (dataRepository.isUsernameTaken(username)) {
            System.out.println("Username is already taken. Please choose another one.");
            return false;
        }

        if (!isPasswordStrong(password)) {
            System.out.println("Password is not strong enough.");
            return false;
        }

        String encryptedPassword = encryptionService.encryptedPass(password);

        User user = new User(username, encryptedPassword);
        dataRepository.saveUser(user);

        System.out.println("Registration successful!");
        return true;
    }

    public boolean isPasswordStrong(String password) {
        if (password.length() < 8) return false; 
        if (!password.matches(".*\\d.*")) return false; 
        if (!password.matches(".*[!@#$%^&*].*")) return false; 
        if (!password.matches(".*[A-Z].*")) return false; 
        return true;
    }
}