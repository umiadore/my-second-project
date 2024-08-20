package service;

import entity.User;
import repository.DataRepository;

import java.util.Scanner;
import java.util.regex.Pattern;

public class RegistrationServiceImpl implements RegistrationService {

    private final  EncryptionService encryptionService;
    private final DataRepository dataRepository;


    private final Scanner scanner;

    public RegistrationServiceImpl(EncryptionService encryptionService, DataRepository dataRepository, Scanner scanner) {
        this.encryptionService = encryptionService;
        this.dataRepository = dataRepository;
        this.scanner = scanner;
    }


    @Override
    public boolean registered(String login, String password) {
        if (isValidPassword(password)) {
            return true;
        }

        String encryptedPass = encryptionService.hiddenPass(password);
        User user = new User(login, encryptedPass);
        dataRepository.saveUser(user);
        return false;
    }


    // Для проверки пароля
    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                Pattern.compile("[0-9]").matcher(password).find() &&
                Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }

}


