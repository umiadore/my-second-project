package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionServiceImpl implements EncryptionService {

    // we use any type of encryption
    @Override
    public String hiddenPass(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            System.out.println("[SECURITY] Password encrypted successfully.");
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("[ERROR] Error during password encryption: " + e.getMessage());
            throw new RuntimeException("Error during password encryption", e);
        }
    }
}
