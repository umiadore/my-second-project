package service;

import entity.User;
import repository.DataRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;



public class AuthorizationServiceImpl implements AuthorizationService {

    private static final int MAX_ATTEMPTS = 3; 
    private static final long BLOCK_TIME_MS = TimeUnit.MINUTES.toMillis(5); 

    private final DataRepository dataRepository; 
    private final EncryptionService encryptionService; 

    private final Map<String, Integer> loginAttempts = new HashMap<>(); 
    private final Map<String, Long> blockedUsers = new HashMap<>(); 

    public AuthorizationServiceImpl(DataRepository dataRepository, EncryptionService encryptionService) {
        this.dataRepository = dataRepository; 
        this.encryptionService = encryptionService; 
    }

    @Override
    public boolean authorizedUser(String login, String password) {
        if (isBlocked(login)) { 
            System.out.println("Your account is temporarily blocked due to too many failed login attempts. Please try again later."); 
            return false; 
        }

        User user = dataRepository.findUserByUsername(login); 

        if (user == null) { 
            System.out.println("User not found."); 
            return false; 
        }

        if (encryptionService.checkPassword(user.getPassword(), password)) { 
            System.out.println("Authorization successful!"); 
            resetLoginAttempts(login); 
            return true; 
        } else { 
            System.out.println("Incorrect password."); 
            incrementLoginAttempts(login); 
            return false; 
        }
    }

    private void incrementLoginAttempts(String login) {
        int attempts = loginAttempts.getOrDefault(login, 0) + 1; 
        loginAttempts.put(login, attempts); 

        if (attempts >= MAX_ATTEMPTS) { 
            blockedUsers.put(login, System.currentTimeMillis()); 
            System.out.println("Your account has been blocked due to too many failed login attempts."); 
        }
    }

    private void resetLoginAttempts(String login) {
        loginAttempts.remove(login); 
        blockedUsers.remove(login); 
    }

    private boolean isBlocked(String login) {
        if (!blockedUsers.containsKey(login)) { 
            return false; 
        }

        long blockTime = blockedUsers.get(login); 
        if (System.currentTimeMillis() - blockTime > BLOCK_TIME_MS) { 
            resetLoginAttempts(login); 
            return false; 
        }

        return true; 
    }

    private boolean isPasswordStrong(String password) {
        if (password.length() < 8) return false; 
        if (!password.matches(".*\\d.*")) return false; 
        if (!password.matches(".*[!@#$%^&*].*")) return false; 
        if (!password.matches(".*[A-Z].*")) return false; 
        return true;
    }
}
