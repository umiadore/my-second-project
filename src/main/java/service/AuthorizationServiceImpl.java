package service;

import entity.User;
import repository.DataRepository;

public class AuthorizationServiceImpl implements AuthorizationService {

    private final DataRepository dataRepository;

    public AuthorizationServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    //encrypt the pass

    @Override
    public boolean authorizedUser(String login, String password) {
            User user = dataRepository.findUserByUsername(login);
            boolean authorized = (user !=null && user.getPassword().equals(password));
            if (authorized) {
                System.out.println("[SECURITY] User " + login + " successfully verified.");
            } else {
                System.out.println("[ERROR] Verification failed for user " + login);
            }
            return authorized;
        }
    }
