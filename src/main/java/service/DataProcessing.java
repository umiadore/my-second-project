package service;

public interface DataProcessing {
    String normalizeUsername(String username); 
    boolean isValidEmail(String email); 
    boolean isValidUsername(String username); 
    String processPassword(String password); 
}
