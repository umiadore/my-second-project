package service;

public interface DataProcessing {

    boolean isValidEmail(String email); 
    boolean isValidUsername(String username); 
     String processPassword(String password);
}
