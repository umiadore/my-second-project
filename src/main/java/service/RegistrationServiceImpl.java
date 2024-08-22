package service;

import entity.User;
import repository.DataRepository;

import java.util.Scanner;
import java.util.regex.Pattern;

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
        
        String normalizedUsername = dataProcessing.normalizeUsername(username);

        
        if (!dataProcessing.isValidUsername(normalizedUsername)) {
            System.out.println("Invalid username. Please try again."); 
            return false; 
        }

        
        if (dataRepository.isUsernameTaken(normalizedUsername)) {
            System.out.println("Username is already taken. Please choose another one."); 
            return false; 
        }

        
        String encryptedPassword = encryptionService.encryptedPass(password);

        
        User user = new User(normalizedUsername, encryptedPassword);
        dataRepository.saveUser(user); 

        System.out.println("Registration successful!"); 
        return true; 
    }
}

