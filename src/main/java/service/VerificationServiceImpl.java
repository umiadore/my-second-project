package service;

import entity.User;
import repository.DataRepository;

import java.lang.ref.SoftReference;

public class VerificationServiceImpl implements VerificationService {

    private final DataRepository dataRepository;

    public VerificationServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public boolean verifyUser(String login, String password) {
        User user = dataRepository.findUserByUsername(login);
        boolean verified = (user !=null && user.getPassword().equals(password));
        if (verified) {
            System.out.println("[SECURITY] User " + login + " successfully verified.");
        } else {
            System.out.println("[ERROR] Verification failed for user " + login);
        }
        return verified;
    }
}
