package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionServiceImpl implements EncryptionService {

    private static final SecureRandom random = new SecureRandom(); 

    public EncryptionServiceImpl(AuthorizationService authorizationService) {
        
    }

    @Override
    public String encryptedPass(String password) {
        try {
            String salt = generateSalt(); 
            String saltedPassword = salt + password; 

            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
            byte[] hash = md.digest(saltedPassword.getBytes()); 
            StringBuilder hexString = new StringBuilder(); 

            for (byte b : hash) { 
                hexString.append(String.format("%02x", b)); 
            }

            System.out.println("[SECURITY] Password successfully encrypted."); 
            return salt + ":" + hexString.toString(); 
        } catch (NoSuchAlgorithmException e) {
            System.out.println("[ERROR] Error during password encryption: " + e.getMessage()); 
            throw new RuntimeException("Error during password encryption", e); 
        }
    }

    private String generateSalt() {
        byte[] salt = new byte[16]; 
        random.nextBytes(salt); 
        return Base64.getEncoder().encodeToString(salt); 
    }

    @Override
    public boolean checkPassword(String storedPassword, String inputPassword) {
        String[] parts = storedPassword.split(":"); 
        String salt = parts[0]; 
        String storedHash = parts[1]; 

        String saltedInputPassword = salt + inputPassword; 

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); 
            byte[] hash = md.digest(saltedInputPassword.getBytes()); 
            StringBuilder hexString = new StringBuilder(); 

            for (byte b : hash) { 
                hexString.append(String.format("%02x", b)); 
            }

            return storedHash.equals(hexString.toString()); 
        } catch (NoSuchAlgorithmException e) {
            System.out.println("[ERROR] Error during password checking: " + e.getMessage()); 
            throw new RuntimeException("Error during password checking", e); 
        }
    }
}
