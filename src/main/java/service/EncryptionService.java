package service;

public interface EncryptionService {
    String encryptedPass(String password); 
    boolean checkPassword(String storedPassword, String inputPassword); 
}
