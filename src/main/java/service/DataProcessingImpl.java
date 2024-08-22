package service;

public class DataProcessingImpl implements DataProcessing {



    @Override
    public boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false; 
        }
        return email.contains("@") && email.contains("."); 
    }

    @Override
    public boolean isValidUsername(String username) {
        if (username == null || username.length() < 3) {
            return false; 
        }
        return username.matches("^[a-zA-Z0-9]+$"); 
    }

    @Override
    public String processPassword(String password) {

        return password.trim();
    }
}