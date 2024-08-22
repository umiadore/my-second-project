package service;

public interface RegistrationService {
    boolean registerUser(String username, String password);
    boolean isPasswordStrong(String password);
}